package side.project.checkgeom_severless.repository.response

data class LibraryRepositoryResponse(
    val bookImageLink: String,
    val title: String,
    val author: String,
    val publisher: String,
    val publicationDate: String,
    val loanAvailability: String
) {
    companion object {
        fun of(
            bookImageLink: String, title: String, author: String,
            publisher: String, publicationDate: String, loanAvailability: String
        ): LibraryRepositoryResponse {
            return LibraryRepositoryResponse(
                bookImageLink, title, author, publisher, publicationDate,
                loanAvailability
            )
        }
    }
}
