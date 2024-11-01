package side.project.checkgeom_severless.service

import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class LibrarySearchServiceImplTests {

    @Autowired
    private lateinit var librarySearchServiceImpl: LibrarySearchServiceImpl

    @Test
    @DisplayName("경기도_사이버_도서관_검색")
    fun gyeonggiDoCyberLibrarySearch() {
        val gyeonggiDoCyberLibrarySearch = librarySearchServiceImpl.gyeonggiDoCyberLibrarySearch("abc")
        gyeonggiDoCyberLibrarySearch.bookDtoList.forEach{ println(it) }
        Assertions.assertThat(gyeonggiDoCyberLibrarySearch.bookDtoList).isNotNull
    }

    @Test
    @DisplayName("경기_교육_전자_도서관_검색")
    fun gyeonggiEducationalElectronicLibrarySearch() {
        val gyeonggiEducationalElectronicLibrarySearch =
            librarySearchServiceImpl.gyeonggiEducationalElectronicLibrarySearch("abc")
        gyeonggiEducationalElectronicLibrarySearch.bookDtoList.forEach{ println(it) }
        Assertions.assertThat(gyeonggiEducationalElectronicLibrarySearch.bookDtoList).isNotNull
    }

    @Test
    @DisplayName("소상공인전자도서관_검색")
    fun smallBusinessLibrarySearch() {
        val smallBusinessLibrarySearch =
            librarySearchServiceImpl.smallBusinessLibrarySearch("abc")
        smallBusinessLibrarySearch.bookDtoList.forEach{ println(it) }
        Assertions.assertThat(smallBusinessLibrarySearch.bookDtoList).isNotNull
    }

    @Test
    @DisplayName("전체도서관_검색")
     fun allLibraryAsyncSearch(): Unit = runBlocking {

        val allLibraryAsyncSearch = librarySearchServiceImpl.allLibraryAsyncSearch("abc")

        allLibraryAsyncSearch.librarySearchServiceResponseList.forEach{ println(it) }
        Assertions.assertThat(allLibraryAsyncSearch.librarySearchServiceResponseList).isNotNull
    }

}