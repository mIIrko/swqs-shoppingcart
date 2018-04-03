package de.htwg.swqs.cart;

import de.htwg.swqs.cart.service.CartService;
import de.htwg.swqs.cart.service.CartServiceImpl;
import de.htwg.swqs.catalog.CatalogConfiguration;
import de.htwg.swqs.catalog.repository.CatalogRepository;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration
@ComponentScan(basePackages = "de.htwg.swqs.cart")
//@EnableJpaRepositories("de.htwg.swqs.cart.repository")
@EntityScan("de.htwg.swqs.cart.model")
@Import(CatalogConfiguration.class)

public class CartConfiguration {

    @Bean
    public CartService cartService(CatalogRepository catalogRepository) {
        return new CartServiceImpl(catalogRepository);
    }

}


