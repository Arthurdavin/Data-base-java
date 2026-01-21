package co.istad.jdbc.dao;

import co.istad.jdbc.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    List<Product> findAll();
//    bre Optional help jomnuy pel vea mean or not
    Optional<Product> findByCode(String code);

    /**
     * Update an existing product by code (name, price, qty)
     *
     * @param code          is identifier of product
     * @param updateProduct is new data
     */
    int updateByCode(String code, Product updateProduct);

    /*
     * Insert a new record into db
     * new data is product
     * return affected row number
     * */
    int insert(Product product);

    List<Product> search(Product product);
    /*
    *Delete an existing product by code
    * Code is required
    * @return affected row number
    */

    int deleteByCode(String code);
}
