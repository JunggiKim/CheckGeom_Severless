package side.project.checkgeom_severless.service.response

import side.project.checkgeom_severless.repository.response.LibraryRepositoryResponse


data class LibrarySearchServiceResponse(
    val bookDtoList: List<BookDto>,
    val bookSearchTotalCount: Int,
    val moreViewLink: List<String>,
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
                    repositoryResponse.bookImageLink,
                    repositoryResponse.title,
                    repositoryResponse.author,
                    repositoryResponse.publisher,
                    repositoryResponse.publicationDate,
                    repositoryResponse.loanAvailability
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
                    bookImageLink,
                    title,
                    author,
                    publisher,
                    publicationDate,
                    loanAvailability
                )
            }
        }
    }

    companion object {
        fun of(
            bookList: List<BookDto>,
            bookSearchTotalCount: Int, moreViewLink: List<String>, libraryTypeText: String
        ): LibrarySearchServiceResponse {
            return LibrarySearchServiceResponse(bookList, bookSearchTotalCount, moreViewLink, libraryTypeText)
        }
    }
}