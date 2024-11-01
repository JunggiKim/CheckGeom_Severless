package side.project.checkgeom_severless.repository.library.gyeonggidocyberlibrary

enum class GyeonggiDoCyberLibraryBookType(
    val subName: String ,
    val urlType: String
) {
    COLLECTION("소장형", "EB"),
    SUBSCRIPTION("구독형", "SUBS");

    companion object {
        fun of(bookType: String): GyeonggiDoCyberLibraryBookType {
            return when (bookType.trim { it <= ' ' }) {
                "소장형" -> COLLECTION
                "구독형" -> SUBSCRIPTION
                else -> throw RuntimeException("$bookType : 지원 하는 타입이 아닙니다.")
            }
        }
    }
}
