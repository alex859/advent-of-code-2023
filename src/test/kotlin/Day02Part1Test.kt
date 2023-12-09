import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class Day02Part1Test {
    @Test
    fun `acceptance test`() {
        val input = """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
        """.trimIndent().lines()

        assertEquals(8, Day02.part1(input))
    }

    @Test
    fun `reads game details from a line`() {
        val line = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"

        val game = line.toGame()

        assertEquals(1, game.id)
    }

    @Test
    fun `reads sets of cubes`() {
        val line = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"

        val game = line.toGame()

        assertEquals(
            setOf(
                mapOf("blue" to 3, "red" to 4),
                mapOf("blue" to 6, "red" to 1, "green" to 2),
                mapOf("green" to 2)
            ),
            game.cubesSets
        )
    }

    @Test
    fun `reads single cubes set`() {
        val line = "3 blue, 4 red"

        val result = line.toCubesSet()

        assertEquals(mapOf("blue" to 3, "red" to 4), result)
    }

    @Test
    fun `a set of cubes is valid when red at most 12 green at most 13 and blue at most 14`() {
        val cubes = mapOf("red" to 12, "green" to 13, "blue" to 14)

        assertTrue(cubes.isValid())
    }

    @Test
    fun `a set of cubes with 13 red is not valid`() {
        val cubes = mapOf("red" to 13, "green" to 13, "blue" to 14)

        assertFalse(cubes.isValid())
    }
}
