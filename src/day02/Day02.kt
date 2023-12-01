package day02

import org.assertj.core.api.Assertions.assertThat
import readInput
import readText


fun main() {

    fun part1(input: String) =
        input

    fun part1(input: List<String>) =
        input


// test if implementation meets criteria from the description, like:
    val testInput = readInput("Part1_test")
    assertThat(part1(testInput)).isEqualTo(1)

// print the puzzle answer
    val puzzleInput = readInput("Part1")
    println(part1(puzzleInput))

}
