package domain

import com.esgi.nova.adapters.web.domain.pagination.Link
import com.esgi.nova.adapters.web.domain.pagination.Relation
import io.netty.handler.codec.http.HttpMethod
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LinkTests {

    private val _expectedUrl = "http://test.test"
    private val _testLink get() = Link(Relation.CURRENT, _expectedUrl, HttpMethod.POST)

    @Test
    fun shouldReturnLowerCaseRelation() = assertEquals("current", _testLink.rel)

    @Test
    fun shouldReturnLowerCaseMethod() = assertEquals("post", _testLink.method)

    @Test
    fun shouldReturnSameUrl() = assertEquals(_expectedUrl, _testLink.href)
}