package com.thkim.playground.cleancode.func.switch

/**
 * Created by Thkim on 4/10/21
 */
const val COMMISSIONED = 0
const val HOURLY = 1
const val SALARIED = 2

data class EmployeeRecord(
    val type: Int
)
