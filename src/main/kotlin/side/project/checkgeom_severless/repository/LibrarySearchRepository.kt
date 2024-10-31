package side.project.checkgeom_severless.repository

import org.springframework.stereotype.Repository
import org.springframework.web.client.RestClient
import side.project.checkgeom_severless.repository.library.gyeonggidocyberlibrary.GyeonggiDoCyberLibrary
import side.project.checkgeom_severless.repository.response.LibraryRepositoryResponse

@Repository
class LibrarySearchRepository(
     private val restClient: RestClient ,
) {


    fun gyeonggiDoCyberLibrarySearch(searchKeyword: String): LibraryRepositoryResponse? {
        return restClient.get()
            .uri(GyeonggiDoCyberLibrary.basicSearchUrlCreate(searchKeyword))
            .retrieve()
            .body(LibraryRepositoryResponse::class.java);
    }

    }


