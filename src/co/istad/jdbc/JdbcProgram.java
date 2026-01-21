package co.istad.jdbc;

import co.istad.jdbc.config.DbConfig;
import co.istad.jdbc.dao.ProductDao;
import co.istad.jdbc.dao.ProductDaoImpl;
import co.istad.jdbc.model.Product;
import co.istad.jdbc.util.InputUtil;
import co.istad.jdbc.view.View;

import java.math.BigDecimal;
import java.util.List;

public class JdbcProgram {

    private ProductDao productDao;

    public static void main(String[] args) {
        DbConfig.init();
        new JdbcProgram().run();
    }

    private void run(){
        productDao = new ProductDaoImpl();

        do{
            View.printAppMenu();
            int menu= InputUtil.getInteger("Enter your option: ");
            switch (menu){
                case 1-> {
                    try{
                        List<Product> products = new ProductDaoImpl().findAll();
                        View.table(products);
                    }catch (RuntimeException e){
                        System.out.println("Data base is errored");
                    }
                }
                case 2 -> {
                    View.printHeader("Search Product");
                    String codeProduct = InputUtil.getText("Enter code : ");
                    Product p = new Product();
                    p.setCode(codeProduct);
                    List<Product> results = productDao.search(p);

                    if (results.isEmpty()) {
                        View.printHeader("No product found! or product is already deleted...!");
                    } else {
                        View.table(results);
                    }
                }

                case 3-> {
                    View.printHeader("Add a new product");
                    String code = InputUtil.getText("Enter Code: ");
                    String name = InputUtil.getText("Enter Name: ");
                    BigDecimal price = InputUtil.getMoney("Enter Price: ");
                    Integer qty = InputUtil.getInteger("Enter Qty: ");
                    Product newProduct = new Product(code,name,price,false,qty);
                    try {
                        int affectedRow = productDao.insert(newProduct);
                        if(affectedRow>0){
                            View.printHeader("Insert a new product successfully...!");
                        }
                        else {
                            View.printHeader("Insert operation is not affected");
                        }
                    }catch (RuntimeException e){
                        View.printHeader("Insert operation is not affected");
                    }
                }
                case 4->{

                    View.printHeader("Update a product by code");
                    String code = InputUtil.getText("Enter code: ");
                    String name = InputUtil.getText("Enter new name: ");
                    BigDecimal price = InputUtil.getMoney("Enter new price: ");
                    Integer qty = InputUtil.getInteger("Enter new qty: ");

                    Product updateProduct= new Product();
                    updateProduct.setName(name);
                    updateProduct.setPrice(price);
                    updateProduct.setQty(qty);

                    try{
                        int affectedRow = productDao.updateByCode(code,updateProduct);
                        if(affectedRow>0){
                            View.printHeader("Update product is successfully...!");
                        }
                        else {
                            View.printHeader("Update product it not affected ");
                        }
                        productDao.updateByCode(code,updateProduct);
                    }catch (RuntimeException e){
                        View.printHeader(e.getMessage());
                    }

                }
                case 5 -> {

                    View.printHeader("Delete a product by code");
                    String code = InputUtil.getText("Enter product code to delete: ");

                    try {
                        String confirmation = InputUtil.getText("Are you sure to delete [Y/n]");
                        if(confirmation.equalsIgnoreCase("y")){
                            int affectedRow = productDao.deleteByCode(code);

                            if (affectedRow > 0) {
                                View.printHeader("Product(s) deleted successfully!");
                            } else {
                                View.printHeader("Product not found or already deleted!");
                            }
                        }else {
                            View.printHeader("Delete operation cancelled");
                        }


                    } catch (RuntimeException e) {
                        View.printHeader(e.getMessage());
                    }

                }
                case 0-> System.exit(0);
                default -> System.out.println("Invalid option");
            }
        }while (true);
    }
}
