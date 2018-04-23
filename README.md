[![pipeline status](https://gitlab.in.htwg-konstanz.de/mibay/swqs-shoppingcart/badges/master/pipeline.svg)](https://gitlab.in.htwg-konstanz.de/mibay/swqs-shoppingcart/commits/master)
[![coverage report](https://gitlab.in.htwg-konstanz.de/mibay/swqs-shoppingcart/badges/master/coverage.svg)](https://gitlab.in.htwg-konstanz.de/mibay/swqs-shoppingcart/commits/master)

# swqs-shopping-cart

### description
This component got a dependency to the catalog service. It's included in the `lib` folder.
### usage
All interfaces the component owns are defined in the `CatalogService` and can be called through this.

### build instructions
To build this project run `mvn install`. This runs also all unit and integration tests.

### reports
The project includes multiple maven code analyzing plugins. When running `mvn site` a html site with all reports, test results and the javadoc will be generated.

