object Day08 {
    fun part1(input: String): Long = input.countSteps()
    fun part2(input: String): Long = input.countStepsGhost()
}

fun main() {
    val testInput = readInputStr("Day08_test")
    check(Day08.part1(testInput) == 6L)
    // check(Day05.part2(testInput) == 46.toLong())

    val input = readInputStr("Day08")

    Day08.part1(input).println()
    Day08.part2(input).println()
}

internal fun String.countSteps(): Long =
    generateSequence { toInput().instruction }
        .flatten()
        .runningFold(State()) { currentState, nextInstruction ->
            val nextNode = toInput().maps[currentState.node]!!.getByInstruction(nextInstruction)
            State(steps = currentState.steps + 1, node = nextNode)
        }
        .dropWhile { it.node != "ZZZ" }
        .first()
        .steps

internal fun String.countStepsGhost(): Long =
    with(toInput()) {
        val steps = maps.keys
            .filter { it.endsWith("A") }
            .map { startNode ->
                generateSequence { instruction }
                    .flatten()
                    .runningFold(State(steps = 0L, startNode)) { currentState, nextInstruction ->
                        val nextNode = toInput().maps[currentState.node]!!.getByInstruction(nextInstruction)
                        State(steps = currentState.steps + 1, node = nextNode)
                    }
                    .dropWhile { !it.node.endsWith("Z") }
                    .first().steps

            }
        lcm(steps)
    }


internal data class State(val steps: Long = 0, val node: String = "AAA")

internal data class Input(val instruction: List<Char>, val maps: Map<String, Pair<String, String>>)

internal fun String.toInput(): Input {
    val (instructions, maps) = split("\n\n")
    return Input(
        instruction = instructions.toList(),
        maps = maps.split('\n')
            .associate { line ->
                val (key, pair) = line.split(" = ")
                val (left, right) = pair.replace("(", "")
                    .replace(")", "")
                    .split(", ")
                key to (left to right)
            }
    )
}

fun Pair<String, String>.getByInstruction(instruction: Char) = when (instruction) {
    'L' -> first
    'R' -> second
    else -> error("Invalid instruction!")
}

internal fun lcm(list: List<Long>): Long {
    return if (list.size == 1) list.first()
    else lcm(listOf(lcm(list[0], list[1])) + list.drop(2))
}

private tailrec fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)

private fun lcm(a: Long, b: Long): Long = a * b / gcd(a, b)