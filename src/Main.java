import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        int countFiles = 0;
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
                int maxLengthStr = 0;
                int minLengthStr = 0;
                while ((line = reader.readLine()) != null) {
                    countOfRows++;
                    int length = line.length();
                    if (length > 1024) {throw new StringRunTimeException("Длина строки больше 1024");}
                    if (countOfRows == 1){minLengthStr = length;}
                    if (maxLengthStr < length) {maxLengthStr = length;}
                    if (minLengthStr > length) {minLengthStr = length;}
                }
                System.out.println("Всего строк: " + countOfRows);
                System.out.println("Максимальная длина строки - " + maxLengthStr);
                System.out.println("Минимальная длина строки - " + minLengthStr);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex1) {
               ex1.printStackTrace();
           }
        }
    }
}