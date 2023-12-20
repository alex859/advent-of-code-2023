import kotlin.test.assertEquals
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class Day06Part1Test {
    @Test
    fun `acceptance test`() {
        val input = """
            Time:      7  15   30
            Distance:  9  40  200
        """.trimIndent().lines()

        val result = Day06.part1(input)

        assertEquals(288, result)
    }

    @Test
    fun `read races`() {
        val input = """
            Time:      7  15   30
            Distance:  9  40  200
        """.trimIndent().lines()

        val result = input.toRaces()

        assertEquals(
            setOf(
                7L to 9L,
                15L to 40L,
                30L to 200L
            ), result.toSet()
        )
    }

    @TestFactory
    fun `calculates how many ways I can win`() = listOf(
        (7L to 9L) to 4,
        (15L to 40L) to 8,
        (30L to 200L) to 9
    ).map { (race, expected) ->
        dynamicTest("$race -> $expected") {
            assertEquals(expected, race.waysToWin)
        }
    }
}