import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class Day03Part2Test {
    @Test
    fun `acceptance test`() {
        val input = """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...${'$'}.*....
            .664.598..
        """.trimIndent().lines()

        val result = Day03.part2(input)

        assertEquals(467835, result)
    }

    @Test
    fun `finds gears`() {
        val input = """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...$.*....
            .664.598..
        """.trimIndent().lines()

        val gears = input.gears()

        assertEquals(
            setOf(
                Gear(467, 35),
                Gear(755, 598),
            ), gears
        )
    }
}
