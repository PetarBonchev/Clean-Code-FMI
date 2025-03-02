public abstract class Command {
    protected CommandContext context;
    protected DayLog details;

    Command(CommandContext context, DayLog details) {
        this.context  = context;
        this.details = details;
    }

    public abstract void execute();
}
