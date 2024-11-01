package side.project.checkgeom_severless.service

import side.project.checkgeom_severless.service.response.AllLibraryServiceResponse
import side.project.checkgeom_severless.service.response.LibrarySearchServiceResponse


interface LibrarySearchService {
    fun gyeonggiDoCyberLibrarySearch(searchKeyword: String): LibrarySearchServiceResponse

    fun gyeonggiEducationalElectronicLibrarySearch(searchKeyword: String): LibrarySearchServiceResponse

    fun smallBusinessLibrarySearch(searchKeyword: String): LibrarySearchServiceResponse

    suspend fun allLibraryAsyncSearch(searchKeyword: String): AllLibraryServiceResponse
}
