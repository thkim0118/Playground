package com.thkim.playground.syntax

import org.junit.Test

/*
 * Created by Taehyung Kim on 2021-04-10
 */
class KotlinSyntaxTwo {

    @Test
    fun testOne() {
        val nameList = listOf("가나다", "가나다라", "가나다라마바", "사아자차", "마바사", "아자차카타")

        // forEach
        nameList.forEach {
            print("$it ")
        }
        println()

        // filter
        println(nameList.filter { it.startsWith("가") })

        // map
        println(nameList.map { "$it..." })

        // any
        println(nameList.any { it == "가나" })

        // all
        println(nameList.all { it.length != 2 })

        // none
        println(nameList.none { it.startsWith("나") })

        // first
        println(nameList.firstOrNull { it.startsWith("사") })

        // last
        println(nameList.lastOrNull { it.startsWith("가") })

        // count
        println(nameList.count { it.length > 4 })
    }
}