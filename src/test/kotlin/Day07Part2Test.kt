import HandType.*
import kotlin.test.assertEquals
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class Day07Part2Test {
    @Test
    fun `acceptance test`() {
        val input = """
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483
        """.trimIndent().lines()

        val result = Day07.part2(input)

        assertEquals(5905, result)
    }

    @TestFactory
    fun `type of a hand of camel cards`() = listOf(
        Hand("22222") to FIVE_OF_A_KIND,
        Hand("2222J") to FIVE_OF_A_KIND,
        Hand("222AJ") to FOUR_OF_A_KIND,
        Hand("222AA") to FULL_HOUSE,
        Hand("22JAA") to FULL_HOUSE,
        Hand("22K3A") to ONE_PAIR,
        Hand("24J3A") to ONE_PAIR,
        Hand("22J3A") to THREE_OF_A_KIND,
        Hand("22J33") to FULL_HOUSE,
        Hand("22J23") to FOUR_OF_A_KIND,
        Hand("22JJ3") to FOUR_OF_A_KIND,
        Hand("24JJA") to THREE_OF_A_KIND,
        Hand("24JJJ") to FOUR_OF_A_KIND,
        Hand("2JJJJ") to FIVE_OF_A_KIND,
        Hand("JJJ3A") to FOUR_OF_A_KIND,
        Hand("JJJJJ") to FIVE_OF_A_KIND,
        Hand("JJJJK") to FIVE_OF_A_KIND,
        Hand("23K45") to HIGH_CARD,
    ).map { (hand, expectedType) ->
        dynamicTest("$hand -> $expectedType") {
            assertEquals(expectedType, hand.typeWithJoker())
        }
    }

    @TestFactory
    fun `sort by strength`() = listOf(
        listOf(Hand("44222"), Hand("44JKK")) to listOf(Hand("44JKK"), Hand("44222")),
    ).map { (unordered, ordered) ->
        dynamicTest("$unordered -> $ordered") {
            assertEquals(ordered, unordered.sortedWith(compareStrengthsWithJoker))
        }
    }
}

