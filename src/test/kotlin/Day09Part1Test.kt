import kotlin.test.assertEquals
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class Day09Part1Test {
    @Test
    fun `acceptance test`() {
        val input = """
            0 3 6 9 12 15
            1 3 6 10 15 21
            10 13 16 21 30 45
        """.trimIndent().lines()

        assertEquals(114, Day09.part1(input))
    }

    @Test
    fun `read histories`() {
        val input = """
            0 3 6 9 12 15
            1 3 6 10 15 21
            10 13 16 21 30 45
        """.trimIndent().lines()

        assertEquals(
            listOf(
                listOf(0, 3, 6, 9, 12, 15),
                listOf(1, 3, 6, 10, 15, 21),
                listOf(10, 13, 16, 21, 30, 45),
            ),
            input.histories
        )
    }

    @TestFactory
    fun `single lines prediction`() = listOf(
        listOf(0, 3, 6, 9, 12, 15) to 18,
        listOf(1, 3, 6, 10, 15, 21) to 28,
        listOf(10, 13, 16, 21, 30, 45) to 68,
    ).map { (input, expected) ->
        dynamicTest("$input -> $expected") {
            assertEquals(expected, input.nextValue())
        }
    }

    @Test
    fun `sequences of differences`() {
        val input = listOf(0, 3, 6, 9, 12, 15)

        val result = input.diffSequences()

        assertEquals(
            listOf(
                listOf(0, 3, 6, 9, 12, 15),
                listOf(3, 3, 3, 3, 3),
//                listOf(0, 0, 0, 0),
            ), result.toList()
        )
    }

    @TestFactory
    fun `sequence of differences`() = listOf(
        listOf(0, 3, 6, 9, 12, 15) to listOf(3, 3, 3, 3, 3),
        listOf(0, 3, 6, 9, 12, 15, 12) to listOf(3, 3, 3, 3, 3, -3),
        listOf(3, 3, 3, 3, 3) to listOf(0, 0, 0, 0),
    ).map { (input, expected) ->
        dynamicTest("$input -> $expected") {
            assertEquals(expected, input.diffSequence())
        }
    }
}