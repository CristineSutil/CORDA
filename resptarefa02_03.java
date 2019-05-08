override fun verify(tx: LedgerTransaction) {
    val command = tx.commands.requireSingleCommand<Commands.Issue>()
    val state = tx.outputStates.single() as IOUState

    when (command.value) {
        is Commands.Issue ->

            requireThat {
            "No inputs should be consumed when issuing an IOU." using (tx.inputs.isEmpty())
            "Only one output state should be created when issuing an IOU." using (tx.outputs.size == 1)
            "A newly issued IOU must have a positive amount." using (state.amount.quantity > 0)
            "The lender and borrower cannot have the same identity." using (state.borrower != state.lender)

        }
    }
}

