package com.esgi.nova.domain.domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class PageTests {

    private val _testPage get() = Page(5, 0, (0..18).toList())

    @Test
    fun currentPageElementsShouldReturnCurrentPageElements() =
        Assertions.assertArrayEquals(_testPage.currentPageElements.toIntArray(), (0..3).toList().toIntArray())

    @Test
    fun hasNextShouldReturnFalse() = Assertions.assertFalse(_testPage.moveTo(_testPage.lastPage)?.hasNext ?: false)

    @Test
    fun hasNextShouldReturnTrue() = assertTrue(_testPage.hasNext)

    @Test
    fun hasPreviousShouldReturnFalse() = assertFalse(_testPage.hasPrevious)

    @Test
    fun hasPreviousShouldReturnTrue() = Assertions.assertTrue(_testPage.moveNext()?.hasPrevious ?: false)

    @Test
    fun moveToShouldReturnNullOnBadIdx() = Assertions.assertNull(_testPage.moveTo(_testPage.lastPage + 1))

    @Test
    fun moveToShouldReturnInstanceOnValidIdx() = Assertions.assertNotNull(_testPage.moveTo(_testPage.lastPage))

    @Test
    fun moveNextShouldReturnNullOnEnd() = Assertions.assertNull(_testPage.moveTo(_testPage.lastPage)?.moveNext())

    @Test
    fun movePreviousShouldReturnNullOnStart() = Assertions.assertNull(_testPage.movePrevious())

    @Test
    fun currentPageStartElementIdxShouldReturnValidIdx() =
        Assertions.assertEquals(5, _testPage.moveNext()?.currentPageStartElementIndex)

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