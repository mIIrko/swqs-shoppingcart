package de.htwg.swqs.cart;

import de.htwg.swqs.cart.service.CartService;
import de.htwg.swqs.cart.service.CartServiceImpl;
import de.htwg.swqs.catalog.CatalogConfiguration;
import de.htwg.swqs.catalog.service.CatalogService;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration
@ComponentScan(basePackages = "de.htwg.swqs.cart")
@EntityScan("de.htwg.swqs.cart.model")
@Import(CatalogConfiguration.class)

public class CartConfiguration {

  @Bean
  public CartService cartService(CatalogService catalogService) {
    return new CartServiceImpl(catalogService);
  }

}


