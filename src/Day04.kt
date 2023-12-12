fun main() {

    fun part1(input: List<String>): Int {
        println(
            input.map { it.split(':') }
                .map { it[1] }
                .map { it.split('|') }
                .map { it.map { it.split(' ') } }
        )
        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 13)

    val testInput2 = readInput("Day04_test2")
//    check(part2(testInput2) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
