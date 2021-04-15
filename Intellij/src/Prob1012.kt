import java.util.*
import kotlin.math.abs

fun main() {
    val totalCase = readLine()!!.toInt()

    val posArray = arrayListOf<Array<Int>>()

    val visited = arrayListOf<Boolean>()

    val result = arrayListOf<Int>()

    for (i in 1..totalCase) {
        val (column, row, totalNum) = readLine()!!.split(' ').map { it.toInt() }

        for (j in 1..totalNum) {
            val (posX, posY) = readLine()!!.split(' ').map { it.toInt() }

            posArray.add(arrayOf(posX, posY))

            visited.add(false)
        }

        result.add(i)
        foundTotal(posArray, visited, result, i)
    }

    result.forEach {
        println("$it")
    }
    println("${result[0]}")
}

fun foundTotal(posArray: ArrayList<Array<Int>>, visited: ArrayList<Boolean>, result: ArrayList<Int>, num: Int) {
    posArray.forEachIndexed loop@{ current, arrays ->
        for (next in current until posArray.size) {
            if (!visited[current]) {
                val curX = arrays[0]
                val curY = arrays[1]

                val nextX = posArray[next][0]
                val nextY = posArray[next][1]

                if (abs(curX - nextX) > 1 || abs(curY - nextY) > 1) {
                    continue
                } else if (abs(curX - nextX) == 1 && abs(curY - nextY) == 1) {
                    continue
                } else {
                    visited[current] = true
                    result[num - 1]++
                }
            }
        }
    }
}