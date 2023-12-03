package day03

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class Day03KtTest {
    val testInput = """
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
    """.trimIndent().split('\n')


    @Test
    fun `given testInput, when findAdjecentNumbers, then sum should equal 4361`() {
        val specialCharsAndPosition = testInput.checkForSpecialChar()
        val result = specialCharsAndPosition.map { char ->
            char.findAdjacentNumbers(testInput)
        }
            .flatten()
            .sum()

        assertThat(result).isEqualTo(4361)
    }


    @Test
    fun `given position of a special character and lines, when getNumbers, should return number when summed up`() {
        //      ......#...
        //      617*......      <--- here '*'
        //      .....+.58.
        val specialCharWithPosition: Pair<Pair<Int, Int>, Char> = (4 to 3) to '*'
        val result = specialCharWithPosition
            .findAdjacentNumbers(testInput)
            .sum()

        assertThat(result).isEqualTo(617)
    }

    @Test
    fun `given another position of a special character and lines, when getNumbers, should return number when summed up`() {
        //      ......755.
        //      ...$.*....      <----- here $
        //      .664.598..
        val specialCharWithPosition: Pair<Pair<Int, Int>, Char> = (8 to 3) to '$'
        val result = specialCharWithPosition
            .findAdjacentNumbers(testInput)
            .sum()

        assertThat(result).isEqualTo(664)
    }

    @Test
    fun `given another2 position of a special character and lines, when getNumbers, should return number when summed up`() {
        //        617*......
        //        .....+.58.    <--- here '+'
        //        ..592.....
        val specialCharWithPosition: Pair<Pair<Int, Int>, Char> = (5 to 5) to '+'
        val result = specialCharWithPosition
            .findAdjacentNumbers(testInput)
            .sum()

        assertThat(result).isEqualTo(592)
    }

    @Test
    fun `given another3 position of a special character and lines, when getNumbers, should return number when summed up`() {
        //        467..114..
        //        ...*......     <--- here
        //        ..35..633.
        val specialCharWithPosition: Pair<Pair<Int, Int>, Char> = (1 to 3) to '*'
        val result = specialCharWithPosition
            .findAdjacentNumbers(testInput)
            .sum()

        assertThat(result).isEqualTo(502)
    }

    @Test
    fun `given another single digit as char, when given string, should find whole number intil dot or endOfLine`() {
        val charPosition = 2 // char = 7
        val line = "617*......"

        val result = charPosition.findWholeNumber(line)

        assertThat(result).isEqualTo(617)
    }

    @Test
    fun `given single digit as char, when given string, should find whole number intil dot or endOfLine`() {
        val charPosition = 7 // char = 5 (435)
        val line = "123..435..633."

        val result = charPosition.findWholeNumber(line)

        assertThat(result).isEqualTo(435)
    }


    @Test
    fun `given first three lines, when checkForSpecialChar, should return special chars and position`() {
        val firstThreeLines: List<String> = testInput.dropLast(6)
        val result = firstThreeLines.checkForSpecialChar()

        assertThat(result).isEqualTo(listOf((1 to 3) to '*', (3 to 6) to '#'))
    }

}