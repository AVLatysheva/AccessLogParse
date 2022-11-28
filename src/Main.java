import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        /* System.out.println("Введите текст и нажмите <Enter>:");
        String text = new Scanner(System.in).nextLine();
        System.out.println("Длина текста: " + text.length());*/

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
        }
    }
}