package com.thkim.playground.cleancode.func.switch

/**
 * Created by Thkim on 4/10/21
 */
class EmployeeFactoryImpl : EmployeeFactory {
    override fun makeEmployee(r: EmployeeRecord): Employee {
        return when (r.type) {
            COMMISSIONED -> CommissionedEmployee(r)

            HOURLY -> HourlyEmployee(r)

            SALARIED -> SalariedEmployee(r)

            else -> throw Throwable()
        }
    }
}