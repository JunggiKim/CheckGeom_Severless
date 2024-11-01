package side.project.checkgeom_severless.repository.library.gyeonggiEducationalElectronicLibrary

object gyeonggiEducationalElectronicLibrary {
    const val stayClassName: String = "smain"

    private const val basicSearchUrl =
        "https://lib.goe.go.kr/elib/module/elib/search/index.do?com_code=&menu_idx=94&viewPage=1&type=&search_text="

    private const val MORE_VIEW_COUNT = "&rowCount="


    fun basicSearchUrlCreate(searchKeyword: String?): String {
        return StringBuilder(basicSearchUrl).append(searchKeyword)
            .toString()
    }

    fun moreViewSearchUrlCreate(basicUrl: String?, totalCount: Int): String {
        return StringBuilder().append(basicUrl)
            .append(MORE_VIEW_COUNT).append(totalCount)
            .toString()
    }
}
