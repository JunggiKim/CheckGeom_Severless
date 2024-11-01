package side.project.checkgeom_severless.repository.library.gyeonggidocyberlibrary

object GyeonggiDoCyberLibrary {
    // all
    private const val BASIC_SEARCH_URL = "https://ebook.library.kr/search?listType=list&keyword="
    private const val MORE_VIEW_SEARCH_URL =
        "https://ebook.library.kr/search/type?searchType=all&listType=list&asc=desc&keyword="
    const val STAY_CSS: String = "h4.summaryHeading i"
    const val CONTENT_TYPE_URL: String = "&contentType="
    const val SIZE: String = "&size="


    fun basicSearchUrlCreate(searchKeyword: String?): String {
        return (BASIC_SEARCH_URL + (searchKeyword)
                + "&searchType=" + "all")
    }

    fun moreViewSearchUrlCreate(keyword: String?, viewType: GyeonggiDoCyberLibraryMoreViewType?): String {
        return StringBuilder().append(MORE_VIEW_SEARCH_URL).append(keyword)
            .append(CONTENT_TYPE_URL).append(viewType?.bookType?.urlType)
            .append(SIZE).append(viewType?.totalCount)
            .toString()
    }
}
