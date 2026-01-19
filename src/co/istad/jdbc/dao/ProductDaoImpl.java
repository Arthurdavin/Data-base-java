package co.istad.jdbc.dao;

import co.istad.jdbc.config.DbConfig;
import co.istad.jdbc.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//    1 read all records from DB
//    * expected return value
public class ProductDaoImpl implements ProductDao {

    private final Connection conn;

    public ProductDaoImpl() {
        conn = DbConfig.getInstance();
    }

    @Override
    public List<Product> findAll() {

//        step 4: create statement

        try {
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
        try{
            final String SQL = """
                    INSERT INTO products(code,name,price,qty,is_deleted)
                    VALUES(?,?,?,?,?)
                    """;
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1,product.getCode());
            pstmt.setString(2,product.getName());
            pstmt.setBigDecimal(3,product.getPrice());
            pstmt.setInt(4,product.getQty());
            pstmt.setBoolean(5,product.getDeleted());
            return pstmt.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Product> search(Product product) {
        try {
            final String SQL = """
                        SELECT * FROM products
                        WHERE (code LIKE ?)
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
    public int updateById(Product product) {
        try {
            final String SQL = """
                        UPDATE products
                        SET code = ?, name = ?, price = ?, qty = ?, is_deleted = ?
                        WHERE id = ? AND is_deleted = false
                    """;

            PreparedStatement pstmt = conn.prepareStatement(SQL);

            // 1. code
            pstmt.setString(1, product.getCode());
            // 2. name
            pstmt.setString(2, product.getName());
            // 3. price
            pstmt.setBigDecimal(3, product.getPrice());
            // 4. qty
            pstmt.setInt(4, product.getQty());
            // 5. is_deleted
            pstmt.setBoolean(5, product.getDeleted());
            // 6. id (THIS WAS MISSING)
            pstmt.setInt(6, product.getId());

            return pstmt.executeUpdate(); // number of rows affected

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
