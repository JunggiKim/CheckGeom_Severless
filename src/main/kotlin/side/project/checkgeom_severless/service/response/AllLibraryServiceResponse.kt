package side.project.checkgeom_severless.service.response

import side.project.checkgeom_severless.repository.response.LibraryRepositoryResponse


data class AllLibraryServiceResponse(
    val librarySearchServiceResponseList: List<LibrarySearchServiceResponse>,
    val libraryTypeText: String
) {
    data class BookDto(
        val bookImageLink: String,
        val title: String,
        val author: String,
        val publisher: String,
        val publicationDate: String,
        val loanAvailability: String
    ) {

        companion object {
            fun of(
                repositoryResponse: LibraryRepositoryResponse
            ): BookDto {
                return BookDto(
                   bookImageLink= repositoryResponse.bookImageLink,
                   title= repositoryResponse.title,
                   author= repositoryResponse.author,
                   publisher= repositoryResponse.publisher,
                   publicationDate= repositoryResponse.publicationDate,
                   loanAvailability= repositoryResponse.loanAvailability
                )
            }

            fun of(
                bookImageLink: String,
                title: String,
                author: String,
                publisher: String,
                publicationDate: String,
                loanAvailability: String
            ): BookDto {
                return BookDto(
                   bookImageLink= bookImageLink,
                   title= title,
                   author= author,
                   publisher= publisher,
                   publicationDate= publicationDate,
                   loanAvailability= loanAvailability
                )
            }
        }
    }

    companion object {
        fun of(
            librarySearchServiceResponseList: List<LibrarySearchServiceResponse>,
            libraryTypeText: String
        ): AllLibraryServiceResponse {
            return AllLibraryServiceResponse(
               librarySearchServiceResponseList =  librarySearchServiceResponseList,
                libraryTypeText =  libraryTypeText)
        }
    }
}