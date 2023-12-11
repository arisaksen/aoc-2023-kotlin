package day05

import org.assertj.core.api.Assertions.assertThat
import readText


fun main() {


    fun part2(input: String): Long {
        val inputValues = input
            .split("\n\n")
            .associate {
                it.substringBefore(":") to it.substringAfter(":")
            }

        val seeds = inputValues.keys.first() to
                inputValues.values.first().split(" ").filterNot { it.isBlank() }.map { it.toLong() }
        val seedRange = seeds.second.slice((0 + 1)..<(seeds.second.size) step 2)
        val allSeeds = seeds.second.slice(0..<seeds.second.size step 2)
            .mapIndexed { index, seedStart ->
                (seedStart..(seedStart + seedRange[index]))
            }

        val test = allSeeds.first().toList()
        val maps = inputValues.entries.drop(1).associate {
            it.key to it.value.split('\n').filterNot { it.isBlank() }.map { it.split(" ").map { it.toLong() } }
        }

//        val locations = allSeeds.map { seed ->
//            seed.getLocation(maps)
//        }

//        return locations.min()
        return 10L
    }


// test if implementation meets criteria from the description, like:
    val testInput = """
seeds: 79 14 55 13

seed-to-soil map:
50 98 2
52 50 48

soil-to-fertilizer map:
0 15 37
37 52 2
39 0 15

fertilizer-to-water map:
49 53 8
0 11 42
42 0 7
57 7 4

water-to-light map:
88 18 7
18 25 70

light-to-temperature map:
45 77 23
81 45 19
68 64 13

temperature-to-humidity map:
0 69 1
1 0 69

humidity-to-location map:
60 56 37
56 93 4
""".trimIndent()
//    assertThat(part2(testInput)).isEqualTo(46)

// print the puzzle answer
    val puzzleInput = readText("Part1")
    println(part2(puzzleInput))

}
