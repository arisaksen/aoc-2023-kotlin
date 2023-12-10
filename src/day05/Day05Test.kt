package day05

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class Day05Test {
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
    val inputValues = testInput
        .split("\n\n")
        .associate {
            it.substringBefore(":") to it.substringAfter(":")
        }

    val seeds = inputValues.keys.first() to
            inputValues.values.first().split(" ").filterNot { it.isBlank() }.map { it.toLong() }

    val maps = inputValues.entries.drop(1).associate {
        it.key to it.value.split('\n').filterNot { it.isBlank() }.map { it.split(" ").map { it.toLong() } }
    }


    @Test
    fun locationNumber() {
        val locations = seeds.second.map { seed ->
            seed.getLocation(maps)
        }

        assertThat(locations.min()).isEqualTo(35L)
    }

    @Test
    fun `humid to location`() {
        val humid1 = 78L
        val humid2 = 43L
        val humid3 = 82L
        val humid4 = 35L

        assertThat(humid1.getValue(KEYS.HUMIDITY_TO_LOCATION_MAP.keyName, maps)).isEqualTo(82L)
        assertThat(humid2.getValue(KEYS.HUMIDITY_TO_LOCATION_MAP.keyName, maps)).isEqualTo(43L)
        assertThat(humid3.getValue(KEYS.HUMIDITY_TO_LOCATION_MAP.keyName, maps)).isEqualTo(86L)
        assertThat(humid4.getValue(KEYS.HUMIDITY_TO_LOCATION_MAP.keyName, maps)).isEqualTo(35L)
    }

    @Test
    fun `given seeds, when getFert, then should return expected values`() {
        val soil1 = 81L
        val soil2 = 14L
        val soil3 = 57L
        val soil4 = 13L

        assertThat(soil1.getValue(KEYS.SOIL_TO_FERTILIZER_MAP.keyName, maps)).isEqualTo(81L)
        assertThat(soil2.getValue(KEYS.SOIL_TO_FERTILIZER_MAP.keyName, maps)).isEqualTo(53L)
        assertThat(soil3.getValue(KEYS.SOIL_TO_FERTILIZER_MAP.keyName, maps)).isEqualTo(57L)
        assertThat(soil4.getValue(KEYS.SOIL_TO_FERTILIZER_MAP.keyName, maps)).isEqualTo(52L)
    }

    @Test
    fun `given seeds, when getSoil, then should return expected values`() {
        val seed1 = 79L
        val seed2 = 14L
        val seed3 = 55L
        val seed4 = 13L

        assertThat(seed1.getValue(KEYS.SEED_TO_SOIL_MAP.keyName, maps)).isEqualTo(81L)
        assertThat(seed2.getValue(KEYS.SEED_TO_SOIL_MAP.keyName, maps)).isEqualTo(14L)
        assertThat(seed3.getValue(KEYS.SEED_TO_SOIL_MAP.keyName, maps)).isEqualTo(57L)
        assertThat(seed4.getValue(KEYS.SEED_TO_SOIL_MAP.keyName, maps)).isEqualTo(13L)
    }

}
