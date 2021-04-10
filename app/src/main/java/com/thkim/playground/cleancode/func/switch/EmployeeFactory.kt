package com.thkim.playground.cleancode.func.switch

/**
 * Created by Thkim on 4/10/21
 */
interface EmployeeFactory {
    @Throws(Throwable::class)
    fun makeEmployee(r: EmployeeRecord): Employee
}