import kotlin.test.assertEquals
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class Day09Part2Test {
    @Test
    fun `acceptance test`() {
        val input = """
            10 13 16 21 30 45
        """.trimIndent().lines()

        assertEquals(5, Day09.part2(input))
    }
}