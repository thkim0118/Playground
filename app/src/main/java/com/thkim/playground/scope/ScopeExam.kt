package com.thkim.playground.scope

import kotlin.random.Random

/*
 * Created by Taehyung Kim on 2021-06-25
 */
class ScopeExam {
    class Person {
        var firstName: String? = null
        var lastName: String? = null

        fun work() {}

        fun sleep() {}
    }

    class Service {
        var port: Int = 0

        fun query(): Int = port
    }

    private fun applyScope() {
        // 객체 초기화 시 주로 사용
        val person = Person().apply {
            firstName = "First"
            lastName = "Last"
        }
    }

    private fun alsoScope() {
        // T 객체가 parameter 로 전달.
        // 객체 유효성 판단 or 디버깅용으로 주로 사용
        Random.nextInt(100).also { value ->
            print("random number -> $value")
        }
    }

    private fun letScope() {
        // block 내부 결과 값이 반환
        // null-safe code 작성에 주로 사용
        val number: Int? = null
        val sum = number?.let {
            "${intArrayOf(10, it).sum()}"
        }.orEmpty()
    }

    private fun withScope() {
        // 대상 객체 내부에서 할 수 있는 함수를 사용할때 주로 사용
        val person = Person()

        with(person) {
            work()
            sleep()
        }
    }

    private fun runScope() {
        val service = Service()
        // 반환 값이 block 내부 결과 값.
        val result = service.run {
            port = 8080
            query() // query 의 반환 값이 result 에 주입.
        }
    }
}