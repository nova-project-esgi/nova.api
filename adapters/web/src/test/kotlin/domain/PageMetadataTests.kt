//package domain
//
//import com.esgi.nova.adapters.web.domain.pagination.PageMetadata
//import com.esgi.nova.adapters.web.domain.pagination.Relation
//import com.esgi.nova.ports.provided.IPage
//import com.nhaarman.mockitokotlin2.whenever
//import io.ktor.http.*
//import org.junit.jupiter.api.Assertions.*
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.params.ParameterizedTest
//import org.junit.jupiter.params.provider.Arguments
//import org.junit.jupiter.params.provider.MethodSource
//import org.mockito.Mockito
//import java.lang.Integer.parseInt
//import java.util.stream.Stream
//
//abstract class Foo()
//
//class PageMetadataTests {
//
//    private companion object {
//        @JvmStatic
//        fun shouldReturnValidLink(): Stream<Arguments> = Stream.of(
//            Arguments.of(Relation.NEXT, 1),
//            Arguments.of(Relation.PREVIOUS, -1),
//            Arguments.of(Relation.CURRENT, 0)
//        )
//    }
//
//    private val _testUrl = "http://localhost:8080/test/url"
//    private val _testList get() = (0..18).toList()
//    private val _foo: Foo get(){
//        return Mockito.mock(Foo::class.java)
//    }
//    private val _testPage: IPage<Int>
//        get() {
//            val mock: IPage<*> = Mockito.mock(IPage::class.java)
//            whenever(mock.currentPage).thenReturn(1)
//            whenever(mock.hasPrevious).thenReturn(true)
//            whenever(mock.hasNext).thenReturn(false)
//            whenever(mock.pageSize).thenReturn(4)
//            return mock as IPage<Int>
//        }
//    private val _testPageMetadata get() = PageMetadata(_testPage, _testUrl)
//
//    @Test
//    fun shouldReturnCurrentPageInValues(){
//        val test = _foo
//        assertArrayEquals(_testPage.toIntArray(), _testPageMetadata.values.toIntArray())
//    }
//
//
//    @ParameterizedTest
//    @MethodSource
//    fun shouldReturnValidPageAndSizeLinks(relation: Relation, shiftCnt: Int) {
//        val link = _testPageMetadata.links.find { link -> link.rel == relation.name.toLowerCase() }
//        assertNotNull(link)
//        link?.let { it ->
//            val linkPage = URLBuilder(it.href).parameters["page"]
//            val linkSize = URLBuilder(it.href).parameters["size"]
//            assertEquals(_testPage.currentPage + shiftCnt, parseInt(linkPage))
//            assertEquals(_testPage.pageSize, parseInt(linkSize))
//        }
//    }
//
//    @Test
//    fun shouldReturnValidPaginationLinksMethod() {
//        val relations = listOf(Relation.CURRENT, Relation.PREVIOUS, Relation.NEXT).map { rel -> rel.name.toLowerCase() }
//        _testPageMetadata.links.forEach { link ->
//            if (relations.contains(link.rel)) assertEquals(
//                HttpMethod.Get.value.toLowerCase(),
//                link.method
//            )
//        }
//    }
//
//
//}