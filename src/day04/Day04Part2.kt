package day04

import org.assertj.core.api.Assertions.assertThat
import readInput

fun scratchYourHeartOutAndCount(cardToScore: Map<String, Int>): Int {
    val stackOfCards = mutableListOf<Pair<String, Int>>()
    cardToScore.forEach { card ->
        stackOfCards.add(card.key to card.value)
    }
    val stackOfCardsScratched = ArrayDeque<Pair<String, Int>>()

    while (stackOfCards.size > 0) {
        val nextCard = stackOfCards.removeFirst()
        val nextCardNumber: Int = nextCard.first.filter { it.isDigit() }.toInt()
        val nextCardScore: Int = nextCard.second
        val size = stackOfCards.size
        (1..nextCardScore).forEach { cardNumber ->
            val newCard = "Card ${cardNumber+nextCardNumber}"
            val newCardScore = cardToScore[newCard]
            newCardScore?.let {
                stackOfCards.add(newCard to newCardScore)
            }
        }
        stackOfCardsScratched.add(nextCard)
    }
    return stackOfCardsScratched.size
}


fun main() {


    fun part2(input: List<String>): Int {
        val cards = input
            .associate {
                it.substringBefore(":") to (
                        it.substringAfter(":").substringBefore("|").split(" ")
                            .filter { it.isNotBlank() } to it.substringAfter("|")
                            .split(" ").filter { it.isNotBlank() }
                        )
            }

        val cardToWins: Map<String, Int> = cards.map { card ->
            val winningNumber = (card.value.first intersect card.value.second)
            val wins = winningNumber.size
            card.key to wins
        }.toMap()

        return scratchYourHeartOutAndCount(cardToWins)
    }

// test if implementation meets criteria from the description, like:
    val testInput = """
Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
""".trimIndent().split('\n')
    //  readInput("Part1_test")
    assertThat(part2(testInput)).isEqualTo(30)

// print the puzzle answer
    val puzzleInput = readInput("Part1")
//    println(part2(puzzleInput))

}
