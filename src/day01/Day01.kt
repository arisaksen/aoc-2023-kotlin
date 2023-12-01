package day01

import org.assertj.core.api.Assertions.assertThat
import readInput
import java.io.File
import java.io.FileReader


fun main() {
    fun part1(input: List<String>) =
        input
            .map { it.filter { it.isDigit() } }
            .map { "${it.first()}${it.last()}" }
            .sumOf { it.toInt() }


// test if implementation meets criteria from the description, like:
    val dir = System.getProperty("sun.java.command").substringBefore('.')
    val testInput = readInput("$dir/Part1_test")
    assertThat(part1(testInput)).isEqualTo(142)

// print the puzzle answer
    val puzzleInput = readInput("$dir/Part1")
    println(part1(puzzleInput))
}
