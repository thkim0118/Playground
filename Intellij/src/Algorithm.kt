import java.util.*

fun main() {
    val testCase = readLine()!!.toInt()

    val result = arrayListOf<Int>()

    for (i in 1..testCase) {
        val (column, row, totalNum) = readLine()!!.split(' ').map { it.toInt() }

        val cabbagePosition: ArrayList<Array<Int>> = arrayListOf()
        val visited = arrayListOf<Boolean>()

        for (i in 1..totalNum) {
            val (x, y) = readLine()!!.split(' ').map { it.toInt() }

            cabbagePosition.add(arrayOf(x, y))
            visited.add(false)
        }

        foundResult(cabbagePosition, visited, result)
    }

    result.forEach {
        println(it)
    }
}

fun foundResult(cabbagePosition: ArrayList<Array<Int>>, visited: ArrayList<Boolean>, result: ArrayList<Int>) {
    val queue: Queue<Pair<Int, Array<Int>>> = LinkedList()

    queue.add(0 to cabbagePosition[0])
    visited[0] = true

    while (queue.iterator().hasNext()) {
        val v = queue.poll()
        val pos = v.first

        if (cabbagePosition.size > pos + 1) {
            val currentX = v.second[0]
            val currentY = v.second[1]

            cabbagePosition.forEachIndexed loop@{ index, arrays ->
                val nextX = arrays[0]
                val nextY = arrays[1]

                if (!visited[index]) {
                    if (kotlin.math.abs(currentX - nextX) > 1 || kotlin.math.abs(currentY - nextY) > 1) {
                        return@loop
                    } else if (kotlin.math.abs(currentX - nextX) == 1 && kotlin.math.abs(currentY - nextY) == 1) {
                        return@loop
                    } else {
                        queue.add(index to cabbagePosition[index])
                        visited[index] = true
                    }
                }
            }
        }
    }

    var count = 0
    visited.forEach {
        if (it) count++
    }

    result.add(count)
}