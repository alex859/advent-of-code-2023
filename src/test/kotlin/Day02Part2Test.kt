import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class Day02Part2Test {
    @Test
    fun `calculate min number of cubes to make a game valid`() {
        val input = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"

        assertEquals(mapOf("red" to 4, "green" to 2, "blue" to 6), input.toGame().minCubesSet())
    }

    @Test
    fun `calculates the power of a cube set`() {
        assertEquals(48, mapOf("red" to 4, "green" to 2, "blue" to 6).power())
    }
}

