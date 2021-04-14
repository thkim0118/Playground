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


    @Test
    fun testTwo() {

        val personList = listOf(
            Person("RM", 110),
            Person("RM", 111),
            Person("RM", 112),
            Person("슈가", 120),
            Person("진", 130),
            Person("제이홉", 140),
            Person("제이홉", 141),
            Person("지민", 150),
            Person("뷔", 160),
            Person("정국", 170)
        )

        // num 을 기준으로 map 을 만든다. -> Key : num, Value : Person
        println(personList.associateBy { it.num })

        // name 을 기준으로 group 을 만들어 map 을 만든다.
        println(personList.groupBy { it.name })

        // num 을 기준으로 나누어 각 리스트로 반환한다.
        val (over150, under150) = personList.partition { it.num > 150 }
        println(over150)
        println(under150)
    }

    data class Person(val name: String, val num: Int)

    @Test
    fun testThree() {

        val numList = listOf(-3, 7, 2, -10, 1)

        println(numList.flatMap { listOf(it * 10, it + 10) })

        println(numList.getOrElse(1) { 50 })
        println(numList.getOrElse(10) { 50 })

        val names = listOf("A", "B", "C", "D")

        println(numList zip names)
    }
}