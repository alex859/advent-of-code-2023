import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class Day03Part1Test {
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

        assertEquals(4361, Day03.part1(input))
    }

    @Test
    fun `get symbols with positions`() {
        val input = """
            ......#...
            617*......
            .....+.58.
        """.trimIndent().lines()

        val symbols = input.symbols()

        assertEquals(
            setOf(
                Symbol("#", Position(6, 0)),
                Symbol("*", Position(3, 1)),
                Symbol("+", Position(5, 2))
            ), symbols
        )
    }

    @Test
    fun `get parts with positions`() {
        val input = """
            ......#...
            617*......
            .....+.58.
            31.....+..45
        """.trimIndent().lines()

        val parts = input.potentialPartNumbers()

        assertEquals(
            setOf(
                PartNumber(617, positions = setOf(Position(0, 1), Position(1, 1), Position(2, 1))),
                PartNumber(58, positions = setOf(Position(7, 2), Position(8, 2))),
                PartNumber(31, positions = setOf(Position(0, 3), Position(1, 3))),
                PartNumber(45, positions = setOf(Position(10, 3), Position(11, 3))),
            ), parts
        )
    }

    @Test
    fun `position surroundings`() {
        val input = Position(x = 1, y = 1)

        val result = input.surroundingPositions()

        assertEquals(
            setOf(
                Position(0, 0),
                Position(1, 0),
                Position(2, 0),
                Position(0, 1),
                Position(2, 1),
                Position(0, 2),
                Position(1, 2),
                Position(2, 2),
            ), result
        )
    }

    @Test
    fun `get parts with positions on a line`() {
        val input = "617*..34...."

        val parts = input.potentialPartNumbers(y = 1)

        assertEquals(
            setOf(
                PartNumber(617, positions = setOf(Position(0, 1), Position(1, 1), Position(2, 1))),
                PartNumber(34, positions = setOf(Position(6, 1), Position(7, 1))),
            ), parts
        )
    }

    @Test
    fun `identifies part numbers`() {
        val input = """
            ....-./..
            .347..268
            .........
        """.trimIndent().lines()

        val symbols = setOf(
            Symbol("-", position = Position(4, 0)),
            Symbol("/", position = Position(6, 0)),
        )
        val parts = input.potentialPartNumbers().filter { it.isAdjacentTo(symbols) }.toSet()

        assertEquals(
            setOf(
                PartNumber(347, positions = setOf(Position(1, 1), Position(2, 1), Position(3, 1))),
                PartNumber(268, positions = setOf(Position(6, 1), Position(7, 1), Position(8, 1))),
            ), parts
        )
    }

    @Test
    fun `another test`() {
        val input = """
            .839....644......./262......
            .......#....................
            ..........620...536.....100*
            ............................
            +....355.....+...998..630...
            76...*....513....%....*.....
        """.trimIndent().lines()

        val result = Day03.part1(input)

        assertEquals(644 + 262 + 100 + 998 + 355 + 630 + 76 + 513, result)
    }
}
