package side.project.checkgeom_severless.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Hooks
import side.project.checkgeom_severless.repository.LibrarySearchRepository
import side.project.checkgeom_severless.repository.response.LibraryRepositoryResponse
import side.project.checkgeom_severless.service.response.LibrarySearchServiceResponse
import java.util.function.Function
import java.util.function.Supplier
import java.util.stream.Stream

@Service
class LibrarySearchServiceImpl(
    private val librarySearchRepository: LibrarySearchRepository

) {

    private val log: Logger = LoggerFactory.getLogger(javaClass)


    // 소장형이든 구독형 최대 첫화면에서는 6개만 보여준다.
    // 그래서 숫자 값을 찾아서 만약 총값이 6개이상이라면 더보기칸을 눌러서 들어간다
    // gyeonggiDoCyberLibrarySearch 의 경우 가져오는게 api로 변경이 있을 수 있기에 AOP로 따로 뺴두도록하자 웹드라이버의 기능을 빼 둘수가있나 한번 알아보자
//    override fun gyeonggiDoCyberLibrarySearch(searchKeyword: String): LibrarySearchServiceResponse {


//        val gyeonggiDoCyberLibrarySearch = librarySearchRepository.gyeonggiDoCyberLibrarySearch(searchKeyword);

//        val bookDtoList : List<LibrarySearchServiceResponse.BookDto>  =
//        val map = gyeonggiDoCyberLibrarySearch.map { response -> LibrarySearchServiceResponse.BookDto.of(response) }
//            map.
        //        final Element htmlBody = gyeonggiDoCyberLibraryReader.getGyeonggiDoCyberLibraryHtmlBody(searchKeyword);
//
//        final List<String> moreViewLink = gyeonggiDoCyberLibraryReader.getMoreViewLinks(searchKeyword, htmlBody);
//
//        final List<LibrarySearchServiceResponse.BookDto> bookDtoList = gyeonggiDoCyberLibraryReader.searchBookList(htmlBody);
//
//        final int bookSearchTotalCount = gyeonggiDoCyberLibraryReader.getBookSearchTotalCount(htmlBody);

//        return LibrarySearchServiceResponse.of(bookDtoList, bookSearchTotalCount, moreViewLink, LibraryType.GYEONGGIDO_CYBER.getKoreanText());
//    }


    // 경기도사이버도서관 더 보기에 맞는 모든 북을 가져오는 로직
//    private fun moreViewBook(moreViewList: List<GyeonggiDoCyberLibraryMoreViewType>, basicSearchUrl: String) {
//        for (moreViewBook in moreViewList) {
////            if (moreViewBook.isNotMoreView()) continue;
////            String moreViewUrl = GyeonggiDoCyberLibrary.moreViewSearchUrlCreate(basicSearchUrl, moreViewBook);
//            //타입에 맞는 URL
//            val moreViewTag = "searchResultList"
//            //            WebDriver moreViewWebDriver = openWebBrowser(moreViewUrl, moreViewTag);
////            Document document = Jsoup.parse(moreViewWebDriver.getPageSource());
////            gyeonggiDoCyberLibraryReader.gyeonggiDoCyberLibraryGetBookInfo(document);
//            // 더보기 링크 에 맞는 브라우저 오픈
//        }
//    }
//
//    // 경기도사이버도서관 더보기에 맞는 유형에 대한 로직
//    private fun getSearchBookList(htmlPage: Document) {
//        val collectibleBook: Elements = htmlPage.select("[data-type=EB]") // 소장형
//        val subscriptionBook: Elements = htmlPage.select("[data-type=SUBS]") // 구독형
//        val audioBook: Elements = htmlPage.select("[data-type=AB]") // 오디오북
//    }
//
//
//    // 기본 검색한 책 목록과 책 총 결과 수 와 검색결과 모두 볼수있는 더보기링크 까지 보내주자
//    override fun gyeonggiEducationalElectronicLibrarySearch(keyword: String): LibrarySearchServiceResponse {
//        val searchUrl: String = gyeonggiEducationalElectronicLibrary.basicSearchUrlCreate(keyword)
//
//        val document: Document =
//            gyeonggiEducationalElectronicLibraryReader.getGyeonggiEducationalElectronicLibraryHtml(searchUrl)
//
//        val moreViewLinkList: List<String> =
//            gyeonggiEducationalElectronicLibraryReader.getMoreViewLinks(document, searchUrl)
//
//        val bookItemDtos: List<LibrarySearchServiceResponse.BookDto> =
//            gyeonggiEducationalElectronicLibraryReader.getBookItemDtos(document)
//
//        val totalCount: String = gyeonggiEducationalElectronicLibraryReader.getBookSearchTotalCount(document)
//
//        return LibrarySearchServiceResponse.of(
//            bookItemDtos,
//            totalCount.toInt(),
//            moreViewLinkList,
//            LibraryType.GYEONGGI_EDUCATIONAL_ELECTRONIC.getKoreanText()
//        )
//    }
//
//
//    override fun smallBusinessLibrarySearch(searchKeyword: String): LibrarySearchServiceResponse {
//        // TODO 기본 URL 불러오기까지는 가능하게 해놨다.
//        //   이제 값을 가져오기만 하면 될듯 하다.
//
//        val basicUrl: String = SmallBusinessLibrary.basicUrlCreate(searchKeyword)
//
//        val htmlBody: Element = smallBusinessLibraryReader.getHtmlBody(basicUrl)
//
//        val bookDtoList: List<LibrarySearchServiceResponse.BookDto> = smallBusinessLibraryReader.getBooks(htmlBody)
//
//        val totalCount: Int = smallBusinessLibraryReader.getTotalCount(htmlBody)
//
//        val moreViewUrlList: List<String> = smallBusinessLibraryReader.getMoreViewLinks(searchKeyword, totalCount)
//
//
//        return LibrarySearchServiceResponse.of(
//            bookDtoList,
//            totalCount,
//            moreViewUrlList,
//            LibraryType.SMALL_BUSINESS.getKoreanText()
//        )
//    }
//
//
//    override fun allLibraryAsyncSearch(searchKeyword: String): AllLibraryServiceResponse {
//        val gyeonggiDoCyberResponse: CompletableFuture<LibrarySearchServiceResponse> =
//            CompletableFuture.supplyAsync<LibrarySearchServiceResponse>(
//                Supplier<LibrarySearchServiceResponse> { gyeonggiDoCyberLibrarySearch(searchKeyword) })
//        val gyeonggiEducationalElectronicResponse: CompletableFuture<LibrarySearchServiceResponse> =
//            CompletableFuture.supplyAsync<LibrarySearchServiceResponse>(
//                Supplier<LibrarySearchServiceResponse> { gyeonggiEducationalElectronicLibrarySearch(searchKeyword) })
//        val smallBusinessResponse: CompletableFuture<LibrarySearchServiceResponse> =
//            CompletableFuture.supplyAsync<LibrarySearchServiceResponse>(
//                Supplier<LibrarySearchServiceResponse> { smallBusinessLibrarySearch(searchKeyword) })
//
//        val resultList: List<LibrarySearchServiceResponse> = CompletableFuture.allOf(
//            gyeonggiDoCyberResponse,
//            gyeonggiEducationalElectronicResponse,
//            smallBusinessResponse
//        )
//            .thenApply<List<LibrarySearchServiceResponse>>(
//                Function<Void, List<LibrarySearchServiceResponse>> { voidResult: Void? ->
//                    Stream.of<CompletableFuture<LibrarySearchServiceResponse>>(
//                        gyeonggiDoCyberResponse,
//                        gyeonggiEducationalElectronicResponse,
//                        smallBusinessResponse
//                    )
//                        .map<LibrarySearchServiceResponse>(Function<CompletableFuture<LibrarySearchServiceResponse?>, LibrarySearchServiceResponse> { obj: CompletableFuture<LibrarySearchServiceResponse?> -> obj.join() })
//                        .toList()
//                }
//            ).join()
//
//        return AllLibraryServiceResponse.of(resultList, LibraryType.ALL.getKoreanText())
//    }  public AllLibraryServiceResponse allLibraryVirtualThreadAsyncSearch(String searchKeyword) throws ExecutionException, InterruptedException {
    //
    //     Future<LibrarySearchServiceResponse> gyeonggiDoCyberResponse =
    //             virtualThreadExecutor.submit(() -> gyeonggiDoCyberLibrarySearch(searchKeyword, searchType));
    //     Future<LibrarySearchServiceResponse> gyeonggiEducationalElectronicResponse =
    //             virtualThreadExecutor.submit(() -> gyeonggiEducationalElectronicLibrarySearch(searchKeyword));
    //     Future<LibrarySearchServiceResponse> smallBusinessResponse =
    //             virtualThreadExecutor.submit(() -> smallBusinessLibrarySearch(searchKeyword));
    //     List<LibrarySearchServiceResponse> resultList = List.of(
    //                     gyeonggiDoCyberResponse.get(),
    //                     gyeonggiEducationalElectronicResponse.get(),
    //                     smallBusinessResponse.get()
    //             );
    //
    //     return AllLibraryServiceResponse.of(resultList, LibraryType.ALL.getKoreanText());
    // }
}
