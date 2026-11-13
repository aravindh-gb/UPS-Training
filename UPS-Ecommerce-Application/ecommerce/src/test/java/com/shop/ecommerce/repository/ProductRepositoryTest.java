package com.shop.ecommerce.repository;

import com.shop.ecommerce.model.Category;
import com.shop.ecommerce.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:ecommercedb;DB_CLOSE_DELAY=-1",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class ProductRepositoryTest {
    @Autowired private ProductRepository productRepository;
    @Autowired private CategoryRepository categoryRepository;

    @Test
    void catalogFiltersByCategoryAndSearchText() {
        Category home = categoryRepository.save(new Category("Home", "Home goods."));
        Category tech = categoryRepository.save(new Category("Technology", "Useful devices."));
        productRepository.save(new Product("Table lamp", "Warm light for reading.",
                new BigDecimal("25.50"), null, 4, home));
        productRepository.save(new Product("Desk lamp", "A compact work light.",
                new BigDecimal("39.00"), null, 2, tech));
        productRepository.flush();

        assertThat(productRepository.findCatalog(home.getId(), null))
                .extracting(Product::getName).containsExactly("Table lamp");
        assertThat(productRepository.findCatalog(null, "LAMP"))
                .extracting(Product::getName).containsExactlyInAnyOrder("Table lamp", "Desk lamp");
        assertThat(productRepository.findCatalog(home.getId(), "reading"))
                .extracting(Product::getName).containsExactly("Table lamp");
    }
}
