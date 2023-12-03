package day03

import org.assertj.core.api.Assertions.assertThat
import readInput

fun main() {


    fun part2(input: List<String>) =
        input
            .checkForSpecialChar()
            .map { char ->
                char.findAdjacentNumbers(input, gear = true)
            }
            .filter { it.size > 1 }
            .map {
                it.reduce { acc, ints -> acc * ints }
            }
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
    assertThat(part2(testInput)).isEqualTo(467835)

// print the puzzle answer
    val puzzleInput = readInput("Part2")
    println(part2(puzzleInput))

}
