package Day01

import org.assertj.core.api.Assertions.assertThat
import readInput


fun main() {
    fun part1(input: List<String>) =
        input
            .map { it.filter { it.isDigit() } }
            .map { "${it.first()}${it.last()}" }
            .sumOf { it.toInt() }


// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    assertThat(part1(testInput)).isEqualTo(142)

// print the puzzle answer
    val puzzleInput = readInput("Day01")
    println(part1(puzzleInput))

}
