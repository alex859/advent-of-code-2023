import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day01Part1Test {
    @Test
    fun `acceptance test`() {
        val input = listOf(
            "1abc2",
            "pqr3stu8vwx",
            "a1b2c3d4e5f",
            "treb7uchet"
        )

        assertEquals(142, Day01.part1(input))
    }

    @Test
    fun `reads first and last digit from a string`() {
        val input = "1abc2"

        assertEquals(12, input.firstAndLastDigits())
    }

    @Test
    fun `reads first and last digit when there are more than 2 digits`() {
        val input = "a1b2c3d4e5f"

        assertEquals(15, input.firstAndLastDigits())
    }

    @Test
    fun `reads first digit twice when it is the only one`() {
        val input = "treb7uchet"

        assertEquals(77, input.firstAndLastDigits())
    }
}
