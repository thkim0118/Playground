package com.thkim.playground.algorithm

import org.junit.Test
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Thkim on 4/14/21
 */
class BFS {


    private fun bfs(graph: ArrayList<Array<Int>>, start: Int, visited: ArrayList<Boolean>) {
        val queue: Queue<Int> = LinkedList()

        queue.add(start)

        visited[start] = true

        while (queue.iterator().hasNext()) {
            val v = queue.poll()

            print("$v ")

            for (i in graph[v]) {
                if (!visited[i]) {
                    queue.add(i)
                    visited[i] = true
                }
            }
        }
    }

    @Test
    fun bfsTest() {
        val graph = arrayListOf(
            emptyArray(),
            arrayOf(2, 3, 8),
            arrayOf(1, 7),
            arrayOf(1, 4, 5),
            arrayOf(3, 5),
            arrayOf(3, 4),
            arrayOf(7),
            arrayOf(2, 6, 8),
            arrayOf(1, 7)
        )

        val visited = arrayListOf<Boolean>()

        for (i in 0..8) {
            visited.add(false)
        }

        bfs(graph, 1, visited)
    }

}