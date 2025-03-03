public class DrinkRegistration extends Command {

    DrinkRegistration(CommandContext context, DayLog details) {
        super(context, details);
    }

    @Override
    public void execute() {
        context.logFileRepository.saveLog(context.fileNames.waterDataFile, details);
        context.feedbackDisplay.notifySuccessfulSave();
    }
}
