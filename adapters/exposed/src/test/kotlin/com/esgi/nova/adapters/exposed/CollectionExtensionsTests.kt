package extensions

import org.junit.jupiter.api.Test

class CollectionExtensionsTests {

    @Test
    fun insideTypeShouldReturnValidType() {
        val testList = listOf<String>("TOTO")
        testList.getInsideType()
    }
}