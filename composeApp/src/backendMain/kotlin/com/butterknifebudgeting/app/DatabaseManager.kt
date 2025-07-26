package com.butterknifebudgeting.app

import java.math.BigDecimal

class DatabaseManager {
    fun getIncomeListForBudget(id: String): List<IIncome> {
       return MockDb.budgets.first { it.id == id }.incomes
    }
}

object MockDb {
    private val usaaIncome = Income(
        grossAmount = Salary(
            hourlyWage = BigDecimal("32.45"),
            hoursPerPaycheck = 80
        ).getGrossAmount(),
        frequency = Frequency.BiWeekly,
        preTaxWithHoldings = listOf(
            SavingsWithHolding(
                name = "401k",
                unitsWithHeld = BigDecimal("0.06"),
                unitType = WithHoldingType.PERCENTAGE
            )
        ),
        taxPercentage = BigDecimal("20.15"),
        postTaxWithHoldings = listOf()
    )
    private val myBudget = Budget(
        id = "1",
        incomes = listOf(usaaIncome)
    )

    val budgets: List<IBudget> = listOf(myBudget)
}