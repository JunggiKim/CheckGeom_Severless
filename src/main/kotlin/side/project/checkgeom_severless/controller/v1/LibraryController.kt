package side.project.checkgeom_severless.controller.v1


import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import side.project.checkgeom_severless.service.LibrarySearchService
import side.project.checkgeom_severless.service.response.LibrarySearchServiceResponse
import side.project.checkgeom_severless.support.response.ApiResponse

@RestController
class LibraryController(
    private val libraryService: LibrarySearchService
) {

    @GetMapping("/api/v1/gyeonggiDoCyberLibrary")
    fun gyeonggiDoCyberLibrarySearch(
        @RequestParam searchKeyword: String
    ): ApiResponse<*> {
        val librarySearchServiceResponse: LibrarySearchServiceResponse =
            libraryService.gyeonggiDoCyberLibrarySearch(searchKeyword)
        return ApiResponse.success(librarySearchServiceResponse)
    }

    @GetMapping("/api/v1/gyeonggiEducationalElectronic")
    fun gyeonggiEducationalElectronicLibrarySearch(
        @RequestParam searchKeyword: String
    ): ApiResponse<*> {
        return ApiResponse.success(libraryService.gyeonggiEducationalElectronicLibrarySearch(searchKeyword))
    }

    @GetMapping("/api/v1/smallBusiness/{searchKeyword}")
    fun smallBusinessLibrarySearch(
        @RequestParam searchKeyword: String
    ): ApiResponse<*> {
        return ApiResponse.success(libraryService.smallBusinessLibrarySearch(searchKeyword))
    }

    @GetMapping("/api/v1/allLibrary/{searchKeyword}")
    fun allLibraryAsyncSearch(@PathVariable("searchKeyword") searchKeyword: String): ApiResponse<*> {
        return ApiResponse.success(libraryService.allLibraryAsyncSearch(searchKeyword))
    }

    @GetMapping("/api/v1/ok")
    fun check(): ApiResponse<*> {
        return ApiResponse.success()
    }

    // Uncomment and use if needed
    // @GetMapping("/api/v1/allLibrary2")
    // fun allLibraryAsyncSearch(
    //     @RequestParam  searchKeyword: String,
    //     @RequestParam searchType: SearchType
    // ): ApiResponse<*> {
    //     return ApiResponse.success(libraryService.allLibraryAsyncSearch(searchKeyword))
    // }

    // @GetMapping("/api/v1/allLibraryVirtualThreadAsyncSearch/{searchKeyword}")
    // @Throws(ExecutionException::class, InterruptedException::class)
    // fun allLibraryVirtualThreadAsyncSearch(@PathVariable searchKeyword: String): ApiResponse<*> {
    //     validation(searchKeyword)
    //     return ApiResponse.success(libraryService.allLibraryVirtualThreadAsyncSearch(searchKeyword))
    // }
}
