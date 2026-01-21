package co.istad.jdbc.dao;

import co.istad.jdbc.config.DbConfig;
import co.istad.jdbc.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//    1 read all records from DB
//    * expected return value
public class ProductDaoImpl implements ProductDao {

    private final Connection conn;

    public ProductDaoImpl() {
        conn = DbConfig.getInstance();
    }

    @Override
    public List<Product> findAll() {

        try {
//            step 4: create statement
            Statement stmt = conn.createStatement();
//            step 5 Start executing SQL Query
            final String sql = """
                    SELECT *
                    FROM products
                    ORDER BY name ASC ,price DESC
                    """;
//            step 6: Handle ResultSet
            ResultSet rs = stmt.executeQuery(sql);
            List<Product> products = new ArrayList<>();
//            multiple rows
            while (rs.next()) {
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
        } catch (SQLException e) {
            System.out.println("SQL errored: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int insert(Product product) {
        try {

            final String SQL = """
                    INSERT INTO products(code,name,price,qty,is_deleted)
                    VALUES (?,?,?,?,?)
                    """;

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, product.getCode());
            pstmt.setString(2, product.getName());
            pstmt.setBigDecimal(3, product.getPrice());
            pstmt.setInt(4, product.getQty());
            pstmt.setBoolean(5, product.getDeleted());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Product> search(Product product) {
        try {
            final String SQL = """
                        SELECT * FROM products
                        WHERE code LIKE ?
                    """;

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, "%" + product.getCode() + "%");

            ResultSet rs = pstmt.executeQuery();
            List<Product> products = new ArrayList<>();

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setCode(rs.getString("code"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getBigDecimal("price"));
                p.setQty(rs.getInt("qty"));
                p.setDeleted(rs.getBoolean("is_deleted"));

                products.add(p);
            }

            return products;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteByCode(String code) {
        try {
            final String SQL = """
                    DELETE
                    FROM products
                    WHERE code=?
                    """;
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, code);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Product> findByCode(String code) {
        try {
            final String SQL = """
                    SELECT *
                    FROM products
                    WHERE code =?
                    """;
            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setString(1, code);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setCode(rs.getString("code"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setQty(rs.getInt("qty"));
                product.setDeleted(rs.getBoolean("is_deleted"));
                return Optional.of(product);
            }
            return Optional.empty();
        } catch (SQLException e) {
            System.out.println("SQL errored: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int updateByCode(String code, Product updateProduct) {
//        update partially
//        Step 1. Load old data
        Product oldProduct = findByCode(code).orElseThrow(()->new RuntimeException("Product is not exit....!"));

//        step 2 Start partially update
        if(!updateProduct.getName().isBlank()){
            oldProduct.setName(updateProduct.getName());
        }
        if (updateProduct.getPrice()!=null){
            oldProduct.setPrice(updateProduct.getPrice());
        }
        if(updateProduct.getQty()!=null){
            oldProduct.setQty(updateProduct.getQty());
        }

        final String SQL = """
                UPDATE products
                SET name=?,price=?,qty=?
                WHERE code =?
                """;
        try{
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1,oldProduct.getName());
            pstmt.setBigDecimal(2,oldProduct.getPrice());
            pstmt.setInt(3,oldProduct.getQty());
            pstmt.setString(4,oldProduct.getCode());

            return pstmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
