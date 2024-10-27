package side.project.checkgeom_severless.repository

import org.springframework.http.HttpMethod
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import side.project.checkgeom_severless.config.WebClientConfig
import side.project.checkgeom_severless.repository.library.gyeonggidocyberlibrary.GyeonggiDoCyberLibrary
import side.project.checkgeom_severless.repository.response.LibraryRepositoryResponse

@Repository
class LibrarySearchRepository(
     private val webClient: WebClient ,
) {


    fun gyeonggiDoCyberLibrarySearch(searchKeyword: String): Mono<LibraryRepositoryResponse> {

        return webClient.get()
            .uri(GyeonggiDoCyberLibrary.basicSearchUrlCreate(searchKeyword))
            .retrieve()
            .bodyToMono(LibraryRepositoryResponse::class.java)

    }




//    fun getDataFromExternalServerWithParams(endpoint: String, params: Map<String, String>): Mono<String> {
//        return webClient.get()
//            .uri(uri)
//            .retrieve()
//            .bodyToMono(String::class.java)
//    }




    fun <T> get(url: String, responseDtoClass: Class<T>): T {
        return webClient.method(HttpMethod.GET)
            .uri(url)
            .retrieve()
//            .onStatus({ it.is4xxClientError }) { Mono.error(InternalServerException.EXCEPTION) }
//            .onStatus({ it.is5xxServerError }) { Mono.error(InternalServerException.EXCEPTION) }
            .bodyToMono(responseDtoClass)
            .block()!!
    }

    fun <T, V : Any> post(url: String, requestDto: V, responseDtoClass: Class<T>): T {
        return webClient.method(HttpMethod.POST)
            .uri(url)
            .bodyValue(requestDto)
            .retrieve()
//            .onStatus({ it.is4xxClientError }) { Mono.error(InternalServerException.EXCEPTION) }
//            .onStatus({ it.is5xxServerError }) { Mono.error(InternalServerException.EXCEPTION) }
            .bodyToMono(responseDtoClass)
            .block()!!

//    webClient post example
//    fun postDataToExternalServer(endpoint: String, requestBody: Any): Mono<String> {
//        return webClient.post()
//            .uri(endpoint)
//            .bodyValue(requestBody)
//            .retrieve()
//            .bodyToMono(String::class.java)
//    }


}

