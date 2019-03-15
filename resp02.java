package net.corda.training.state

import net.corda.core.contracts.Amount
import net.corda.core.contracts.ContractState
import net.corda.core.identity.Party
import java.util.*

data class IOUState(val lender: Party ): ContractState {
    override val participants: List<Party> get() = listOf()
}

-hasPaidFieldOfCorrectType:
package net.corda.training.state

import net.corda.core.contracts.Amount
import net.corda.core.contracts.ContractState
import net.corda.core.identity.Party
import net.corda.finance.POUNDS
import java.util.*

data class IOUState(val paid: Amount<Currency> = 10.POUNDS ): ContractState {
    override val participants: List<Party> get() = listOf()
}