package side.project.checkgeom_severless.repository


import org.jsoup.nodes.Element
import org.springframework.stereotype.Component
import side.project.checkgeom_severless.repository.response.LibraryRepositoryResponse
import java.util.*
import java.util.function.Function

@Component
class LibraryBookInfoReader {
    fun getGyeonggiDoCyberLibraryResponse(htmlBody: Element): List<LibraryRepositoryResponse> {
        val searchBookItems = htmlBody.select("li.bookItem.row")
        return searchBookItems.stream()
            .map<LibraryRepositoryResponse>(Function<Element, LibraryRepositoryResponse> { htmlElement: Element ->
                this.getGyeonggiDoCyberLibraryRepositoryResponse(
                    htmlElement
                )
            })
            .toList()
    }

    private fun getGyeonggiDoCyberLibraryRepositoryResponse(
        htmlElement: Element
    ): LibraryRepositoryResponse {
        val bookImageLink = getBookImgeLink(htmlElement)
        val title = getBookTitle(htmlElement)
        // 인덱스 순서는 작성자 , 출판사 ,출판 날짜
        val bookPublishingInformationList = getBookPublishingInformationList(htmlElement)

//        val text = htmlElement.select("div.stat").first().text()

        //        System.out.println("대출예약 현황 = " + text);    ex : 대출 : 1/5 예약 : 0/5
        val loanReservationStatus = getLoanReservationStatus(htmlElement)

        return LibraryRepositoryResponse.of(
           bookImageLink = bookImageLink,
           title = title,
           author = bookPublishingInformationList[0],
           publisher = bookPublishingInformationList[1],
           publicationDate = bookPublishingInformationList[2],
           loanAvailability = loanReservationStatus
        )
    }

    fun getBookSearchTotalCount(htmlBody: Element): Int {
        val StringTotalCount = htmlBody.select("h4.summaryHeading i").text().replace(",".toRegex(), "")
        return StringTotalCount.toInt()
    }


    companion object {
        private fun getLoanReservationStatus(htmlElement: Element): String {
            return htmlElement.select("div.stat").first().text()
        }

        private fun getBookImgeLink(htmlElement: Element): String {
            return htmlElement.select("img.bookCover").attr("src")
        }

        private fun getBookTitle(htmlElement: Element): String {
            return htmlElement.select("h6.title").first().children().first().text()
        }

        private fun getBookPublishingInformationList(htmlElement: Element): List<String> {
            return Arrays.stream(htmlElement.select("p.desc").text().split("/".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()).toList()
        }
    }
}
