package com.thkim.playground.algorithm

import org.junit.Test

/**
 * Created by Thkim on 4/14/21
 */
class DFS {

    private fun dfs(graph: ArrayList<Array<Int>>, v: Int, visited: ArrayList<Boolean>) {
        visited[v] = true

        print("$v ")

        for (i in graph[v]) {
            if (!visited[i]) {
                dfs(graph, i, visited)
            }
        }
    }

    @Test
    fun dfsTest() {
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

        dfs(graph, 1, visited)
    }

}