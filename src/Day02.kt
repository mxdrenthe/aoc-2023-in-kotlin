import com.sun.jdi.InvalidTypeException

fun main() {

    fun part1(input: List<String>, red: Int, green: Int, blue: Int): Int {
         return input
                .map { it.split(": ") }
                .map { game ->
                    Pair(
                        game[0].split(" ")[1].toInt(),
                        game.subList(1, game.size)
                            .flatMap { hands ->
                                hands
                                    .split("; ")
                                    .map { hand ->
                                        hand.split(", ")
                                            .map { cube ->
                                                val cubeSplit = cube.split(" ")
                                                Pair(cubeSplit[0].toInt(), cubeSplit[1])
                                            }
                                    }
                            }
                    )
                }.filter { (_, value) ->
                    var threshold = true
                    value.forEach { hand ->
                        hand.forEach { (amount, color) ->
                            if (threshold) {
                                threshold = when (color) {
                                    "red" -> amount <= red
                                    "green" -> amount <= green
                                    "blue" -> amount <= blue
                                    else -> throw InvalidTypeException("invalid value")
                                }
                            }
                        }
                    }
                    threshold
                }
                .sumOf { it.first }
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput, 12, 13, 14) == 8)

//    val testInput2 = readInput("Day02_test2")
//    check(part2(testInput2) == 281)

    val input = readInput("Day02")
    part1(input, 12, 13, 14).println()
    part2(input).println()
}
