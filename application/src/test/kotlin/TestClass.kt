import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.axonframework.queryhandling.QueryGateway
import org.junit.jupiter.api.Test
import org.springframework.util.Assert


class TestClass {

    @Test
    fun testTest() {
        Assert.isTrue(true)
        val mock = mock<QueryGateway> {
            on { toString() } doReturn "text"
        }
    }
}