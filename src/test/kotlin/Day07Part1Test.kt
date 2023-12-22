import HandType.*
import kotlin.test.assertEquals
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class Day07Part1Test {
    @Test
    fun `acceptance test`() {
        val input = """
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483
        """.trimIndent().lines()

        val result = Day07.part1(input)

        assertEquals(6440, result)
    }

    @Test
    fun `reads hands`() {
        val input = """
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483
        """.trimIndent().lines()

        val result = input.toHandsAndBids()

        assertEquals(
            listOf(
                Hand("32T3K") to 765,
                Hand("T55J5") to 684,
                Hand("KK677") to 28,
                Hand("KTJJT") to 220,
                Hand("QQQJA") to 483,
            ).toSet(),
            result.toSet()
        )
    }

    @TestFactory
    fun `type of a hand of camel cards`() = listOf(
        Hand("22222") to FIVE_OF_A_KIND,
        Hand("22223") to FOUR_OF_A_KIND,
        Hand("222A3") to THREE_OF_A_KIND,
        Hand("222AA") to FULL_HOUSE,
        Hand("22KAA") to TWO_PAIRS,
        Hand("22K3A") to ONE_PAIR,
        Hand("23K45") to HIGH_CARD,
    ).map { (hand, expectedType) ->
        dynamicTest("$hand -> $expectedType") {
            assertEquals(expectedType, hand.type())
        }
    }

    @TestFactory
    fun `sort by strength`() = listOf(
        listOf(Hand("22222"), Hand("22233")) to listOf(Hand("22233"), Hand("22222")),
        listOf(Hand("22J33"), Hand("3456K")) to listOf(Hand("3456K"), Hand("22J33")),
        listOf(Hand("44J33"), Hand("33JKK")) to listOf(Hand("33JKK"), Hand("44J33")),
        listOf(Hand("44K33"), Hand("44J55")) to listOf(Hand("44J55"), Hand("44K33")),
    ).map { (unordered, ordered) ->
        dynamicTest("$unordered -> $ordered") {
            assertEquals(ordered, unordered.sortByStrength())
        }
    }
}

