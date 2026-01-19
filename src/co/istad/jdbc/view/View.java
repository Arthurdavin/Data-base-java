package co.istad.jdbc.view;

import co.istad.jdbc.model.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;

public class View {

    public static void printAppMenu() {
        CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        Table table = new Table(1, BorderStyle.UNICODE_DOUBLE_BOX);
        table.setColumnWidth(0, 50, 100);
        table.addCell("Application Menu", cellStyle);
        table.addCell("1) List Products 2) Search  3) Add Product 4) Update Product", cellStyle);
        table.addCell("5) Delete Product 0) Exit", cellStyle);
        print(table.render(), true);
    }

    public static void showSuccessMsg(String prefix) {
        System.out.println(prefix + " successfully");
    }

    public static void print(String text, boolean isNewLine) {
        if (isNewLine)
            System.out.println(text);
        else
            System.out.print(text);
    }

    public static void printHeader(String text) {
        Table table = new Table(1,
                BorderStyle.UNICODE_ROUND_BOX_WIDE);
        table.addCell(text);
        print(table.render(), true);
    }

    public static void table(List<Product> products) {

        CellStyle centerStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        Table table = new Table(6, BorderStyle.UNICODE_DOUBLE_BOX);

        // Set column widths
        table.setColumnWidth(0, 10, 20); // ID
        table.setColumnWidth(1, 10, 20); // Code
        table.setColumnWidth(2, 15, 30); // Name
        table.setColumnWidth(3, 10, 20); // Price
        table.setColumnWidth(4, 5, 10);  // Qty
        table.setColumnWidth(5, 10, 20); // Is_Deleted

        // Header
        table.addCell("ID", centerStyle);
        table.addCell("Code", centerStyle);
        table.addCell("Name", centerStyle);
        table.addCell("Price", centerStyle);
        table.addCell("Qty", centerStyle);
        table.addCell("Is_Deleted", centerStyle);

        // Data
        for (Product p : products) {
            table.addCell(String.valueOf(p.getId()), centerStyle);
            table.addCell(String.valueOf(p.getCode()), centerStyle);
            table.addCell(String.valueOf(p.getName()), centerStyle);
            table.addCell(String.valueOf(p.getPrice()), centerStyle);
            table.addCell(String.valueOf(p.getQty()), centerStyle);
            table.addCell(String.valueOf(p.getDeleted()), centerStyle);
        }

        View.print(table.render(), true);
    }

}
