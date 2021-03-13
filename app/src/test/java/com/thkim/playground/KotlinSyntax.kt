package com.thkim.playground

import org.junit.Test

/*
 * Created by Taehyung Kim on 2021-03-13
 */
class KotlinSyntax {

    @Test
    fun collections() {
        val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
        fruits
            .filter { it.startsWith("a") }
            .sortedBy { it }
            .map { it.toUpperCase() }
            .forEach { println(it) }
    }

    /**
     * #1. Check if number is within a range using @in operator.
     * #2. Check if a number is out of range.
     * #3. Iterate over a range.
     */
    @Test
    fun ranges() {
        // #1.
        println("#1.")
        val x = 10
        val y = 9
        if (x in 1..y + 1) {
            println("fits in range")
        }
        println()

        // #2.
        println("#2.")
        val list02 = listOf("a", "b", "c")
        if (-1 !in 0..list02.lastIndex) {
            println("-1 is out of range")
        }
        if (list02.size !in list02.indices) {
            println("list size is out of valid list indices range, too")
        }
        println()

        // #3.
        println("#3.")
        for (x in 1..5) {
            print(x)
        }
        println()

        // Or over a progression.
        for (x in 1..10 step 2) {
            print(x)
        }
        println()
        for (x in 9 downTo 0 step 3) {
            print(x)
        }
        println()
    }
}