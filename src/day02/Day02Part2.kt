package day02

import org.assertj.core.api.Assertions.assertThat
import readInput

fun main() {

    fun part2(input: List<String>) =
        input


// test if implementation meets criteria from the description, like:
    val dir = System.getProperty("sun.java.command").substringBefore('.')
    val testInput = readInput("$dir/Part2_test")
    assertThat(part2(testInput)).isEqualTo(1)

// print the puzzle answer
    val puzzleInput = readInput("$dir/Part2")
    println(part2(puzzleInput))
}
