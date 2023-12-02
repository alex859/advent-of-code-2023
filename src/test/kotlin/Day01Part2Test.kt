import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class Day01Part2Test {
    @Test
    fun `acceptance test`() {
        val input = """
            two1nine
            eightwothree
            abcone2threexyz
            xtwone3four
            4nineeightseven2
            zoneight234
            7pqrstsixteen
        """.trimIndent().lines()

        assertEquals(281, Day01.part2(input))
    }

    @Test
    fun `convert digits spelled with letters - 1`() {
        val input = "one"

        assertEquals("1", input.convertDigitsSpelledWithLetters())
    }

    @Test
    fun `picks the first occurring digit`() {
        val input = "eightwo3"

        assertEquals("823", input.convertDigitsSpelledWithLetters())
    }

    @Test
    fun `digits are not converted`() {
        val input = "onetwo3four5sixseveneightnine"

        assertEquals("123456789", input.convertDigitsSpelledWithLetters())
    }

    @TestFactory
    fun checkEachLine() = mapOf(
        "two1nine" to 29,
        "eightwothree" to 83,
        "abcone2threexyz" to 13,
        "xtwone3four" to 24,
        "4nineeightseven2" to 42,
        "zoneight234nineight" to 18,
        "7pqrstsixteen" to 76,
    ).map { (input, expected) ->
        dynamicTest("$input -> $expected") {
            println(input.convertDigitsSpelledWithLetters())
            assertEquals(expected, input.convertDigitsSpelledWithLetters().firstAndLastDigits())
        }
    }
}