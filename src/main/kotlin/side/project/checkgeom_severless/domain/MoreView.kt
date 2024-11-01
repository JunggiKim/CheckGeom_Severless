package side.project.checkgeom_severless.domain

@JvmRecord
data class MoreView(
    val moreView: Boolean,
    val totalCount: Int
) {
    companion object {
        fun create(
            totalCount: Int
        ): MoreView {
            return MoreView(
                isMoreView(totalCount),
                totalCount
            )
        }

        private fun isMoreView(totalCount: Int): Boolean {
            return totalCount >= 10
        }
    }
}
