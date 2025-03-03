public class FeedbackDisplay {

    public void notifySuccessfulSave() {
        System.out.println("ok");
    }

    public void outputWaterLog(DayLog dayLog) {
        System.out.println(dayLog.date + ":");
        for (String data : dayLog.data) {
            System.out.println(data + "ml");
        }
    }
}
