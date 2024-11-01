package side.project.checkgeom_severless.repository.library.gyeonggidocyberlibrary

data class GyeonggiDoCyberLibraryMoreViewType(
    val bookType: GyeonggiDoCyberLibraryBookType,
    val totalCount: Int
) {
    val isMoreView: Boolean
        get() = this.totalCount > 6

    val isNotMoreView: Boolean
        get() = this.totalCount <= 6


    companion object {
        fun of(bookType: GyeonggiDoCyberLibraryBookType, totalCount: Int): GyeonggiDoCyberLibraryMoreViewType {
            return GyeonggiDoCyberLibraryMoreViewType(
                bookType,
                totalCount
            )
        }
    }
}
