import kotlin.test.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class Day08Part2Test {
    @Test
    fun `should count number of steps to get from AAA to ZZZ`() {
        val input = """
            LR

            11A = (11B, XXX)
            11B = (XXX, 11Z)
            11Z = (11B, XXX)
            22A = (22B, XXX)
            22B = (22C, 22C)
            22C = (22Z, 22Z)
            22Z = (22B, 22B)
            XXX = (XXX, XXX)
        """.trimIndent()

        assertEquals(6, input.countStepsGhost())
    }

    @TestFactory
    fun `calculate the lcm`() = listOf(
        listOf(5L, 7L) to 35L,
        listOf(12L, 15L, 75L) to 300L,
        listOf(123L, 151L, 755L) to 92865L,
    ).map { (input, expectedOutput) ->
        dynamicTest("$input -> $expectedOutput") {
            assertEquals(expectedOutput, lcm(input))
        }
    }
}