import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        System.out.println("Введите первое число A:");
        int firstNumber = new Scanner(System.in).nextInt();
        System.out.println("Введите второе число B:");
        int secondNumber = new Scanner(System.in).nextInt();

        System.out.println("*****************************");
        System.out.println("Сумма чисел (A+B): " + (firstNumber + secondNumber));
        System.out.println("Разность чисел (A-B): " + (firstNumber - secondNumber));
        System.out.println("Произведение чисел (A*B): " + (firstNumber * secondNumber));
        System.out.println("Частное чисел (A/B): " + ((double)firstNumber / secondNumber));
    }
}