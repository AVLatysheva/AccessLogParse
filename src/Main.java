import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        int countFiles = 0;
        int countOfRows = 0;

        while (1==1){

            System.out.println("Укажите путь к файлу:");
            String path = new Scanner(System.in).nextLine();
            File file = new File(path);
            boolean fileExists  = file.exists();
            boolean isDirectory  = file.isDirectory();
            Statistics statistics = new Statistics();


            if (isDirectory) {System.out.println("Вы указали путь, а не файл: " + path); continue;}
            if (!fileExists) {System.out.println("Файла не существует: " + file); continue;}

            countFiles ++;
            System.out.println("Путь указан верно. Это файл номер " + countFiles);

           try {
                FileReader fileReader = new FileReader(path);
                BufferedReader reader = new BufferedReader(fileReader);
                String line;

                while ((line = reader.readLine()) != null) {

                    int length = line.length();
                    if (length > 1024) {throw new StringRunTimeException("Cтрока № " + countOfRows++ + " : длина строки больше 1024");}
                    countOfRows++;

                    //System.out.println("Номер строки " + countOfRows);
                    // Парсинг строки
                    LogEntry logEntry = new LogEntry(line);
                    //System.out.println(line.toString());
                    statistics.addEntry(logEntry);

                }

                System.out.println("Всего строк: " + countOfRows);
                System.out.println("Минимальное время: " + statistics.getMinTime());
                System.out.println("Максимальное время врем: " + statistics.getMaxTime());
                System.out.println("Объем трафика за все время: " + statistics.getTraffic());
                System.out.println("Объем часового трафика: " + statistics.getTrafficRate());
                //System.out.println("Существующие станицы: " + statistics.getAllExistsPages());
                System.out.println("Пары ОС/кол-во: " + statistics.getMapOfDifferentOS());
                System.out.println("Доля ОС: " + statistics.getRatioOfOS());

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