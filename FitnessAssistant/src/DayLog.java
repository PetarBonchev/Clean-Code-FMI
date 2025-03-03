import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DayLog {
    public String date;
    public List<String> data;

    DayLog() {
        this.date = "";
        this.data = new ArrayList<>();
    }

    public void addData(List<String> data) {
        this.data.addAll(data);
    }

    public String makeStringRepresentation() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.date);
        for (String dataPoint : this.data) {
            sb.append(" ").append(dataPoint);
        }
        return sb.toString();
    }

    public static DayLog convertStringToDayLog(String representation) {
        DayLog dayLog = new DayLog();
        String[] tokens = representation.split(" ");
        dayLog.date = tokens[0];
        dayLog.data.addAll(Arrays.asList(tokens).subList(1, tokens.length));
        return dayLog;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DayLog other = (DayLog) obj;
        return this.date.equals(other.date) && this.data.equals(other.data);
    }
}
