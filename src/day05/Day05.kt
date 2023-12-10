package day05

import org.assertj.core.api.Assertions.assertThat
import readText

enum class KEYS(val keyName: String) {
    SEEDS("seeds"),
    SEED_TO_SOIL_MAP("seed-to-soil map"),
    SOIL_TO_FERTILIZER_MAP("soil-to-fertilizer map"),
    FERTILIZER_TO_WATER_MAP("fertilizer-to-water map"),
    WATER_TO_LIGHT_MAP("water-to-light map"),
    LIGHT_TO_TEMPERATURE_MAP("light-to-temperature map"),
    TEMPERATURE_TO_HUMIDITY_MAP("temperature-to-humidity map"),
    HUMIDITY_TO_LOCATION_MAP("humidity-to-location map")
}

fun List<Long>.toMap(): Map<Long, Long> {
    val sourceRange = (this[0]..(this[0] + this[2])).toList()
    val destinationRange = (this[1]..<this[1] + this[2]).toList()

    return destinationRange.zip(sourceRange).toMap()
}

// fert
//14

//[39, 0, 15]

// 53

fun Long.getValue(keyName: String, maps: Map<String, List<List<Long>>>): Long {
    val list = maps[keyName]

    val inList = list?.firstOrNull {
        val sourceRangeStart = it[1]
        val sourceRangeEnd = it[1] + it[2]
        val destinationRangeStart = it[0]
        val destinationRangeEnd = it[0] + it[2]

        this in sourceRangeStart..sourceRangeEnd
    }
    // this = 79
    // return = 81

    val destinationRangeStart = inList?.get(0)
    val sourceRangeStart = inList?.get(1)
    val destination = this.minus(sourceRangeStart ?: 0L).plus(destinationRangeStart ?: 0L)

    return destination
}

fun Long.getLocation(maps: Map<String, List<List<Long>>>): Long {
    val soil = this.getValue(KEYS.SEED_TO_SOIL_MAP.keyName, maps)
    val fert = soil.getValue(KEYS.SOIL_TO_FERTILIZER_MAP.keyName, maps)
    val water = fert.getValue(KEYS.FERTILIZER_TO_WATER_MAP.keyName, maps)
    val light = water.getValue(KEYS.WATER_TO_LIGHT_MAP.keyName, maps)
    val temprature = light.getValue(KEYS.LIGHT_TO_TEMPERATURE_MAP.keyName, maps)
    val humidity = temprature.getValue(KEYS.TEMPERATURE_TO_HUMIDITY_MAP.keyName, maps)

    return humidity.getValue(KEYS.HUMIDITY_TO_LOCATION_MAP.keyName, maps)
}

fun main() {


    fun part1(input: String): Long {
        val inputValues = input
            .split("\n\n")
            .associate {
                it.substringBefore(":") to it.substringAfter(":")
            }

        val seeds = inputValues.keys.first() to
                inputValues.values.first().split(" ").filterNot { it.isBlank() }.map { it.toLong() }

        val maps = inputValues.entries.drop(1).associate {
            it.key to it.value.split('\n').filterNot { it.isBlank() }.map { it.split(" ").map { it.toLong() } }
        }

        val locations = seeds.second.map { seed ->
            seed.getLocation(maps)
        }

        return locations.min()
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
    assertThat(part1(testInput)).isEqualTo(35)

// print the puzzle answer
    val puzzleInput = readText("Part1")
    println(part1(puzzleInput))

}
