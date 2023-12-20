import kotlin.test.assertEquals
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class Day06Part2Test {
    @Test
    fun `acceptance test`() {
        val input = """
            Time:      7  15   30
            Distance:  9  40  200
        """.trimIndent().lines()

        val result = Day06.part2(input)

        assertEquals(71503, result)
    }

    @Test
    fun `read races`() {
        val input = """
            Time:      7  15   30
            Distance:  9  40  200
        """.trimIndent().lines()

        val result = input.toRace()

        assertEquals(71530L to 940200L, result)
    }
}