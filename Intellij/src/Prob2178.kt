import java.util.*

fun main() {
    val input = readLine()!!.split(' ')

    val col = input[0].toInt()
    val row = input[1].toInt()

    val pos = Array(col + 1) { IntArray(row + 1) }
    val visited = Array(col + 1) { BooleanArray(row + 1) }

    for (i in 1..col) {
        val position = Scanner(System.`in`).next()

        for (j in 1..row) {
            pos[i][j] = Character.getNumericValue(position[j - 1])
        }
    }

    val moveQY: Queue<Int> = LinkedList()
    val moveQX: Queue<Int> = LinkedList()
    val countQ: Queue<Int> = LinkedList()

    moveQY.add(1)
    moveQX.add(1)
    countQ.add(1)

    var count = 0

    while (moveQY.isNotEmpty()) {
        val currentY = moveQY.poll()
        val currentX = moveQX.poll()
        val currentC = countQ.poll()

        if (visited[currentY][currentX]) continue
        visited[currentY][currentX] = true

        if (currentY == col && currentX == row) {
            count = currentC
            break
        }

        // left
        if (check(currentY, currentX - 1, col, row, visited, pos)) {
            moveQY.add(currentY)
            moveQX.add(currentX - 1)
            countQ.add(currentC + 1)
        }

        // down
        if (check(currentY + 1, currentX, col, row, visited, pos)) {
            moveQY.add(currentY + 1)
            moveQX.add(currentX)
            countQ.add(currentC + 1)
        }

        // right
        if (check(currentY, currentX + 1, col, row, visited, pos)) {
            moveQY.add(currentY)
            moveQX.add(currentX + 1)
            countQ.add(currentC + 1)
        }

        // up
        if (check(currentY - 1, currentX, col, row, visited, pos)) {
            moveQY.add(currentY - 1)
            moveQX.add(currentX)
            countQ.add(currentC + 1)
        }
    }

    println("$count")
}

fun check(
        cy: Int,
        cx: Int,
        N: Int,
        M: Int,
        visited: Array<BooleanArray>,
        pos: Array<IntArray>
): Boolean {
    if (cy < 1 || cx < 1 || cy > N || cx > M) return false

    if (pos[cy][cx] == 0) return false

    if (visited[cy][cx]) return false

    return true
}