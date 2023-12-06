import com.sun.jdi.InvalidTypeException
import kotlin.math.max

fun main() {

    fun convertInput(input: List<String>) = input
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
        }

    fun part1(input: List<String>, red: Int, green: Int, blue: Int): Int {
        return convertInput(input).filter { (_, value) ->
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

    fun colorCompare(input: Int, currentValue: Int) = if (currentValue <= input) {
        input
    } else {
        currentValue
    }

    fun part2(input: List<String>): Int {
       return convertInput(input).map { (_, value) ->
            var maxColor = Triple(0, 0, 0)
            value.forEach { hand ->
                hand.forEach { (amount, color) ->
                    maxColor = when (color) {
                        "red" -> Triple(colorCompare(amount, maxColor.first), maxColor.second, maxColor.third)
                        "green" -> Triple( maxColor.first, colorCompare(amount, maxColor.second), maxColor.third)
                        "blue" -> Triple(maxColor.first, maxColor.second, colorCompare(amount, maxColor.third))
                        else -> throw InvalidTypeException("invalid value")
                    }
                }
            }
            maxColor
        }.sumOf { it.first * it.second * it.third }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput, 12, 13, 14) == 8)
    check(part2(testInput) == 2286)

    val input = readInput("Day02")
    part1(input, 12, 13, 14).println()
    part2(input).println()
}
