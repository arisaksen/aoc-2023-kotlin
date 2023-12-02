package day02

import org.assertj.core.api.Assertions.assertThat
import readInput
import java.lang.IllegalArgumentException


fun main() {

    fun String.cubesRequired(): List<Cube> {
        val listOfCubes = mutableListOf<Cube>()
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
                repeat(number.toInt()) { listOfCubes.add(cube) }
            }
        return listOfCubes
    }

    fun part2(input: List<String>) =
        input
            .toGameRounds()
            .map { game ->
                val cubesPerGame = game.value.map { it.cubesRequired() }
                val maxRed = cubesPerGame.map {
                    it.count { it.color == Color.RED }
                }.max()
                val maxGreen = cubesPerGame.map {
                    it.count { it.color == Color.GREEN }
                }.max()
                val maxBlue = cubesPerGame.map {
                    it.count { it.color == Color.BLUE }
                }.max()
                val power = maxRed * maxGreen * maxBlue
                power
            }
            .sum()

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Part1_test")
    assertThat(part2(testInput)).isEqualTo(2286)

// print the puzzle answer
    val puzzleInput = readInput("Part2")
    println(part2(puzzleInput))

}
