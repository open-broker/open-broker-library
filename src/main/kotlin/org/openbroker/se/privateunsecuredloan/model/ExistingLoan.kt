package org.openbroker.se.privateunsecuredloan.model

import org.openbroker.common.requireMin

data class ExistingLoan(
    val loanAmount: Int,
    val monthlyPayment: Int,
    val shouldRefinance: Boolean,
    val refinanceAmount: Int? = null,
    val existingLoanType: ExistingLoanType,
    val responsibility: Responsibility,
    val lenderName: String? = null
) {
    init {
    	loanAmount.requireMin(1, "loanAmount")
        monthlyPayment.requireMin(1, "monthlyPayment")
    }
}