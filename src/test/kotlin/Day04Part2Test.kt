import kotlin.test.assertEquals
import kotlin.test.assertTrue
import org.junit.jupiter.api.Test

class Day04Part2Test {
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

        val result = Day04.part2(input)

        assertEquals(30, result)
    }

    @Test
    fun `calculates the copy of cards that a card wins`() {
        val input = """
            Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
            Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
            Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
            Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
            Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
            Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
        """.trimIndent().lines()

        val result = input.winningCardsFor("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53")

        assertEquals(listOf(
            "Card 2",
            "Card 3",
            "Card 4",
            "Card 5",
        ), result.map { it.id })
    }

    @Test
    fun `last card does not win any cards`() {
        val input = """
            Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
        """.trimIndent().lines()

        val result = input.winningCardsFor("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53")

        assertEquals(emptyList(), result)
    }

    @Test
    fun `calculates all the winning cards`() {
        val input = """
            Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
            Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
            Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
            Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
            Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
            Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
        """.trimIndent().lines()

        val result = allWinningCardsFor(input, "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53")

        val expected = listOf(
            "Card 2",
            "Card 3",
            "Card 4",
            "Card 5",

            "Card 3",
            "Card 4",
            "Card 4",
            "Card 5",

            "Card 4",
            "Card 5",
            "Card 5",
            "Card 5",
            "Card 5",

            "Card 5",
        )
        assertEquals(expected.size, result.map { it.id }.size)
        assertTrue(expected.containsAll(result.map { it.id }))
    }
}

private val Card.id: String get()  {
    val (id, numbers) = split(":")
    return id
}
