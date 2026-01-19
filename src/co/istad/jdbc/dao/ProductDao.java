package co.istad.jdbc.dao;

import co.istad.jdbc.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> findAll();
    /*
     * Insert a new record into db
     * new data is product
     * return affected row number
     * **/
    int insert(Product product);
    List<Product> search(Product product);

    /*
    *Delete an existing product by code
    * Code is required
    * @return affected row number
     */
    int deleteByCode(String code);
    int updateById(Product product);
}
