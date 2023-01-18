import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;

public class Statistics {
    private int totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;
    private HashSet<String> allExistsPages;
    private HashMap<PlatformEnum, Integer> amountOfDifferentOS;
    public Statistics(){
        totalTraffic = 0;
        allExistsPages = new HashSet<>();
        amountOfDifferentOS = new HashMap<>();
        // зададим заведомо малое и заведомо большое значения
        maxTime = LocalDateTime.of(1900,01,01,00,00,00);
        minTime = LocalDateTime.of(4000,01,01,00,00,00);
    }

    public void addEntry(LogEntry logEntry){
        PlatformEnum plat;
        // System.out.println(logEntry.getRespSize());
        totalTraffic+=logEntry.getRespSize();
        if (logEntry.getDateOfReq().compareTo(minTime)<0)
            minTime=logEntry.getDateOfReq();
        if (logEntry.getDateOfReq().compareTo(maxTime)>0)
            maxTime=logEntry.getDateOfReq();

        // добавим страницу, если она существует код 200;
        if (logEntry.getRespCode() == 200){allExistsPages.add(logEntry.getMethod());}

        // подсчитаем кол-во различных ОС
        plat = PlatformEnum.valueOf(logEntry.getUserAgent().getPlatform());
        if (amountOfDifferentOS.containsKey(plat)){
            amountOfDifferentOS.put(plat, amountOfDifferentOS.get(plat) + 1);
        }
        else amountOfDifferentOS.put(plat, 1);

    }

    public int getTraffic() {return totalTraffic;}
    public LocalDateTime getMinTime(){return minTime;}

    public LocalDateTime getMaxTime(){return maxTime;}
    public HashSet<String> getAllExistsPages(){
        return new HashSet<>(this.allExistsPages);

    }

    public HashMap<PlatformEnum, Integer> getMapOfDifferentOS(){
        return new HashMap<>(amountOfDifferentOS);
    }

    public double getTrafficRate(){return totalTraffic/Duration.between(minTime,maxTime).toHours();}

    public HashMap<PlatformEnum, Double> getRatioOfOS(){
        int allOS = 0;
        HashMap<PlatformEnum, Double> RatioOS = new HashMap<>();
        // Подсчитаем общее кол-во OS и в новую коллекцию сразу заполним ключи
        if (!amountOfDifferentOS.isEmpty()) {
            for(PlatformEnum key : amountOfDifferentOS.keySet()) {
                allOS+= amountOfDifferentOS.get(key);
                RatioOS.put(key,(double)amountOfDifferentOS.get(key));
            }
            for(PlatformEnum key : RatioOS.keySet()) {
                RatioOS.put(key,RatioOS.get(key)/allOS);
            }
        }
        return RatioOS;
    }
}
