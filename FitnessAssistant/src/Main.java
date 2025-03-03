public class Main {
    public static void main(String[] args) {
        UserInputGatherer gatherer = new UserInputGatherer();
        CommandFactory commandFactory = new CommandFactory();
        CommandReceiver receiver = new CommandReceiver(gatherer, commandFactory);
        receiver.executeUserCommands();
    }
}