import java.util.Scanner;

public class UserInputGatherer {

    private final Scanner scanner;

    UserInputGatherer() {
        this.scanner = new Scanner(System.in);
    }

    public DayLog getDetails(String userCommand) {
        DayLog commandDetails = new DayLog();
        switch (userCommand) {
            case "1":
                commandDetails = getDrinkRegistrationDetails();
                break;
            case "2":
                commandDetails = getDrinkDisplayDetails();
                break;
        }
        return commandDetails;
    }

    private DayLog getDrinkRegistrationDetails() {
        DayLog commandDetails = new DayLog();
        System.out.println("When?");
        commandDetails.date = this.scanner.nextLine();
        System.out.println("How much?(ml)");
        String waterAmount = this.scanner.nextLine();
        commandDetails.data.add(waterAmount);
        return commandDetails;
    }

    private DayLog getDrinkDisplayDetails() {
        DayLog commandDetails = new DayLog();
        System.out.println("When?");
        commandDetails.date = this.scanner.nextLine();
        return commandDetails;
    }
}
