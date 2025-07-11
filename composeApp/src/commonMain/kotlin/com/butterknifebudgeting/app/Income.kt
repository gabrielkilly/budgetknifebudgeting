package com.butterknifebudgeting.app

import java.math.BigDecimal

enum class Frequency {
    BiWeekly,    //every 2 weeks (not twice a week)
    Annually,
    Monthly
}

interface IIncome {
    val grossAmount: BigDecimal
    val frequency: Frequency
    val preTaxWithHoldings: List<IWithHolding>
    val taxPercentage: BigDecimal
    val postTaxWithHoldings: List<IWithHolding>
}

class Income(
    override val grossAmount: BigDecimal,
    override val frequency: Frequency,
    override val preTaxWithHoldings: List<IWithHolding>,
    override val taxPercentage: BigDecimal,
    override val postTaxWithHoldings: List<IWithHolding>
): IIncome {

}

enum class WithHoldingType {
    PERCENTAGE, FLAT
}

interface IWithHolding {
    val unitsWithHeld: BigDecimal
    val unitType: WithHoldingType
}

data class InsuranceWithHolding(
    val name: String,
    override val unitsWithHeld: BigDecimal,
    override val unitType: WithHoldingType
): IWithHolding

data class SavingsWithHolding(
    val name: String,
    override val unitsWithHeld: BigDecimal,
    override val unitType: WithHoldingType
): IWithHolding

interface ISalary {
    val hourlyWage: BigDecimal
    val hoursPerPaycheck: Int
}

class Salary(
    override val hourlyWage: BigDecimal,
    override val hoursPerPaycheck: Int
): ISalary {
    fun getGrossAmount(): BigDecimal =
        hourlyWage * BigDecimal(hoursPerPaycheck)
}