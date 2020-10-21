package domain

import com.esgi.nova.adapters.web.domain.pagination.Page
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class PageTests {

    private val _testPage get() = Page(5, 0, (0..18).toList())

    @Test
    fun currentPageElementsShouldReturnCurrentPageElements() =
        assertArrayEquals(_testPage.currentPageElements.toIntArray(), (0..4).toList().toIntArray())

    @Test
    fun hasNextShouldReturnFalse() = assertFalse(_testPage.moveTo(_testPage.lastPage)?.hasNext ?: false)

    @Test
    fun hasNextShouldReturnTrue() = assertTrue(_testPage.hasNext)

    @Test
    fun hasPreviousShouldReturnFalse() = assertFalse(_testPage.hasPrevious)

    @Test
    fun hasPreviousShouldReturnTrue() = assertTrue(_testPage.moveNext()?.hasPrevious ?: false)

    @Test
    fun moveToShouldReturnNullOnBadIdx() = assertNull(_testPage.moveTo(_testPage.lastPage + 1))

    @Test
    fun moveToShouldReturnInstanceOnValidIdx() = assertNotNull(_testPage.moveTo(_testPage.lastPage))

    @Test
    fun moveNextShouldReturnNullOnEnd() = assertNull(_testPage.moveTo(_testPage.lastPage)?.moveNext())

    @Test
    fun movePreviousShouldReturnNullOnStart() = assertNull(_testPage.movePrevious())

    @Test
    fun currentPageStartElementIdxShouldReturnValidIdx() =
        assertEquals(5, _testPage.moveNext()?.currentPageStartElementIndex)

    @Test
    fun currentPageEndElementIdxShouldReturnValidIdx() =
        assertEquals(_testPage.pageSize - 1, _testPage.currentPageEndElementIndex)

    @Test
    fun currentPageShouldReturnValidIdx() {
        val currentIdx = _testPage.currentPage
        val nextIdx = _testPage.moveNext()?.currentPage
        assertEquals(currentIdx + 1, nextIdx)

    }
}
