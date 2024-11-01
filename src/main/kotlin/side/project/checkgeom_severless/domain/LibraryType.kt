package side.project.checkgeom_severless.domain


enum class LibraryType(
    val englishText: String,
    val koreanText: String
) {
    ALL("all", "전체"),
    GYEONGGIDO_CYBER("gyeonggiDoCyberLibrary", "경기도사이버도서관"),
    GYEONGGI_EDUCATIONAL_ELECTRONIC("gyeonggiEducationalElectronic", "경기교육전자도서관"),
    SMALL_BUSINESS("smallBusiness", "소상공인전자도서관")
}
