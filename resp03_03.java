package net.corda.training.flow

import co.paralleluniverse.fibers.Suspendable
import net.corda.core.contracts.Command
import net.corda.core.contracts.requireThat
import net.corda.core.flows.CollectSignaturesFlow
import net.corda.core.flows.FinalityFlow
import net.corda.core.flows.FlowLogic
import net.corda.core.flows.FlowSession
import net.corda.core.flows.InitiatedBy
import net.corda.core.flows.InitiatingFlow
import net.corda.core.flows.SignTransactionFlow
import net.corda.core.flows.StartableByRPC
import net.corda.core.transactions.SignedTransaction
import net.corda.core.transactions.TransactionBuilder
import net.corda.training.contract.IOUContract
import net.corda.training.state.IOUState
import picocli.CommandLine


@InitiatingFlow
@StartableByRPC
class IOUIssueFlow(val state: IOUState) : FlowLogic<SignedTransaction>() {
    @Suspendable
    override fun call(): SignedTransaction {


        val notary = serviceHub.networkMapCache.notaryIdentities.first()
        val tb = TransactionBuilder(notary = notary)
        val Commandissue = Command(IOUContract.Commands.Issue(), state.participants.map { it.owningKey })
        tb.addCommand(Commandissue)
        tb.addOutputState(state,IOUContract.IOU_CONTRACT_ID)
        val sign = serviceHub.signInitialTransaction(tb)
        tb.verify(serviceHub)
        val session = (state.participants - ourIdentity).map { initiateFlow(it) }.toSet()
        val signaturef  = subFlow(CollectSignaturesFlow(sign,session))
        return subFlow(FinalityFlow(signaturef,session))
    }
}


@InitiatedBy(IOUIssueFlow::class)
class IOUIssueFlowResponder(val flowSession: FlowSession): FlowLogic<Unit>() {
    @Suspendable
    override fun call() {
        val signedTransactionFlow = object : SignTransactionFlow(flowSession) {
            override fun checkTransaction(stx: SignedTransaction) = requireThat {
                val output = stx.tx.outputs.single().data
                "This must be an IOU transaction" using (output is IOUState)
            }
        }
        val signedin = subFlow(signedTransactionFlow)
        subFlow(signedTransactionFlow)
    }
}