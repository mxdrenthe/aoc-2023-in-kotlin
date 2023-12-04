fun main() {

    fun part1(input: List<String>): Int {
        return input.map { line -> line.filter { c -> c.isDigit() } }
            .sumOf { "${it.first()}${it.last()}".toInt() }
    }

    fun part2(input: List<String>): Int {
        val digits = listOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
        return input.sumOf { s ->
            val d = buildString {
                for (i in s.indices) when (s[i]) {
                    in '0'..'9' -> append(s[i])
                    else -> {
                        val ss = s.substring(i)
                        digits.forEachIndexed { index, digit ->
                            if (ss.startsWith(digit)) {
                                append(index)
                            }
                        }
                    }
                }
            }
            "${d.first()}${d.last()}".toInt()
        }
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 142)

    val testInput2 = readInput("Day01_test2")
    check(part2(testInput2) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
