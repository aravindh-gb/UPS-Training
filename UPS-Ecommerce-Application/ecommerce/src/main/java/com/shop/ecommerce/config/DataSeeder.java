package com.shop.ecommerce.config;

import com.shop.ecommerce.model.Category;
import com.shop.ecommerce.model.Product;
import com.shop.ecommerce.repository.CategoryRepository;
import com.shop.ecommerce.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.math.BigDecimal;

@Configuration
public class DataSeeder {
    @Bean
    CommandLineRunner seedCatalog(CategoryRepository categories, ProductRepository products) {
        return args -> {
            if (categories.count() > 0 || products.count() > 0) return;

            Category home = categories.save(new Category("Home & Living", "Thoughtful essentials for your home."));
            Category tech = categories.save(new Category("Technology", "Useful everyday technology."));
            Category accessories = categories.save(new Category("Accessories", "The finishing touches."));

            products.save(new Product("Ceramic Table Vase", "A handmade-look ceramic vase with a soft matte finish.",
                    new BigDecimal("34.00"), "https://images.unsplash.com/photo-1578500494198-246f612d3b3d?auto=format&fit=crop&w=900&q=85", 18, home));
            products.save(new Product("Linen Cushion Cover", "Textured natural linen cushion cover, sized for a 45 cm insert.",
                    new BigDecimal("28.00"), "https://images.unsplash.com/photo-1584100936595-c0654b55a2e2?auto=format&fit=crop&w=900&q=85", 25, home));
            products.save(new Product("Everyday Headphones", "Comfortable wireless headphones with clear, balanced sound.",
                    new BigDecimal("89.00"), "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?auto=format&fit=crop&w=900&q=85", 12, tech));
            products.save(new Product("Portable Speaker", "A compact Bluetooth speaker for music at home or on the go.",
                    new BigDecimal("64.00"), "https://images.unsplash.com/photo-1608043152269-423dbba4e7e1?auto=format&fit=crop&w=900&q=85", 16, tech));
            products.save(new Product("Canvas Weekend Tote", "A durable everyday tote made from heavyweight cotton canvas.",
                    new BigDecimal("42.00"), "https://images.unsplash.com/photo-1590874103328-eac38a683ce7?auto=format&fit=crop&w=900&q=85", 20, accessories));
            products.save(new Product("Minimal Desk Lamp", "A clean-lined desk lamp with warm, comfortable lighting.",
                    new BigDecimal("76.00"), "https://images.unsplash.com/photo-1507473885765-e6ed057f782c?auto=format&fit=crop&w=900&q=85", 9, home));
        };
    }
}
