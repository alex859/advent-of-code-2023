import java.util.stream.Collectors
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class Day05Part2Test {
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

        val result = Day05.part2(input)

        assertEquals(46L, result)
    }

    @Test
    fun `reads seeds`() {
        val input = """
            seeds: 79 14 55 13

            seed-to-soil map:
            50 98 2
            52 50 48
        """.trimIndent()

        val seeds = input.seedsPart2

        assertEquals(((79L..92L) + (55L..67L)).toSet(), seeds.collect(Collectors.toSet()))
    }
}
