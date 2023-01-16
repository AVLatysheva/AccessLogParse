import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        int countFiles = 0;
        int countYandexBots = 0;
        int countGoogleBots = 0;

        while (1==1){
            System.out.println("Укажите путь к файлу:");
            String path = new Scanner(System.in).nextLine();

            File file = new File(path);

            boolean fileExists  = file.exists();
            boolean isDirectory  = file.isDirectory();

            if (isDirectory) {System.out.println("Вы указали путь, а не файл: " + path); continue;}
            if (!fileExists) {System.out.println("Файла не существует: " + file); continue;}

            countFiles ++; System.out.println("Путь указан верно. Это файл номер " + countFiles);

           try {
                FileReader fileReader = new FileReader(path);
                BufferedReader reader = new BufferedReader(fileReader);
                String line;

                int countOfRows = 0;
                double yandexPart = 0;
                double googlePart = 0;

                while ((line = reader.readLine()) != null) {

                    int length = line.length();
                    if (length > 1024) {throw new StringRunTimeException("Длина строки больше 1024");}
                    countOfRows++;

                    // Парсинг строки
                    LogStr logStr = LogStr.parse(line);

                    if (logStr.isYandexBot()) countYandexBots++;
                    if (logStr.isGoogleBot()) countGoogleBots++;
                }
                if (countOfRows!=0) {
                    yandexPart=(double)countYandexBots/(double)countOfRows;
                    googlePart=(double)countGoogleBots/(double)countOfRows;
                }

                System.out.println("Всего строк: " + countOfRows);
                System.out.println("Кол-во YandexBots: " + countYandexBots);
                System.out.println("Кол-во GoogleBots: " + countGoogleBots);
                System.out.print("Доля запросов от YandexBot в файле: " + String.format("%.5g%n", yandexPart));
                System.out.print("Доля запросов от GoogleBot в файле: " + String.format("%.5g%n", googlePart));

            } catch (FileNotFoundException ex) {
               ex.printStackTrace();
            } catch (IOException ex1) {
               ex1.printStackTrace();
            } catch (ArithmeticException ex2){
               ex2.printStackTrace();
           }
        }
    }
}