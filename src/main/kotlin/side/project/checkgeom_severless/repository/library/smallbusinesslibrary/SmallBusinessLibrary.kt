package side.project.checkgeom_severless.repository.library.smallbusinesslibrary


object SmallBusinessLibrary {
    private const val BASIC_SEARCH_URL =
        "https://semas.dkyobobook.co.kr/search/searchList.ink?schClst=all&schDvsn=001&orderByKey=&schTxt="
    private const val MORE_VIEW_URL =
        "https://semas.dkyobobook.co.kr/search/searchList.ink?schClst=all&schDvsn=001&orderByKey=&reSchTxt=&pageIndex=1&recordCount=20"

    const val ROW_COUNT: String = "&selViewCnt="
    const val SEARCH_TEXT: String = "&schTxt="

    fun basicUrlCreate(searchKeyword: String): String {
        return BASIC_SEARCH_URL + searchKeyword
    }


    fun moreViewUrlCreate(searchKeyword: String?, totalSearchCount: Int): String {
        return StringBuilder(MORE_VIEW_URL)
            .append(SEARCH_TEXT).append(searchKeyword)
            .append(ROW_COUNT).append(totalSearchCount)
            .toString()
    }
}
