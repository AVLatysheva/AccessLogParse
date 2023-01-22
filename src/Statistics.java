import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.IntStream;

public class Statistics {
    private int totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;
    private HashSet<String> allExistsPages;
    private HashSet<String> allNotExistsPages;
    private HashMap<PlatformEnum, Integer> amountOfDifferentOS;
    private HashMap<BrowserEnum, Integer> amountOfDifferentBrowsers;
    private double amountOfBots;
    private int amountOfRows;
    private int amountOfErrorReq;
    private HashSet<String> allUniqueIPAddresses;
    public Statistics(){
        totalTraffic = 0;
        amountOfBots = 0;
        amountOfRows = 0;
        amountOfErrorReq = 0;
        allExistsPages = new HashSet<>();
        allNotExistsPages = new HashSet<>();
        amountOfDifferentOS = new HashMap<>();
        amountOfDifferentBrowsers = new HashMap<>();
        allUniqueIPAddresses = new HashSet<>();

        // зададим заведомо малое и заведомо большое значения
        maxTime = LocalDateTime.of(1900,01,01,00,00,00);
        minTime = LocalDateTime.of(4000,01,01,00,00,00);
    }

    public void addEntry(LogEntry logEntry){
        PlatformEnum plat;
        BrowserEnum browser;
        amountOfRows +=1;
        // System.out.println(logEntry.getRespSize());
        totalTraffic+=logEntry.getRespSize();
        if (logEntry.getDateOfReq().compareTo(minTime)<0)
            minTime=logEntry.getDateOfReq();
        if (logEntry.getDateOfReq().compareTo(maxTime)>0)
            maxTime=logEntry.getDateOfReq();

        // добавим страницу, если она существует код 200;
        if (logEntry.getRespCode() == 200){allExistsPages.add(logEntry.getMethod());}

        // добавим страницу, если она не существует код 404;
        if (logEntry.getRespCode() == 404){allNotExistsPages.add(logEntry.getMethod());}

        // подсчитаем кол-во различных ОС
        plat = PlatformEnum.valueOf(logEntry.getUserAgent().getPlatform());
        if (amountOfDifferentOS.containsKey(plat)){
            amountOfDifferentOS.put(plat, amountOfDifferentOS.get(plat) + 1);
        }
        else amountOfDifferentOS.put(plat, 1);

        // подсчитаем кол-во различных браузеров
        browser = BrowserEnum.valueOf(logEntry.getUserAgent().getBrowser());
        if (amountOfDifferentBrowsers.containsKey(browser)){
            amountOfDifferentBrowsers.put(browser, amountOfDifferentBrowsers.get(browser) + 1);
        }
        else amountOfDifferentBrowsers.put(browser, 1);

        // подсчитываем кол-во ботов + добавляем IP адресс для подсчета уникальных реальных пользователей
        if (!logEntry.getUserAgent().getIsBot()) {
            amountOfBots+=1;
            allUniqueIPAddresses.add(logEntry.getAddress());
        }

        // подсчитываем число ошибочных запросов
        if (logEntry.getRespCode()/100 == 4 || logEntry.getRespCode()/100 == 5) {amountOfErrorReq +=1;}

    }

    public int getTraffic() {return totalTraffic;}
    public LocalDateTime getMinTime(){return minTime;}

    public LocalDateTime getMaxTime(){return maxTime;}
    public HashSet<String> getAllExistsPages(){
        return new HashSet<>(this.allExistsPages);

    }

    public HashSet<String> getAllNotExistsPages(){
        return new HashSet<>(this.allNotExistsPages);
    }

    public HashMap<PlatformEnum, Integer> getMapOfDifferentOS(){
        return new HashMap<>(amountOfDifferentOS);
    }

    public HashMap<BrowserEnum, Integer> getMapOfDifferentBrowsers(){
        return new HashMap<>(amountOfDifferentBrowsers);
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

    public HashMap<BrowserEnum, Double> getRatioOfBrowsers(){
        int allBrowsers= 0;
        HashMap<BrowserEnum, Double> RatioOfBrowsers = new HashMap<>();
        // Подсчитаем общее кол-во OS и в новую коллекцию сразу заполним ключи
        if (!amountOfDifferentBrowsers.isEmpty()) {
            for(BrowserEnum key : amountOfDifferentBrowsers.keySet()) {
                allBrowsers+= amountOfDifferentBrowsers.get(key);
                RatioOfBrowsers.put(key,(double)amountOfDifferentBrowsers.get(key));
            }
            for(BrowserEnum key : RatioOfBrowsers.keySet()) {
                RatioOfBrowsers.put(key,RatioOfBrowsers.get(key)/allBrowsers);
            }
        }
        return RatioOfBrowsers;
    }

    public double getAverageAmountOfVisiting() {
        return (amountOfRows-amountOfBots)/Duration.between(minTime,maxTime).toHours();
    }

    public double getAverageAmountOfErrorReq() {
        return (amountOfErrorReq)/Duration.between(minTime,maxTime).toHours();
    }

    public HashSet<String> getAllUniqueIPAddresses(){
        return new HashSet<>(this.allUniqueIPAddresses);
    }

    public double getAverageAmountOfUniqueVisitors(){
        return (amountOfRows-amountOfBots)/ allUniqueIPAddresses.size();
    }
}
