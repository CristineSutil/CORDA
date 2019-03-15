package net.corda.training.state
2.	 
3.	import net.corda.core.contracts.ContractState
4.	import net.corda.core.identity.Party
5.	import java.util.*
6.	import net.corda.core.contracts.Amount
7.	import net.corda.finance.*
8.	 
9.	/**
10.	 * This is where you'll add the definition of your state object. Look at the unit tests in [IOUStateTests] for
11.	 * instructions on how to complete the [IOUState] class.
12.	 *
13.	 * Remove the "val data: String = "data" property before starting the [IOUState] tasks.
14.	 */
15.	data class IOUState(val amount: Amount<Currency>,
16.	                    val lender: Party,
17.	                    val borrower: Party,
18.	                    val paid: Amount<Currency> = 10.POUNDS): ContractState {
19.	    override val participants: List<Party> get() = listOf(lender, borrower)
20.	 
21.	}