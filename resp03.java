-hasLenderFieldOfCorrectType:
2.	 
3.	package net.corda.training.state
4.	 
5.	import net.corda.core.contracts.Amount
6.	import net.corda.core.contracts.ContractState
7.	import net.corda.core.identity.Party
8.	import java.util.*
9.	 
10.	data class IOUState(val lender: Party ): ContractState {
11.	    override val participants: List<Party> get() = listOf()
12.	}
13.	 
14.	 
15.	-hasBorrowerFieldOfCorrectType:
16.	 
17.	package net.corda.training.state
18.	 
19.	import net.corda.core.contracts.Amount
20.	import net.corda.core.contracts.ContractState
21.	import net.corda.core.identity.Party
22.	import java.util.*
23.	 
24.	data class IOUState(val borrower: Party ): ContractState {
25.	    override val participants: List<Party> get() = listOf()
26.	}
27.	 
28.	 
29.	 
30.	-hasPaidFieldOfCorrectType:
31.	package net.corda.training.state
32.	 
33.	import net.corda.core.contracts.Amount
34.	import net.corda.core.contracts.ContractState
35.	import net.corda.core.identity.Party
36.	import net.corda.finance.POUNDS
37.	import java.util.*
38.	 
39.	data class IOUState(val paid: Amount<Currency> = 10.POUNDS ): ContractState {
40.	    override val participants: List<Party> get() = listOf()
41.	}
