Complete the unit tests for verifying the transactions: https://github.com/corda/corda-training-template/blob/master/kotlin-source/src/test/kotlin/net/corda/training/contract/IOUIssueTests.kt

lenderAndBorrowerCannotBeTheSame

override fun verify(tx: LedgerTransaction) {
    // Add contract code here.
    // requireThat {
    //     ...
    // }
    val cmdIssue= tx.commands.requireSingleCommand<Commands.Issue>()
    val state = tx.outputStates.single() as IOUState
    val signers:Set<PublicKey> = cmdIssue.signers.toSet()
    val statePart :Set<PublicKey> = state.participants.toSet().map { it.owningKey }.toSet()
    requireThat {

        "No Input allowed for Issue command" using (tx.inputs.size==0)
        "Only one output allowed for Issue command" using (tx.outputs.size==1)
        "New IOU must be issued with a positive value" using (state.amount.quantity>0)
        "Lender and borrower cannot be the same" using (state.lender != state.borrower)
        "Transaction singers must include all the state participant" using (signers == statePart)
    }