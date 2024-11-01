package side.project.checkgeom_severless.start

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import side.project.checkgeom_severless.service.LibrarySearchService
import side.project.checkgeom_severless.service.response.LibrarySearchServiceResponse
import side.project.checkgeom_severless.support.response.ApiResponse


@Component
class RegradingFunction(
    private val libraryService: LibrarySearchService
) {


    @Bean
    fun processString(): java.util.function.Function<String, ApiResponse<String>> {
        return java.util.function.Function { input : String ->
            ApiResponse.success(input)
        }
    }

}