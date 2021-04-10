package com.thkim.playground.cleancode.func.switch

/**
 * Created by Thkim on 4/10/21
 */
abstract class Employee {
    abstract fun isPayDay(): Boolean

    abstract fun calculatePay(): Money

    abstract fun deliverPay(pay: Money): Unit
}