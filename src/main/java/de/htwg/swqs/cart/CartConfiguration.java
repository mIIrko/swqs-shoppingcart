package de.htwg.swqs.cart;

import de.htwg.swqs.cart.service.CartService;
import de.htwg.swqs.cart.service.CartServiceImpl;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@ComponentScan(basePackages = "de.htwg.swqs.cart")
//@EnableJpaRepositories("de.htwg.swqs.cart.repository")
@EntityScan("de.htwg.swqs.cart.model")
public class CartConfiguration {

    @Bean
    public CartService cartService() {
        return new CartServiceImpl();
    }

}


