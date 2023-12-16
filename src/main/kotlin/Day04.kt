import kotlin.math.pow

object Day04 {
    fun part1(input: List<String>): Int = input.sumOf { it.score }

    fun part2(initialCards: List<String>): Int {
        val cardsWon = initialCards.flatMap { card -> allWinningCardsFor(initialCards, card) }
        return (cardsWon + initialCards).size
    }
}

fun main() {
    val testInput = readInput("Day04_test")
    check(Day04.part1(testInput) == 13)
    check(Day04.part2(testInput) == 30)

    val input = readInput("Day04")

    Day04.part1(input).println()
    Day04.part2(input).println()
}

internal fun allWinningCardsFor(initialCards: List<Card>, card: Card): List<Card> =
    initialCards.winningCardsFor(card).run { this + this.flatMap  { allWinningCardsFor(initialCards, it) } }

internal fun List<Card>.winningCardsFor(card: Card): List<Card> =
    dropWhile { it != card }.drop(1).take(card.winningNumbersIHave.size)

internal val Card.score: Int get() = 2.0.pow(winningNumbersIHave.size.toDouble() - 1).toInt()

internal val Card.winningNumbersIHave get() = numbers.mine.intersect(numbers.winning)

internal val CardNumbers.winning get() = first.toNumbers().toSet()

internal val CardNumbers.mine get() = second.toNumbers().toSet()

internal val Card.numbers: CardNumbers
    get() {
        val (_, numbers) = split(":")
        val (winningNumbers, numbersIHave) = numbers.split("|")
        return winningNumbers to numbersIHave
    }

private fun String.toNumbers() = split(" ").mapNotNull { it.toIntOrNull() }.toSet()

internal typealias CardNumbers = Pair<String, String>

internal typealias Card = String
