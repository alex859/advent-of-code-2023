object Day06 {
    fun part1(input: List<String>): Int = input.toRaces().map { it.waysToWin }.multiply()

    fun part2(input: List<String>): Int = input.toRace().waysToWin
}

fun main() {
    val testInput = readInput("Day06_test")
    check(Day06.part1(testInput) == 288)
    // check(Day05.part2(testInput) == 46.toLong())

    val input = readInput("Day06")

    Day06.part1(input).println()
    Day06.part2(input).println()
}

internal fun List<String>.toRaces(): List<Race> {
    val (times, distances) =
        this.map { it.split(" ").drop(1).mapNotNull { numbers -> numbers.toLongOrNull() } }

    return times.zip(distances)
}

internal fun List<String>.toRace(): Race {
    val (times, distances) =
        this.map { it.split(" ").drop(1).joinToString("").toLong() }

    return times to distances
}

internal val Race.waysToWin: Int
    get() {
        val (time, bestDistance) = this
        return (0..time).map { wait -> wait * (time - wait) }.count { it > bestDistance }
    }

private typealias Race = Pair<Long, Long>

private fun Iterable<Int>.multiply() = reduce{ x, y -> x * y }