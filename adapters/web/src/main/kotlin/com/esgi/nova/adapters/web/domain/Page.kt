package com.esgi.nova.adapters.web.domain

import kotlin.math.min

class Page<T>(val pageSize: Int, startPage: Int, elements: Collection<T>) :
    ArrayList<T>(elements) {

    private var _currentPage = startPage
    val currentPage get() = _currentPage
    val lastPage get() = this.size / pageSize
    val hasNext get() = _currentPage < lastPage
    val hasPrevious get() = _currentPage > 0
    val currentPageStartElementIndex get() = _currentPage * pageSize
    val currentPageEndElementIndex get() = min(currentPageStartElementIndex + pageSize - 1, this.size - 1)
    val currentPageElements get() = this.subList(currentPageStartElementIndex, currentPageEndElementIndex)

    fun moveTo(idx: Int): Page<T>? {
        if (idx in 0..lastPage) {
            _currentPage = idx
            return this
        }
        return null
    }

    fun moveNext(): Page<T>? {
        if (hasNext) {
            _currentPage++
            return this
        }
        return null
    }

    fun movePrevious(): Page<T>? {
        if (hasPrevious) {
            _currentPage--
            return this
        }
        return null
    }
}