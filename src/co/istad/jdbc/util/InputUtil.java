package co.istad.jdbc.util;

import co.istad.jdbc.view.View;

import java.math.BigDecimal;
import java.util.Scanner;

public class InputUtil {

    private static final Scanner scanner = new Scanner(System.in);

    public static String getText(String label) {
        System.out.print(label);
        return scanner.nextLine();
    }

    public static Integer getInteger(String label) {
        do {
            try {
                System.out.print(label);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print(e.getMessage());
            }
        }
        while (true);
    }

    public static Double getDouble(String label) {
        do {
            try {
                System.out.print(label);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print(e.getMessage());
            }
        }
        while (true);
    }

    public static BigDecimal getMoney(String label) {
        do {
            View.print(label + "-> ", false);
            try {
                return BigDecimal.valueOf(Double.parseDouble(scanner.nextLine()));
            } catch (NumberFormatException e) {
                View.print(e.getMessage(), true);
            }
        } while (true);
    }
}
