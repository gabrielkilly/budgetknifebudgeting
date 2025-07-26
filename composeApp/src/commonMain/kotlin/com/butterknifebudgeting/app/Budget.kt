package com.butterknifebudgeting.app

interface IBudget {
    val id: String
    val incomes: List<IIncome>
}

class Budget(
    override val id: String,
    override val incomes: List<Income>
): IBudget {

}