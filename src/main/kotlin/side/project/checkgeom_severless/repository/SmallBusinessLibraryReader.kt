package side.project.checkgeom_severless.repository

import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.springframework.stereotype.Component
import side.project.checkgeom_severless.domain.MoreView
import side.project.checkgeom_severless.repository.library.smallbusinesslibrary.SmallBusinessLibrary
import side.project.checkgeom_severless.service.response.LibrarySearchServiceResponse
import java.util.regex.Pattern

import side.project.checkgeom_severless.util.WebDriverUtil.createWebDriver;
import side.project.checkgeom_severless.util.WebDriverUtil.createWebDriverWait;
@Component
class SmallBusinessLibraryReader {
    fun getHtmlBody(basicUrl: String): Element {
        val webDriver = openWebBrowser(basicUrl, "contents")
        val htmlBody = Jsoup.parse(webDriver.pageSource).body()
        webDriver.quit()
        return htmlBody
    }


    private fun openWebBrowser(basicSearchUrl: String, stayClassName: String): WebDriver {
        val webDriver: WebDriver = createWebDriver()
        val webDriverWait: WebDriverWait = createWebDriverWait(webDriver)

        webDriver[basicSearchUrl] // 브라우저에서 url로 이동한다.
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.className(stayClassName)))

        return webDriver
    }


    fun getBooks(htmlBody: Element): List<LibrarySearchServiceResponse.BookDto> {
        val selectBookList = htmlBody.select("ul.book_resultList > li")

        return selectBookList.stream().map { mapBookDto(it)}
            .toList()
    }

    private fun mapBookDto(element: Element): LibrarySearchServiceResponse.BookDto {
        val bookImageLink = element.select("a.scale img").attr("src")
        val bookTitle = element.select("li.tit a").text()
        val bookDetailInfo = element.select("li.writer").toString()
        val pattern = Pattern.compile("<li class=\"writer\">(.*?)<span>(.*?)</span>(\\d{4}-\\d{2}-\\d{2})</li>")
        val matcher = pattern.matcher(bookDetailInfo)
        if (matcher.find()) {
            val author = matcher.group(1) // 저자
            val publisher = matcher.group(2) // 출판사
            val publicationDate = matcher.group(3) // 출판 날짜

            return LibrarySearchServiceResponse.BookDto.of(
                bookImageLink = bookImageLink,
                title = bookTitle,
                author = author,
                publisher = publisher,
                publicationDate = publicationDate,
                loanAvailability = "대출 가능"
            )
        }
        throw RuntimeException("$element 의 정상적인 변환을 하지 못했습니다.")
    }

    fun getTotalCount(htmlBody: Element): Int {
        val totalCount = htmlBody.select("div.book_resultTxt p").toString().replace("[^0-9]".toRegex(), "")
        val stringTotalCount = if (totalCount.isBlank()) "0" else totalCount
        return stringTotalCount.toInt()
    }


    fun getMoreViewLinks(searchKeyword: String?, totalCount: Int): List<String> {
        val moreView: MoreView = MoreView.create(totalCount)

        val moreViewUrlList: MutableList<String> = ArrayList()

        if (moreView.isMoreView) {
            val moreViewUrl: String = SmallBusinessLibrary.moreViewUrlCreate(searchKeyword, totalCount)
            moreViewUrlList.add(moreViewUrl)
        }

        return moreViewUrlList
    }
}
