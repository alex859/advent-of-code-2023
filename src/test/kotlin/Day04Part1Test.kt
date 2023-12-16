import kotlin.test.assertEquals
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory


class Day04Part1Test {
    @Test
    fun `acceptance test`() {
        val input = """
            Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
            Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
            Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
            Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
            Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
            Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
        """.trimIndent().lines()

        val result = Day04.part1(input)

        assertEquals(13, result)
    }
    
    @Test
    fun `reads winning numbers`() {
        val input = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53"

        assertEquals(setOf(41, 48, 83, 86, 17), input.numbers.winning)
    }

    @Test
    fun `reads numbers I have`() {
        val input = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53"

        assertEquals(setOf(83, 86, 6, 31, 17, 9, 48, 53), input.numbers.mine)
    }

    @Test
    fun `checks the winning numbers I have`() {
        val input = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53"

        assertEquals(setOf(48, 83, 17,  86), input.winningNumbersIHave)
    }

    @TestFactory
    fun `checks the score`() = listOf(
        "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53" to 8,
        "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19" to 2,
        "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1" to 2,
        "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83" to 1,
        "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36" to 0,
        "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11" to 0
    ).map { (card, score) ->
        dynamicTest("$card -> $score") {
            assertEquals(score, card.score)
        }
    }
}