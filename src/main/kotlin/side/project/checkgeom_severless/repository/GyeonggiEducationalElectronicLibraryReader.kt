package side.project.checkgeom_severless.repository


import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.springframework.stereotype.Component
import side.project.checkgeom_severless.domain.MoreView
import side.project.checkgeom_severless.repository.library.gyeonggiEducationalElectronicLibrary.gyeonggiEducationalElectronicLibrary
import side.project.checkgeom_severless.service.response.LibrarySearchServiceResponse
import side.project.checkgeom_severless.util.WebDriverUtil.createWebDriver
import side.project.checkgeom_severless.util.WebDriverUtil.createWebDriverWait
import java.util.*

@Component
class GyeonggiEducationalElectronicLibraryReader {
    fun getGyeonggiEducationalElectronicLibraryHtml(searchUrl: String): Document {
        val webDriver: WebDriver = openWebBrowser(searchUrl)
        val document = Jsoup.parse(webDriver.getPageSource())
        webDriver.quit()
        return document
    }


    private fun openWebBrowser(basicSearchUrl: String): WebDriver {
        val webDriver: WebDriver = createWebDriver()
        val webDriverWait: WebDriverWait = createWebDriverWait(webDriver)

        webDriver.get(basicSearchUrl) // 브라우저에서 url로 이동한다.
        webDriverWait.until<WebElement>(
            ExpectedConditions.presenceOfElementLocated(
                By.className(
                    gyeonggiEducationalElectronicLibrary.stayClassName
                )
            )
        )

        return webDriver
    }

    fun getMoreViewLinks(document: Document, searchUrl: String?): List<String> {
        val moreViewLinkList: MutableList<String> = ArrayList()
        val moreView: MoreView = gyeonggiEducationalElectronicLibraryIsMoreView(document)

        if (moreView.isMoreView) {
            val moreViewUrl: String =
                gyeonggiEducationalElectronicLibrary.moreViewSearchUrlCreate(searchUrl, moreView.totalCount)
            moreViewLinkList.add(moreViewUrl)
        }
        return moreViewLinkList
    }

    fun getBookSearchTotalCount(document: Document): String {
        return document.select("b#book_totalDataCount").text()
    }


    fun getBookItemDtos(document: Document): List<LibrarySearchServiceResponse.BookDto> {
        val select = document.select("div.row")

        //  제목 , 저자 , 출판사  , 출판날짜  , 대출가능여부  , 책 이미지링크 ,
//    String bookImageLink, String title, String author, String publisher, String publicationDate,
        val bookDtoList: MutableList<LibrarySearchServiceResponse.BookDto> =
            ArrayList<LibrarySearchServiceResponse.BookDto>()


        for (element in select) {
            val bookTitle = element.select("a.name.goDetail").text()
            val bookImageLink = element.select("a.goDetail img").attr("src")

            val bookDetailInfo =
                element.select("div p").text().split("│".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val bookDetailInfoList = Arrays.stream(bookDetailInfo)
                .map { bookInfo: String -> this.extractBookDetails(bookInfo) }.toList()

            // 저자, 출판사, 출판일 ,도서관 대출가능여부 (대출 ,예약 두개있을수도 있음) ,자료유형 전자책
            val bookDto: LibrarySearchServiceResponse.BookDto = LibrarySearchServiceResponse.BookDto.of(
                bookImageLink = bookImageLink,
                title = bookTitle,
                author = bookDetailInfoList[0],
                publisher = bookDetailInfoList[1],
                publicationDate = bookDetailInfoList[2],
                loanAvailability = bookDetailInfoList[3]
            )

            bookDtoList.add(bookDto)
            //            인덱스 순서
        }

        return bookDtoList
    }


    private fun extractBookDetails(bookInfo: String): String {
        val index = bookInfo.indexOf(":")
        val subStringed = bookInfo.substring(index + 1)
        val index1 = subStringed.indexOf(":") // 경기교육통합도서관대출 가능 여부 :  <- 문자 또 제거
        return subStringed.substring(index1 + 1)
    }


    private fun gyeonggiEducationalElectronicLibraryIsMoreView(document: Document): MoreView {
        val totalCount = document.select("b#book_totalDataCount").text()
        return MoreView.create(totalCount.toInt())
    }
}
