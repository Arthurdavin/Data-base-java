package co.istad.jdbc.util;

import java.util.Scanner;

public class InputUtil {

    private static Scanner scanner = new Scanner(System.in);

    public static String getText(String label) {
        System.out.print(label);
        return scanner.nextLine();
    }
    public static Integer getInteger(String label){
        do {
            try{
                System.out.print(label);
                return Integer.parseInt(scanner.nextLine());
            }
            catch (NumberFormatException e){
                System.out.print(e.getMessage());
            }
        }
        while (true);
    }

    public static Double getDouble(String label){
        do {
            try {
                System.out.print(label);
                return Double.parseDouble(scanner.nextLine());
            }
            catch (NumberFormatException e){
                System.out.print(e.getMessage());
            }
        }
        while (true);
    }
}
