object Day03 {
    fun part1(input: List<String>): Int =
        input.partNumbers.sumOf { it.value }

    fun part2(input: List<String>): Int =
        input.gears().sumOf { it.ratio }
}

fun main() {
    val testInput = readInput("Day03_test")
    check(Day03.part1(testInput) == 4361)
    check(Day03.part2(testInput) == 467835)

    val input = readInput("Day03")

    Day03.part1(input).println()
    Day03.part2(input).println()
}

internal val List<String>.partNumbers get() = potentialPartNumbers().filter { it.isAdjacentTo(symbols()) }

internal fun List<String>.gears(): Set<Gear> =
    symbols()
        .filter { it.value == "*" }
        .mapNotNull { symbol ->
            partNumbers
                .filter { it.isAdjacentTo(setOf(symbol)) }
                .takeIf { it.size == 2 }
                ?.let { Gear(it[0].value, it[1].value) }
        }.toSet()

internal fun String.potentialPartNumbers(
    y: Int,
    acc: Acc = Acc(),
    x: Int = 0,
    result: Set<PartNumber> = emptySet()
): Set<PartNumber> {
    return result + when {
        isEmpty() -> acc.toPartNumberOrEmpty(y)
        else -> {
            val current = toCharArray().first()
            when {
                current.isDigit() -> drop(1).potentialPartNumbers(y, acc.add(current, x), x + 1)
                else -> acc.toPartNumberOrEmpty(y) + drop(1).potentialPartNumbers(y, acc = Acc(), x = x + 1)
            }
        }
    }
}

private val Gear.ratio get() = part1 * part2

internal data class Acc(val value: String = "", val positions: Set<Int> = emptySet())

private fun Acc.add(c: Char, x: Int) = Acc(value = value + c, positions = positions + x)
private fun Acc.toPartNumber(y: Int) = PartNumber(value.toInt(), positions.map { Position(it, y) }.toSet())
private fun Acc.toPartNumberOrEmpty(y: Int) = if (value.isEmpty()) emptySet() else setOf(toPartNumber(y))

data class Position(val x: Int, val y: Int)

internal fun Position.surroundingPositions() = setOf(
    Position(y = y - 1, x = x - 1),
    Position(y = y - 1, x = x),
    Position(y = y - 1, x = x + 1),
    Position(y = y, x = x - 1),
    Position(y = y, x = x + 1),
    Position(y = y + 1, x = x - 1),
    Position(y = y + 1, x = x),
    Position(y = y + 1, x = x + 1),
)

internal fun List<String>.symbols(): Set<Symbol> =
    flatMapIndexed { y, line ->
        line.mapIndexedNotNull { x, c ->
            c.takeIf { it in symbols }?.toSymbol(x, y)
        }
    }.toSet()

data class Symbol(val value: String, val position: Position)

private fun Char.toSymbol(x: Int, y: Int) = Symbol(this.toString(), Position(x = x, y = y))

internal fun List<String>.potentialPartNumbers(): Set<PartNumber> =
    flatMapIndexed { y, line -> line.potentialPartNumbers(y) }.toSet()


data class PartNumber(val value: Int, val positions: Set<Position>)

data class Gear(val part1: Int, val part2: Int)

fun PartNumber.isAdjacentTo(symbols: Set<Symbol>): Boolean {
    val symbolPositions = symbols.map { it.position }
    return positions.flatMap { position -> position.surroundingPositions() }.toSet().any {
        it in symbolPositions
    }
}

internal val symbols = setOf('*', '-', '$', '@', '=', '#', '+', '/', '%', '&', 'F')
