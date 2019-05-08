public interface Commands extends CommandData {
    class Issue extends TypeOnlyCommandData implements Commands{}
}
@Override
public void verify(LedgerTransaction tx) {
   requireSingleCommand(tx.getCommands(), Commands.Issue.class);
}
