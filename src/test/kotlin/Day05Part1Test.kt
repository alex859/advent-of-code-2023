import kotlin.test.assertEquals
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory


class Day05Part1Test {
    @Test
    fun `acceptance test`() {
        val input = """
            seeds: 79 14 55 13

            seed-to-soil map:
            50 98 2
            52 50 48

            soil-to-fertilizer map:
            0 15 37
            37 52 2
            39 0 15

            fertilizer-to-water map:
            49 53 8
            0 11 42
            42 0 7
            57 7 4

            water-to-light map:
            88 18 7
            18 25 70

            light-to-temperature map:
            45 77 23
            81 45 19
            68 64 13

            temperature-to-humidity map:
            0 69 1
            1 0 69

            humidity-to-location map:
            60 56 37
            56 93 4
        """.trimIndent()

        val result = Day05.part1(input)

        assertEquals(35, result)
    }

    @Test
    fun `reads seeds`() {
        val input = """
            seeds: 79 14 55 13

            seed-to-soil map:
            50 98 2
            52 50 48
        """.trimIndent()

        val seeds = input.seeds

        assertEquals(setOf(79L, 14L, 55L, 13L), seeds)
    }

    @Test
    fun `compose mappings`() {
        val input = """
            seed-to-soil map:
            50 98 4
            
            soil-to-fertilizer map:
            0 15 37
        """.trimIndent()

        val result = input.mappings.location()

        assertEquals(0, result(15))
    }


    @TestFactory
    fun `any source number that is not mapped corresponds to the same destination number`() =
        ((0L..49L) + (103L..510L)).map { source ->
            dynamicTest("source: $source -> $source") {
                val input = """
                    seed-to-soil map:
                    50 98 4
                    52 50 48
                """.trimIndent().lines()
                val mapping = input.toMapping()

                assertEquals(source, mapping(source))
            }
        }

    @TestFactory
    fun `any source number that is mapped corresponds to the mapped destination number`() =
        listOf(
            50L to 52L,
            51L to 53L,
            52L to 54L,
            98L to 50L,
            99L to 51L,
            100L to 52L,
            101L to 53L,
            102L to 102L,
        ).map { (source, expectedDestination) ->
            dynamicTest("source: $source -> $source") {
                val input = """
                    seed-to-soil map:
                    50 98 4
                    52 50 48
                """.trimIndent().lines()
                val mapping = input.toMapping()

                assertEquals(expectedDestination, mapping(source))
            }
        }
}