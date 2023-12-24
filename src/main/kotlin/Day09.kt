object Day09 {
    fun part1(input: List<String>): Int = input.histories.sumOf { it.nextValue() }
    fun part2(input: List<String>): Int = input.histories.sumOf { it.previousValue() }
}

fun main() {
    val testInput = readInput("Day09_test")
    check(Day09.part1(testInput) == 114)
    // check(Day05.part2(testInput) == 46.toLong())

    val input = readInput("Day09")

    Day09.part1(input).println()
    Day09.part2(input).println()
}

internal fun List<Int>.nextValue(): Int =
    diffSequences().sumOf { it.last() }

internal fun List<Int>.previousValue(): Int =
    diffSequences()
        .toList()
        .reversed()
        .runningFold(0) { acc, diff -> diff.first() - acc }
        .last()

internal val List<String>.histories
    get() =
        map { line ->
            line.split(" ").map { it.toInt() }
        }

internal fun List<Int>.diffSequences(): Sequence<List<Int>> =
    generateSequence(this) { current -> current.diffSequence() }
        .takeWhile { diff -> diff.any { it != 0 } }


internal fun List<Int>.diffSequence(): List<Int> =
    windowed(2)
        .map { (left, right) -> right - left }