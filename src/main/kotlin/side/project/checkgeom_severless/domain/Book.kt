package side.project.checkgeom_severless.domain

class Book
private constructor(
    private val bookImageLink: String,
    private val title: String,
    private val author: String,
    private val publisher: String,
    private val publicationDate: String,
    private val loanAvailability: String
) {
    override fun toString(): String {
        return "BookDto{" +
                "bookImageLink='" + bookImageLink + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                ", loanAvailability='" + loanAvailability + '\'' +
                '}'
    }


    companion object {
        fun of(
            bookImageLink: String,
            title: String,
            author: String,
            publisher: String,
            publicationDate: String,
            loanAvailability: String
        ): Book {
            return Book(
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
