package com.shop.ecommerce.repository;

import com.shop.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import jakarta.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select product from Product product where " +
            "(:categoryId is null or product.category.id = :categoryId) and " +
            "(:query is null or lower(product.name) like lower(concat('%', :query, '%')) or " +
            "lower(product.description) like lower(concat('%', :query, '%')))")
    List<Product> findCatalog(@Param("categoryId") Long categoryId, @Param("query") String query);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select product from Product product where product.id = :id")
    Optional<Product> findByIdForUpdate(@Param("id") Long id);
}
