//класс для строки файла - далее строка для парсинга
public class LogStr {
    private String address;     // IP-адрес клиента
    private String prop1;       // 1-oe св-во, на месте которых обычно стоят дефисы, но могут встречаться пустые строки ("")
    private String prop2;       // 2-oe св-во -=-
    private String dateOfReq;   // Дата и время запроса в квадратных скобках.
    private String methodOfReq; // Метод запроса (GET) и путь, по которому сделан запрос
    private String respCode;    // Код HTTP-ответа (200)
    private String respSize;    // Размер отданных данных в байтах (в примере выше — 61096)
    private String referer;     // Путь к странице, с которой перешли на текущую страницу, — referer (“https://nova-news.ru/search/?rss=1&lg=1”)
    private String userAgent;   // User-Agent — информация о браузере или другом клиенте, который выполнил запрос

    private LogStr() {
    }

    // простой конструктор
    private LogStr(String address
                , String prop1
                , String prop2
                , String dateOfReq
                , String methodOfReq
                , String respCode
                , String respSize
                , String referer
                , String userAgent) {

        this.address = address;
        this.prop1 = prop1;
        this.prop2 = prop2;
        this.dateOfReq = dateOfReq;
        this.methodOfReq = methodOfReq;
        this.respCode = respCode;
        this.respSize = respSize;
        this.referer = referer;
        this.userAgent = userAgent;
    }

    @Override
    public String toString() {
        return address + " " +
                prop1 + " " +
                prop2 + " " +
                dateOfReq +" " +
                "\""+methodOfReq +"\"" + " " +
                respCode + " " +
                respSize + " " +
                "\""+referer  +"\"" + " " +
                "\""+userAgent+"\"";
    }

    // парсинг самой строки(разбираем на объекты класса) основывается именно на таком расположении и на таком кол-ве знаков между лексемами
    public static LogStr parse(String str){
        LogStr LogStr=new LogStr();
        int i;

        i=str.indexOf(' ');
        LogStr.address=str.substring(0,i);
        i++;
        str=str.substring(i);

        i=str.indexOf(' ');
        LogStr.prop1=str.substring(0,i);
        i++;
        str=str.substring(i);

        i=str.indexOf(' ');
        LogStr.prop2=str.substring(0,i);
        i++;
        str=str.substring(i);

        i=str.indexOf(']');
        LogStr.dateOfReq=str.substring(0,i+1);
        i+=3;
        str=str.substring(i);

        i=str.indexOf('"');
        LogStr.methodOfReq=str.substring(0,i);
        i+=2;
        str=str.substring(i);

        i=str.indexOf(' ');
        LogStr.respCode=str.substring(0,i);
        i++;
        str=str.substring(i);

        i=str.indexOf(' ');
        LogStr.respSize=str.substring(0,i);
        i+=2;
        str=str.substring(i);

        i=str.indexOf('\"');
        LogStr.referer=str.substring(0,i);
        i+=3;
        str=str.substring(i);

        i=str.indexOf('\"');
        LogStr.userAgent=str.substring(0,i);

        return LogStr;
    }

   private String getUserAgent() {
       if (userAgent != null){
            String str;
            int i1, i2;
            i1 = userAgent.indexOf('(');
            i2 = userAgent.indexOf(')');
            if (i1>0 && i2>0) {
                str = userAgent.substring(i1+1, i2-1);
                // делим часть по ";"
                String[] parts = str.split(";");
                String fragment = "";
                // если больше 2 частей, которые были разделены
                if (parts.length >= 2) {
                    fragment = parts[1].trim(); // берем 2 часть, убираем пробелы
                }
                // если есть данные во 2 части
                if (fragment != null) {
                    int i = fragment.indexOf('/'); // часть до "/"
                    if (i > 0){ // если нашли /
                        fragment = fragment.substring(0, i);
                        return fragment;
                    }
                }
            } return "";
            }
       return "";
    }

    public boolean isYandexBot(){
        return getUserAgent().equalsIgnoreCase("YandexBot");
    }
    public boolean isGoogleBot(){
        return getUserAgent().equalsIgnoreCase("GoogleBot");
    }

}