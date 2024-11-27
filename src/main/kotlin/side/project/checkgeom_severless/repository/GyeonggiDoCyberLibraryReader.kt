package side.project.checkgeom_severless.repository

import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import org.springframework.web.client.body
import side.project.checkgeom_severless.repository.library.gyeonggidocyberlibrary.GyeonggiDoCyberLibrary
import side.project.checkgeom_severless.repository.library.gyeonggidocyberlibrary.GyeonggiDoCyberLibraryBookType
import side.project.checkgeom_severless.repository.library.gyeonggidocyberlibrary.GyeonggiDoCyberLibraryMoreViewType
import side.project.checkgeom_severless.repository.response.LibraryRepositoryResponse
import side.project.checkgeom_severless.service.response.LibrarySearchServiceResponse
import side.project.checkgeom_severless.util.WebDriverUtil
import side.project.checkgeom_severless.util.WebDriverUtil.createWebDriver
import side.project.checkgeom_severless.util.WebDriverUtil.createWebDriverWait
import java.util.*
import java.util.function.Function
import java.util.regex.Pattern

@Component
class GyeonggiDoCyberLibraryReader(
    private val libraryBookInfoReader: LibraryBookInfoReader ,
    private val restClient: RestClient
) {
    private val log: Logger = LoggerFactory.getLogger(javaClass)


    fun getHtml () : String? {
        return restClient.get()
            .uri(GyeonggiDoCyberLibrary.basicSearchUrlCreate("스프링"))
            .retrieve()
            .body<String>()
    }



    fun searchBookList(htmlBody: Element): List<LibrarySearchServiceResponse.BookDto> {
        return getGyeonggiDoCyberLibraryResponse(htmlBody).stream()
            .map(LibrarySearchServiceResponse.BookDto::of)
            .toList()
    }

    fun gyeonggiDoCyberLibraryGetBookDataList(htmlBody: Element): List<LibrarySearchServiceResponse.BookDto> {
        return getGyeonggiDoCyberLibraryResponse(htmlBody).stream()
            .map(LibrarySearchServiceResponse.BookDto::of)
            .toList()
    }


    fun isMoreViewList(htmlBody: Element): List<GyeonggiDoCyberLibraryMoreViewType?> {
        return htmlBody.select("h5.searchH")
            .filterNot { it.text().contains("오디오북") } // "오디오북"이 포함되지 않은 요소 필터링
            .map { mapGyeonggiDoCyberLibraryMoreViewType(it) } // 매핑하여 결과 리스트 생성
    }

    fun getGyeonggiDoCyberLibraryResponse(htmlBody: Element): List<LibraryRepositoryResponse> {
        return htmlBody.select("li.bookItem.row")
            .map { getGyeonggiDoCyberLibraryRepositoryResponse(it) } // 매핑하여 결과 리스트 생성
    }


    private fun getGyeonggiDoCyberLibraryRepositoryResponse(
        htmlElement: Element
    ): LibraryRepositoryResponse {
        val bookImageLink = getBookImgeLink(htmlElement)
        val title = getBookTitle(htmlElement)

        // 인덱스 순서는 작성자 , 출판사 ,출판 날짜
        val bookPublishingInformationList = getBookPublishingInformationList(htmlElement)

        val text = htmlElement.select("div.stat").first().text()

        //        System.out.println("대출예약 현황 = " + text);    ex : 대출 : 1/5 예약 : 0/5
        val loanReservationStatus = getLoanReservationStatus(htmlElement)

        return LibraryRepositoryResponse.of(
            bookImageLink, title, bookPublishingInformationList[0],
            bookPublishingInformationList[1], bookPublishingInformationList[2], loanReservationStatus
        )
    }

    fun getGyeonggiDoCyberLibraryHtmlBody(searchKeyword: String?): Element {
        val basicSearchUrl: String = GyeonggiDoCyberLibrary.basicSearchUrlCreate(searchKeyword)
        val webDriver: WebDriver = openWebBrowser(basicSearchUrl)
        val htmlBody = Jsoup.parse(webDriver.getPageSource()).body()
        webDriver.quit()
        return htmlBody
    }


    private fun openWebBrowser(basicSearchUrl: String): WebDriver {
        val webDriver: WebDriver = createWebDriver()
        val webDriverWait: WebDriverWait = createWebDriverWait(webDriver)

        webDriver.get(basicSearchUrl) // 브라우저에서 url로 이동한다.
        webDriverWait.until<Boolean>(
            ExpectedConditions.textMatches(
                By.cssSelector(GyeonggiDoCyberLibrary.STAY_CSS),
                Pattern.compile("\\d+")
            )
        )

        return webDriver
    }

    fun getMoreViewLinks(searchKeyword: String?, htmlBody: Element): List<String> {
        val moreViewList = isMoreViewList(htmlBody)

        // "isMoreView"가 true인 경우에만 URL 리스트 생성
        return if (moreViewList.any { it!!.isMoreView }) {
            moreViewList.map { viewType ->
                GyeonggiDoCyberLibrary.moreViewSearchUrlCreate(searchKeyword, viewType)
            }
        } else {
            emptyList() // 빈 리스트 반환
        }
    }


    fun getBookSearchTotalCount(htmlBody: Element): Int {
        val StringTotalCount = htmlBody.select("h4.summaryHeading i").text().replace(",".toRegex(), "")
        return StringTotalCount.toInt()
    }

    companion object {
        private fun mapGyeonggiDoCyberLibraryMoreViewType(element: Element): GyeonggiDoCyberLibraryMoreViewType? {
            val childText = element.child(0).text()

            val moreViewTotalCount = childText.replace("[^0-9]".toRegex(), "")

            val index = childText.indexOf("(")
            val findHtmlBookType = if ((index >= 0)) childText.substring(0, index) else childText

            if (findHtmlBookType == "오디오북") {
                return null
            }

            val bookType: GyeonggiDoCyberLibraryBookType = GyeonggiDoCyberLibraryBookType.of(findHtmlBookType)
            return GyeonggiDoCyberLibraryMoreViewType.of(
                bookType,
                moreViewTotalCount.toInt()
            )
        }


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
            return Arrays.stream(
                htmlElement.select("p.desc").text()
                    .split("/".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()).toList()
        }
    }
}
