package co.istad.jdbc.dao;


import co.istad.jdbc.config.DbConfig;
import co.istad.jdbc.model.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//    1 read all records from DB
//    * expected return value
public class ProductDaoImpl implements ProductDao{

    private final Connection conn;

    public ProductDaoImpl(){
        conn = DbConfig.getInstance();
    }

    @Override
    public List<Product> findAll() {
//        step 4: create statement
        try{
            Statement stmt = conn.createStatement();
//            step 5 Start executing SQL Query
            final String sql = """
                    SELECT*
                    FROM products
                    """;
//            step 6: Handle ResultSet
            ResultSet rs = stmt.executeQuery(sql);
            List<Product> products = new ArrayList<>();
//            multiple rows
            while (rs.next()){
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setCode(rs.getString("code"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setQty(rs.getInt("qty"));
                product.setDeleted(rs.getBoolean("is_deleted"));
                products.add(product);
            }
            return products;
        }catch (SQLException e){
            System.out.println("SQL errored: "+ e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
