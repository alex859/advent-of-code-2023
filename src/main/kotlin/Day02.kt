import Day02.part1
import Day02.part2

object Day02 {
    fun part1(input: List<String>): Int {
        return input
            .map { it.toGame() }
            .filter { it.cubesSets.all(CubesSet::isValid) }
            .sumOf { it.id }
    }

    fun part2(input: List<String>): Int {
        return input
            .map { it.toGame() }
            .map { it.minCubesSet() }
            .sumOf { it.power() }
    }
}

fun main() {
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 8)
    check(part2(testInput) == 2286)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

internal fun CubesSet.isValid(): Boolean {
    return (this["red"] ?: 0) <= 12 && (this["green"] ?: 0) <= 13 && (this["blue"] ?: 0) <= 14
}

internal fun String.toGame(): Game {
    val (game, cubeSets) = this.split(":")
    val (_, id) = game.split(" ")
    return Game(id.toInt(), cubeSets.split(";").map { it.toCubesSet() }.toSet())
}

internal fun String.toCubesSet(): CubesSet {
    val cubeSets = this.split(",")
    return cubeSets.associate {
        val (n, colour) = it.trim().split(" ")
        colour to n.toInt()
    }
}

internal fun CubesSet.power(): Int {
    return values.fold(1) { acc, n -> acc * n }
}

internal fun Game.minCubesSet(): CubesSet {
    return mapOf(
        "red" to cubesSets.maxOf { it["red"] ?: 0 },
        "green" to cubesSets.maxOf { it["green"] ?: 0 },
        "blue" to cubesSets.maxOf { it["blue"] ?: 0 },
    )
}

data class Game(val id: Int, val cubesSets: Set<CubesSet>)

typealias CubesSet = Map<String, Int>
