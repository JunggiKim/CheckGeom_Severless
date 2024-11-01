package side.project.checkgeom_severless.domain

data class MoreView(
    val isMoreView: Boolean,
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
