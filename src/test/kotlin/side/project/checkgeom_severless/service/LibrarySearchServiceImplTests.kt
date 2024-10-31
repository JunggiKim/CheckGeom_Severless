package side.project.checkgeom_severless.service

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
abstract class LibrarySearchServiceImplTests {

    @Autowired
    private lateinit var librarySearchServiceImpl: LibrarySearchServiceImpl

    @Test
    fun test1() {
        librarySearchServiceImpl.test("abc");
    }
}