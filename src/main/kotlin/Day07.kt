import HandType.FIVE_OF_A_KIND
import HandType.FOUR_OF_A_KIND
import HandType.HIGH_CARD
import HandType.ONE_PAIR
import HandType.THREE_OF_A_KIND

object Day07 {
    fun part1(input: List<String>): Int =
        input.toHandsAndBids()
            .sortedWith { (h1, _), (h2, _) -> compareStrengths.compare(h1, h2) }
            .mapIndexed { rank, (_, bid) -> bid * (rank + 1) }
            .sum()

    fun part2(input: List<String>): Int =
        input.toHandsAndBids()
            .sortedWith { (h1, _), (h2, _) -> compareStrengthsWithJoker.compare(h1, h2) }
            .mapIndexed { rank, (_, bid) -> bid * (rank + 1) }
            .sum()
}

fun main() {
    val testInput = readInput("Day07_test")
    check(Day07.part1(testInput) == 6440)
    // check(Day05.part2(testInput) == 46.toLong())

    val input = readInput("Day07")

    Day07.part1(input).println()
    Day07.part2(input).println()
}

internal fun Hand.type(): HandType {
    fun List<Char>.toHand() = Hand(joinToString(""))
    val cards = value.toList()
    val cardsGrouped = cards.groupingBy { it }.eachCount()
    return when (cardsGrouped.values.maxOrNull() ?: 1) {
        5 -> FIVE_OF_A_KIND
        4 -> FOUR_OF_A_KIND
        3 -> {
            val tris = cardsGrouped.maxBy { it.value }.key
            if (cards.filterNot { it == tris }.toHand().type() == ONE_PAIR) {
                HandType.FULL_HOUSE
            } else THREE_OF_A_KIND
        }

        2 -> {
            val pair = cardsGrouped.maxBy { it.value }.key
            if (cards.filterNot { it == pair }.toHand().type() == ONE_PAIR) {
                HandType.TWO_PAIRS
            } else ONE_PAIR
        }

        1 -> HIGH_CARD
        else -> TODO()
    }

}

internal fun Hand.typeWithJoker(): HandType {
    val cards = value.toList()
    val mostCommonCard = cards.groupingBy { it }.eachCount().filterNot { (card, _) -> card == 'J' }.maxByOrNull { it.value }?.key?:'J'
    return Hand(value.replace('J', mostCommonCard)).type()
}

internal fun List<Hand>.sortByStrength(): List<Hand> {
    return sortedWith(compareStrengths)
}

private val compareStrengths: Comparator<Hand> = Comparator { a, b ->
    if (a.type() == b.type()) {
        compareByStrength(a, b)
    } else {
        a.type().compareTo(b.type())
    }
}

internal val compareStrengthsWithJoker: Comparator<Hand> = Comparator { a, b ->
    if (a.typeWithJoker() == b.typeWithJoker()) {
        compareByStrengthWithJoker(a, b)
    } else {
        a.typeWithJoker().compareTo(b.typeWithJoker())
    }
}

internal fun List<String>.toHandsAndBids(): List<Pair<Hand, Int>> =
    map { it.split(" ") }
        .map { (cards, bid) ->
            Hand(cards) to bid.toInt()
        }

data class Hand(val value: String) : Comparable<Hand> {
    override fun compareTo(that: Hand): Int {
        return if (this.type() == that.type()) {
            compareByStrength(this, that)
        } else {
            this.type().compareTo(that.type())
        }
    }
}

enum class HandType {
    HIGH_CARD,
    ONE_PAIR,
    TWO_PAIRS,
    THREE_OF_A_KIND,
    FULL_HOUSE,
    FOUR_OF_A_KIND,
    FIVE_OF_A_KIND,
}

typealias CamelCard = Char

private val CamelCard.strength get() = cardStrengths[this] ?: error("unknown card $this")

private val CamelCard.strengthWithJoker get() = cardStrengthsWithJoker[this] ?: error("unknown card $this")

private fun compareByStrength(a: Hand, b: Hand): Int {
    return when (a.value.length) {
        0 -> 0
        else -> {
            val strengthA = a.value.first().strength
            val strengthB = b.value.first().strength
            if (strengthA != strengthB) {
                strengthA.compareTo(strengthB)
            } else compareByStrength(Hand(a.value.drop(1)), Hand(b.value.drop(1)))
        }
    }
}

private fun compareByStrengthWithJoker(a: Hand, b: Hand): Int {
    return when (a.value.length) {
        0 -> 0
        else -> {
            val strengthA = a.value.first().strengthWithJoker
            val strengthB = b.value.first().strengthWithJoker
            if (strengthA != strengthB) {
                strengthA.compareTo(strengthB)
            } else compareByStrengthWithJoker(Hand(a.value.drop(1)), Hand(b.value.drop(1)))
        }
    }
}

private val cardStrengths =
    listOf('2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A')
        .mapIndexed { i, card -> card to i }
        .toMap()

private val cardStrengthsWithJoker =
    listOf('J', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'Q', 'K', 'A')
        .mapIndexed { i, card -> card to i }
        .toMap()