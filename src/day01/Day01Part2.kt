package day01

import org.assertj.core.api.Assertions.assertThat
import readInput


val stringToDigit = mapOf(
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9
)

fun String.checkIfContainsAndReplaceWithStringWithDigit(): String {
    return if (this.length > 5) {
        this
            .windowed(5)
            .map { window ->
                stringToDigit.keys
                    .fold(window) { acc, digitAsString ->
                        acc.replace(digitAsString, stringToDigit[digitAsString].toString())
                    }
            }
            .joinToString("")

        /** else! was fooled by values shorter than 5! had to open the data set to look at it */
    } else {
        stringToDigit.keys
            .fold(this) { acc, digitAsString ->
                acc.replace(digitAsString, stringToDigit[digitAsString].toString())
            }
    }
}


fun main() {

    fun part2(input: List<String>) =
        input
            .map { it.checkIfContainsAndReplaceWithStringWithDigit() }
            .also { // used this to look at the data set to find where the 'edge cases' was
                it.mapIndexed { index, value ->
                    print("${index + 1}: ")
                    println(value)
                }
            }
            .map { "${it.find { it.isDigit() }}${it.findLast { it.isDigit() }}" }
            .sumOf { it.toInt() }


// test if implementation meets criteria from the description, like:
    val dir = System.getProperty("sun.java.command").substringBefore('.')
    val testInput = readInput("$dir/Part2_test")
    assertThat(part2(testInput)).isEqualTo(281)

// print the puzzle answer
    val puzzleInput = readInput("$dir/Part2")
    println(part2(puzzleInput))
}
