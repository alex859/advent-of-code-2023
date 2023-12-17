import java.util.stream.Stream
import kotlin.streams.asStream

object Day05 {
    fun part1(input: String) =
        input.seeds.minOf(input.mappings.location())

    fun part2(input: String) =
        input.seedsPart2.parallel().mapToLong(input.mappings.location()).min().orElseThrow()
}

fun main() {
    val testInput = readInputStr("Day05_test")
    check(Day05.part1(testInput) == 35.toLong())
    check(Day05.part2(testInput) == 46.toLong())

    val input = readInputStr("Day05")

    Day05.part1(input).println()
    Day05.part2(input).println()
}

internal val String.seeds: Set<Long>
    get() {
        val (_, seeds) = lines().first().split(":")
        return seeds.split(" ").mapNotNull { it.toLongOrNull() }.toSet()
    }

internal val String.seedsPart2: Stream<Long>
    get() {
        val (_, seeds) = lines().first().split(":")
        return seeds.split(" ")
            .mapNotNull { it.toLongOrNull() }
            .chunked(2).asSequence()
            .flatMap { (firstSeed, length) -> generateSequence(firstSeed) { it + 1 }.take(length.toInt()) }
            .asStream()
    }

internal val String.mappings: List<Mapping>
    get() = mappingLinesGroup.map { it.lines().toMapping() }

private val String.mappingLinesGroup get() = split("\n\n").drop(1)

internal fun List<String>.toMapping(): Mapping =
    drop(1)
        .map { it.toRange() }
        .let(::Mapping)

internal fun List<Mapping>.location(): (Long) -> Long = fold({ it }) { acc, f -> { x -> f(acc(x)) } }

private fun String.toRange(): Range {
    val (destination, source, length) = split(" ")
    return Range(destination.toLong(), source.toLong(), length.toLong())
}

internal data class Mapping(val ranges: List<Range>) : (Long) -> Long {
    override fun invoke(source: Long) = ranges.firstNotNullOfOrNull { it -> it(source) } ?: source
}

internal data class Range(val destinationStart: Long, val sourceStart: Long, val length: Long) : (Long) -> Long? {
    override fun invoke(x: Long) = when (x) {
        in sourceStart..<sourceStart + length -> destinationStart + (x - sourceStart)
        else -> null
    }
}