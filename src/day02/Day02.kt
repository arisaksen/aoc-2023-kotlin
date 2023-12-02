package day02

import org.assertj.core.api.Assertions.assertThat
import readInput
import java.lang.IllegalArgumentException

enum class Color {
    RED,
    GREEN,
    BLUE
}

data class Cube(
    val color: Color
)

var cubeBag = mutableListOf<Cube>()
    .addCubesToBag(12, Color.RED)
    .addCubesToBag(13, Color.GREEN)
    .addCubesToBag(14, Color.BLUE)

fun MutableList<Cube>.addCubesToBag(numberOfCubes: Int, color: Color): MutableList<Cube> =
    this.apply {
        repeat(numberOfCubes) {
            this.add(Cube(color))
        }
    }

fun String.removeCubesFromBag(putCubesBackBeforeSet: Boolean = true): Boolean {
    if (putCubesBackBeforeSet) {
        cubeBag = mutableListOf<Cube>()
            .addCubesToBag(12, Color.RED)
            .addCubesToBag(13, Color.GREEN)
            .addCubesToBag(14, Color.BLUE)
    }
    this
        .split(',')
        .map { cubes ->
            val (number, color) = cubes.removePrefix(" ").split(" ")
            val cube = Cube(
                color = when (color) {
                    "blue" -> Color.BLUE
                    "green" -> Color.GREEN
                    "red" -> Color.RED
                    else -> throw IllegalArgumentException("color: $color. New color?")
                }
            )
            repeat(number.toInt()) {
                val removed = cubeBag.remove(cube)
                if (!removed) {
                    return false
                }
            }
        }
    return true
}

fun List<String>.toGameRounds(): Map<String, List<String>> =
    this.associate {
        val game = it.substringBefore(':')
        val set = it.substringAfter(':')
            .split(";")
            .map { it.removePrefix(" ") }
        game to set
    }

fun main() {

    fun part1(input: List<String>): Int =
        input
            .toGameRounds()
            .map { game ->
                val setsPerGame = game.value.map { it.removeCubesFromBag(putCubesBackBeforeSet = true) }
                if (false !in setsPerGame) {
                    val score = game.key.split(" ").last().toInt()
                    score
                } else {
                    0
                }
            }
            .sum()


// test if implementation meets criteria from the description, like:
    val testInput = readInput("Part1_test")
    assertThat(part1(testInput)).isEqualTo(8)

// print the puzzle answer
    val puzzleInput = readInput("Part1")
    println(part1(puzzleInput))

}
