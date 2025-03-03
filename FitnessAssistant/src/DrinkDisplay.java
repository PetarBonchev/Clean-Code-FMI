public class DrinkDisplay extends Command {

    DrinkDisplay(CommandContext context, DayLog details) {
        super(context, details);
    }

    @Override
    public void execute() {
        DayLog requestedDayData = context.logFileRepository.getDayLog(context.fileNames.waterDataFile, details.date);
        context.feedbackDisplay.outputWaterLog(requestedDayData);
    }
}
