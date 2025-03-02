public class CommandContext {

    public LogFileRepository logFileRepository;
    public FileNames fileNames;
    public FeedbackDisplay feedbackDisplay;

    CommandContext(LogFileRepository logFileRepository, FileNames fileNames, FeedbackDisplay feedbackDisplay) {
        this.logFileRepository = logFileRepository;
        this.fileNames = fileNames;
        this.feedbackDisplay = feedbackDisplay;
    }
}
