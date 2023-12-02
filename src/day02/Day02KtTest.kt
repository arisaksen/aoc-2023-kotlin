package day02

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

class Day02KtTest {

    val puzzleExample =
        """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
        """.trimIndent()
            .split("\n")


    @Test
    fun checkGame1Set1() {
        val games = puzzleExample.toGameRounds()
        val game1 = games["Game 1"]
        game1?.first { it.removeCubesFromBag() }
        assertThat(cubeBag.count { it.color == Color.RED }).isEqualTo(8)
        assertThat(cubeBag.count { it.color == Color.BLUE }).isEqualTo(11)
        assertThat(cubeBag.count { it.color == Color.GREEN }).isEqualTo(13)
    }

    @Test
    fun checkGame5() {
        print("${cubeBag.count { it.color == Color.RED }} red, ")
        print("${cubeBag.count { it.color == Color.BLUE }} blue, ")
        println("${cubeBag.count { it.color == Color.GREEN }} green")

        val games = puzzleExample.toGameRounds()
        val game5 = games["Game 5"]
        game5?.first { it.removeCubesFromBag() }

        println(game5?.first())
        assertThat(cubeBag.count { it.color == Color.RED }).isEqualTo(6)
        assertThat(cubeBag.count { it.color == Color.BLUE }).isEqualTo(13)
        assertThat(cubeBag.count { it.color == Color.GREEN }).isEqualTo(10)

        cubeBag = mutableListOf<Cube>()
            .addCubesToBag(12, Color.RED)
            .addCubesToBag(13, Color.GREEN)
            .addCubesToBag(14, Color.BLUE)

        println()
        print("${cubeBag.count { it.color == Color.RED }} red, ")
        print("${cubeBag.count { it.color == Color.BLUE }} blue, ")
        println("${cubeBag.count { it.color == Color.GREEN }} green")
        println(game5?.last())
        game5?.last { it.removeCubesFromBag() }
        assertThat(cubeBag.count { it.color == Color.RED }).isEqualTo(11)
        assertThat(cubeBag.count { it.color == Color.BLUE }).isEqualTo(12)
        assertThat(cubeBag.count { it.color == Color.GREEN }).isEqualTo(11)
    }


    @Test
    fun checkGameScores() {
        val games = puzzleExample.toGameRounds()
        val scorePerGame = games.map { game ->
            val allSets = game.value.map { it.removeCubesFromBag(true) }
            if (false !in allSets) {
                game.key.split(" ").last().toInt()
            } else {
                0
            }
        }
        println(scorePerGame)
        assertThat(scorePerGame[0]).isEqualTo(1)
        assertThat(scorePerGame[1]).isEqualTo(2)
        assertThat(scorePerGame[2]).isEqualTo(0)
        assertThat(scorePerGame[3]).isEqualTo(0)
        assertThat(scorePerGame[4]).isEqualTo(5)

        val score = scorePerGame.sum()
        assertThat(score).isEqualTo(8)
    }

}