import Day01.part1
import Day01.part2

object Day01 {
    fun part1(input: List<String>): Int {
        return input.sumOf { it.firstAndLastDigits() }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { it.convertDigitsSpelledWithLetters().firstAndLastDigits() }
    }
}

internal fun String.firstAndLastDigits(): Int = "${this.digits.first()}${this.digits.last()}".toInt()

private val String.digits get() = filter { it.isDigit() }

internal fun String.convertDigitsSpelledWithLetters() =
    replace("oneight", "18")
        .replace("twone", "21")
        .replace("threeight", "38")
        .replace("fiveight", "58")
        .replace("sevenine", "79")
        .replace("eightwo", "82")
        .replace("eighthree", "83")
        .replace("nineight", "98")
        .replace("one", "1")
        .replace("two", "2")
        .replace("three", "3")
        .replace("four", "4")
        .replace("five", "5")
        .replace("six", "6")
        .replace("seven", "7")
        .replace("eight", "8")
        .replace("nine", "9")

fun main() {
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 142)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
