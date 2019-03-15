package net.corda.training.state
2.	 
3.	 
4.	import net.corda.core.contracts.ContractState
5.	import net.corda.core.identity.Party
6.	import java.util.*
7.	import net.corda.core.contracts.Amount
8.	import net.corda.core.contracts.FungibleAsset
9.	import net.corda.finance.contracts.Commodity
10.	 
11.	 
12.	data class IOUState(val amount: Amount<Currency>,
13.	                    val lender: Party,
14.	                    val borrower: Party,
15.	                    val paid: Amount<Currency> = 0.POUNDS): ContractState {
16.	    
17.	    override val participants: List<Party> get(participants) = listOf(lender,borrower)