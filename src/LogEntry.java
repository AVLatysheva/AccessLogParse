//класс для строки файла - далее строка для парсинга
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LogEntry {
    private final String address;     // IP-адрес клиента
    private final String prop1;       // 1-oe св-во, на месте которых обычно стоят дефисы, но могут встречаться пустые строки ("")
    private final String prop2;       // 2-oe св-во -=-
    private final LocalDateTime dateOfReq;   // Дата и время запроса в квадратных скобках.
    private final HttpMethodEnum methodOfReq; // Метод запроса (GET и т.д.)
    private final String method; // Метод запроса после HttpMethodEnum
    private final int respCode;    // Код HTTP-ответа (200)
    private final int respSize;    // Размер отданных данных в байтах (в примере выше — 61096)
    private final String referer;     // Путь к странице, с которой перешли на текущую страницу, — referer (“https://nova-news.ru/search/?rss=1&lg=1”)
    private final UserAgent userAgent;   // User-Agent — информация о браузере или другом клиенте, который выполнил запрос

    public LogEntry(String str){
        int i, i1;
        i=str.indexOf(' ');
        this.address=str.substring(0,i);
        i++;
        str=str.substring(i);

        i=str.indexOf(' ');
        this.prop1=str.substring(0,i);
        i++;
        str=str.substring(i);

        i=str.indexOf(' ');
        this.prop2=str.substring(0,i);
        i++;
        str=str.substring(i);

        i=str.indexOf(']');
        String dateStr=str.substring(1,i);
        this.dateOfReq=LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z").withLocale(Locale.ENGLISH)); //парсим дату
        i+=2;
        str=str.substring(i);

        i=str.indexOf('"');
        i1=str.indexOf(' ');
        this.methodOfReq=HttpMethodEnum.valueOf(str.substring(i+1,i1));
        str=str.substring(i1);

        i=str.indexOf('\"');
        this.method = str.substring(0,i);
        i+=2;
        str=str.substring(i);

        i=str.indexOf(' ');
        try {
            this.respCode=Integer.parseInt(str.substring(0,i));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        i+=1;
        str=str.substring(i);

        i=str.indexOf(' ');
        try {
            this.respSize=Integer.parseInt(str.substring(0,i));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        i+=2;
        str=str.substring(i);

        i=str.indexOf('\"');
        this.referer=str.substring(0,i);
        i+=3;
        str=str.substring(i);

        i=str.indexOf('\"');
        this.userAgent=new UserAgent(str.substring(0,i));
    }

    public String getAddress() {
        return address;
    }

    public String getProp1() {
        return prop1;
    }

    public String getProp2() {return prop2;}

    public LocalDateTime getDateOfReq() {return dateOfReq;}

    public HttpMethodEnum getMethodOfReq() {return methodOfReq;}

    public String getMethod() {return method;}

    public int getRespCode() {return respCode;}

    public int getRespSize() {return respSize;}

    public String getReferer() {return referer;}

    public UserAgent getUserAgent() {return userAgent;}

    @Override
    public String toString() {
        return address + " " +
                prop1 + " " +
                prop2 + " " +
                dateOfReq +" " +
                "\""+methodOfReq.name() + " " + method + "\"" + " " +
                respCode + " " +
                respSize + " " +
                "\""+referer  +"\"" + " " +
                "\""+userAgent+"\"";
    }

}
