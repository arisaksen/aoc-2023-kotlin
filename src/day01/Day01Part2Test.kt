package day01


import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

class Day01Part2Test {
    val exampleLines =
        """
        two1nine
        eightwothree
        abcone2threexyz
        xtwone3four
        4nineeightseven2
        zoneight234
        7pqrstsixteen
        """.trimIndent()
            .split('\n')

    @Test
    fun singleLine() {
        val line = exampleLines.first()
        val result = line
            .checkIfContainsAndReplaceWithStringWithDigit()

        assertThat(result).isEqualTo("21nwo1nio1nin19")

        val sum: String = result.filter { it.isDigit() }
        val sumInt = "${sum.first()}${sum.last()}".toInt()
        assertThat(sumInt).isEqualTo(29)
    }

    @Test
    fun multiLines() {
        val result = exampleLines
            .map { it.checkIfContainsAndReplaceWithStringWithDigit() }
            .map { "${it.find { it.isDigit() }}${it.findLast { it.isDigit() }}" }
            .sumOf { it.toInt() }

        assertThat(result).isEqualTo(281)
    }

    @Test
    fun multiLinesSummed() {
        val allLines = exampleLines
            .map { it.checkIfContainsAndReplaceWithStringWithDigit() }
            .map { "${it.find { it.isDigit() }}${it.findLast { it.isDigit() }}" }
            .map { it.toInt() }
        assertThat(allLines[0]).isEqualTo(29)
        assertThat(allLines[1]).isEqualTo(83)
        assertThat(allLines[2]).isEqualTo(13)
        assertThat(allLines[3]).isEqualTo(24)
        assertThat(allLines[4]).isEqualTo(42)
        assertThat(allLines[5]).isEqualTo(14)
        assertThat(allLines[6]).isEqualTo(76)

        assertThat(allLines.sum()).isEqualTo(281)
    }

}
