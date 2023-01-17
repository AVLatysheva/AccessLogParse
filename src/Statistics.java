import java.time.Duration;
import java.time.LocalDateTime;

public class Statistics {
    private int totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;

    public Statistics(){
        totalTraffic = 0;
        // зададим заведомо малое и заведомо большое значения
        maxTime = LocalDateTime.of(1900,01,01,00,00,00);
        minTime = LocalDateTime.of(4000,01,01,00,00,00);
    }

    public void addEntry(LogEntry logEntry){
       // System.out.println(logEntry.getRespSize());
        totalTraffic+=logEntry.getRespSize();
        if (logEntry.getDateOfReq().compareTo(minTime)<0)
            minTime=logEntry.getDateOfReq();
        if (logEntry.getDateOfReq().compareTo(maxTime)>0)
            maxTime=logEntry.getDateOfReq();
    }

    public int getTraffic() {return totalTraffic;}
    public LocalDateTime getMinTime(){return minTime;}

    public LocalDateTime getMaxTime(){return maxTime;}

    public double getTrafficRate(){return totalTraffic/Duration.between(minTime,maxTime).toHours();}

}
