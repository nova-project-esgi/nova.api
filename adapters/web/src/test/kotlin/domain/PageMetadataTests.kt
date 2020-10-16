package domain

import com.esgi.nova.adapters.web.domain.Page
import com.esgi.nova.adapters.web.domain.PageMetadata
import com.esgi.nova.adapters.web.domain.Relation
import io.ktor.http.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.lang.Integer.parseInt
import java.util.stream.Stream

class PageMetadataTests {

    private companion object {
        @JvmStatic
        fun shouldReturnValidLink(): Stream<Arguments> = Stream.of(
            Arguments.of(Relation.NEXT, 1),
            Arguments.of(Relation.PREVIOUS, -1),
            Arguments.of(Relation.CURRENT, 0)
        )
    }

    private val _testUrl = "http://localhost:8080/test/url"
    private val _testList get() = (0..18).toList()
    private val _testPage get() = Page(5, 1, _testList)
    private val _testPageMetadata get() = PageMetadata(_testPage, _testUrl)

    @Test
    fun shouldReturnCurrentPageInValues() =
        assertArrayEquals(_testPage.currentPageElements.toIntArray(), _testPageMetadata.values.toIntArray())


    @ParameterizedTest
    @MethodSource
    fun shouldReturnValidPageAndSizeLinks(relation: Relation, shiftCnt: Int) {
        val link = _testPageMetadata.links.find { link -> link.rel == relation.name.toLowerCase() }
        assertNotNull(link)
        link?.let { it ->
            val linkPage = URLBuilder(it.href).parameters["page"]
            val linkSize = URLBuilder(it.href).parameters["size"]
            assertEquals(_testPage.currentPage + shiftCnt, parseInt(linkPage))
            assertEquals(_testPage.pageSize, parseInt(linkSize))
        }
    }

    @Test
    fun shouldReturnValidPaginationLinksMethod() {
        val relations = listOf(Relation.CURRENT, Relation.PREVIOUS, Relation.NEXT).map { rel -> rel.name.toLowerCase() }
        _testPageMetadata.links.forEach { link ->
            if (relations.contains(link.rel)) assertEquals(
                HttpMethod.Get.value.toLowerCase(),
                link.method
            )
        }
    }


}