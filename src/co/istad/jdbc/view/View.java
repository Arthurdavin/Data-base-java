package co.istad.jdbc.view;

import co.istad.jdbc.model.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;

public class View {

    public static void printAppMenu() {
        System.out.println("Welcome to JDBC System");
        System.out.println("--------------");
        System.out.println("""
                1. List Articles
                2. Add Article
                3. Update Article
                4. Delete Article
                0. Exit
                """);
    }

    public static void showSuccessMsg(String prefix) {
        System.out.println(prefix + " successfully");
    }

    public static void print(String text, boolean isNewLine) {
        if (isNewLine) {
            System.out.println(text);
        } else {
            System.out.print(text);
        }
    }

    public static void table(List<Product> products) {

        Table table = new Table(6, BorderStyle.UNICODE_ROUND_BOX_WIDE);

        table.addCell("ID");
        table.addCell("Code");
        table.addCell("Name");
        table.addCell("Price");
        table.addCell("Qty");
        table.addCell("Is_Deleted");

        for (Product p : products) {
            table.addCell(p.getId().toString());
            table.addCell(p.getCode());
            table.addCell(p.getName());
            table.addCell(p.getPrice().toString());
            table.addCell(p.getQty().toString());
            table.addCell(p.getDeleted().toString());
        }

        View.print(table.render(), true);
    }
}
