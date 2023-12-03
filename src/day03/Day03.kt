package day03

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.`in`
import readInput

/** All special characters except '.' */
const val SPECIAL_CHARS = """[$&+,:;=\\?@#|/'<>^*()%!-]"""

fun List<String>.checkForSpecialChar(): List<Pair<Pair<Int, Int>, Char>> {
    val mutableList = mutableListOf<Pair<Pair<Int, Int>, Char>>()
    this.mapIndexed { lineIndex, line ->
        line.mapIndexed { index, char ->
            if ("$char".matches(Regex(SPECIAL_CHARS))) {
                mutableList.add((lineIndex to index) to char)
            }
        }
    }
    return mutableList
}

fun Int.findWholeNumber(line: String): Int {
    var numbers = ""
    val charsAfter = line.substring(this, line.length)
    val charsBefore = line.substring(0, this).reversed()
    run lit@{
        /** kotlin forEach equivalent of break in for loop */
        charsBefore.forEach {
            if (!it.isDigit()) return@lit else numbers += it
        }
    }
    numbers = numbers.reversed()
    run lit@{
        charsAfter.forEach {
            if (!it.isDigit()) return@lit else numbers += it
        }
    }

    return numbers.toIntOrNull() ?: 0
}

fun Pair<Pair<Int, Int>, Char>.findAdjacentNumbers(lines: List<String>, gear: Boolean = false): List<Int> {
    val char = this.second
    val lineIndex: Int = this.first.first
    val charIndex: Int = this.first.second

    return if (!gear) {
        val sameLine = lines[lineIndex]
        val sameLineTotal = checkLine(sameLine, charIndex, char, gear).sum()

        val lineAbove = lines[lineIndex - 1]
        val aboveTotal = checkLine(lineAbove, charIndex, char, gear).sum()

        val lineBelow = lines[lineIndex + 1]
        val belowTotal = checkLine(lineBelow, charIndex, char, gear).sum()

        listOf(sameLineTotal, aboveTotal, belowTotal)
    } else if (char == '*') {
        val sameLine = lines[lineIndex]
        val sameLineTotal = checkLine(sameLine, charIndex, char, gear).filterNot { it == 0 }

        val lineAbove = lines[lineIndex - 1]
        val aboveTotal = checkLine(lineAbove, charIndex, char, gear).filterNot { it == 0 }

        val lineBelow = lines[lineIndex + 1]
        val belowTotal = checkLine(lineBelow, charIndex, char, gear).filterNot { it == 0 }

        listOf(sameLineTotal, aboveTotal, belowTotal).flatten()
    } else {
        return emptyList()
    }
}

private fun checkLine(line: String, charIndex: Int, char: Char, gear: Boolean): List<Int> {
    val sameDigit = digitOnNewLine(line, charIndex)
    val intLeft = if (sameDigit == 0) digitOnNewLine(line, charIndex - 1) else 0
    val intRight = if (sameDigit == 0) digitOnNewLine(line, charIndex + 1) else 0
    return listOf(sameDigit, intLeft, intRight)
}

fun digitOnNewLine(line: String, charIndex: Int) = when (line[charIndex]) {
    in "." -> 0
    in SPECIAL_CHARS -> 0
    else -> charIndex.findWholeNumber(line)
}

fun main() {


    fun part1(input: List<String>) =
        input
            .checkForSpecialChar()
            .map { char ->
                char.findAdjacentNumbers(input)
            }
            .flatten()
            .sum()


// test if implementation meets criteria from the description, like:
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
    //  readInput("Part1_test")
    assertThat(part1(testInput)).isEqualTo(4361)

// print the puzzle answer
    val puzzleInput = readInput("Part1")
    println(part1(puzzleInput))

}
