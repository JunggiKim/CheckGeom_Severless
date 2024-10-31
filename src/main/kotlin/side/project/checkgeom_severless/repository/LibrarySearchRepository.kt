package side.project.checkgeom_severless.repository

import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestClient
import org.springframework.web.client.body
import side.project.checkgeom_severless.repository.library.gyeonggidocyberlibrary.GyeonggiDoCyberLibrary
import side.project.checkgeom_severless.repository.response.LibraryRepositoryResponse

@Repository
class LibrarySearchRepository(
     private val restClient: RestClient ,
) {


    fun gyeonggiDoCyberLibrarySearch(searchKeyword: String): Map<*, *>? {
        return restClient.get()
            .uri(GyeonggiDoCyberLibrary.basicSearchUrlCreate(searchKeyword))
            .retrieve()
            .body(Map::class.java)

//            .(object : ParameterizedTypeReference<Map<String, Any>>() {})
//            .body(Map);
    }

    }


