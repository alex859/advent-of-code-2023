import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class Day08Part1Test {
    @Test
    fun `should count number of steps to get from AAA to ZZZ`() {
        val input = """
            LLR

            AAA = (BBB, BBB)
            BBB = (AAA, ZZZ)
            ZZZ = (ZZZ, ZZZ
        """.trimIndent()

        assertEquals(6, input.countSteps())
    }

    @Test
    fun `should read L R instructions into Input`() {
        val input = """
            LLR

            AAA = (BBB, BBB)
            BBB = (AAA, ZZZ)
            ZZZ = (ZZZ, ZZZ)
        """.trimIndent()

        assert(input.toInput().instruction == listOf('L', 'L', 'R'))
    }

    @Test
    fun `should read maps into Input`() {
        val input = """
            LLR

            AAA = (BBB, BBB)
            BBB = (AAA, ZZZ)
            ZZZ = (ZZZ, ZZZ)
        """.trimIndent()

        assert(
            input.toInput().maps == mapOf(
                "AAA" to ("BBB" to "BBB"),
                "BBB" to ("AAA" to "ZZZ"),
                "ZZZ" to ("ZZZ" to "ZZZ"),
            )
        )
    }

    @Test
    fun `should get first from pair when L`() {
        val pair = "AAA" to "BBB"
        assert(pair.getByInstruction('L') == "AAA")
    }

    @Test
    fun `should get second from pair when R`() {
        val pair = "AAA" to "BBB"
        assert(pair.getByInstruction('R') == "BBB")
    }

    @Test
    fun `should count steps on instructions that do not need repeating`() {
        val input = """
            RL

            AAA = (BBB, CCC)
            BBB = (DDD, EEE)
            CCC = (ZZZ, GGG)
            DDD = (DDD, DDD)
            EEE = (EEE, EEE)
            GGG = (GGG, GGG)
            ZZZ = (ZZZ, ZZZ)
        """.trimIndent()

        assert(input.countSteps() == 2L)
    }

    @Test
    fun `should count steps up to ZZZ`() {
        val input = """
            RLRLRLRLRL

            AAA = (BBB, CCC)
            BBB = (DDD, EEE)
            CCC = (ZZZ, GGG)
            DDD = (DDD, DDD)
            EEE = (EEE, EEE)
            GGG = (GGG, GGG)
            ZZZ = (ZZZ, ZZZ)
        """.trimIndent()

        assertEquals(2, input.countSteps())
    }
}