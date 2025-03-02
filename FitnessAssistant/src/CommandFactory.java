public class CommandFactory {

    private final CommandContext context;

    CommandFactory() {
        LogFileRepository logFileRepository = new LogFileRepository();
        FileNames fileNames = new FileNames();
        FeedbackDisplay feedbackDisplay = new FeedbackDisplay();
        this.context = new CommandContext(logFileRepository, fileNames, feedbackDisplay);
    }

    public Command create(String command, DayLog details) {
        return switch (command) {
            case "1" -> new DrinkRegistration(this.context, details);
            case "2" -> new DrinkDisplay(this.context, details);
            default -> null;
        };
    }

}
