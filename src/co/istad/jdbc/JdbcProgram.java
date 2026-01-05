package co.istad.jdbc;

import co.istad.jdbc.config.DbConfig;
import co.istad.jdbc.dao.ProductDaoImpl;
import co.istad.jdbc.model.Product;
import co.istad.jdbc.util.InputUtil;
import co.istad.jdbc.view.View;

import java.sql.Connection;
import java.util.List;

public class JdbcProgram {
    public static void main(String[] args) {
        DbConfig.init();
        Connection conn = DbConfig.getInstance();
//        System.out.println(conn);
//
//        try{
//            System.out.println(conn.getSchema());
//        }catch (SQLException e){
//            System.out.println("Error: " + e.getMessage());
//        }
        do{
            View.printAppMenu();
            int menu= InputUtil.getInteger("Enter your option: ");
            switch (menu){
                case 1-> {
                    System.out.println("List Items");
                    List<Product> products =new ProductDaoImpl().findAll();
                    View.table(products);
                }
                case 2-> System.out.println("Add Item");
                case 3-> System.out.println("Update Item");
                case 4-> System.out.println("Delete Item");
                case 0-> System.exit(0);
                default -> System.out.println("Invalid option");
            }
        }while (true);

    }
}
