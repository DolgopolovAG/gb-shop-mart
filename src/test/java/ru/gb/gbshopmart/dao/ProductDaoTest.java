package ru.gb.gbshopmart.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.gb.gbshopmart.config.ShopConfig;
import ru.gb.gbshopmart.entity.Manufacturer;
import ru.gb.gbshopmart.entity.Product;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(ShopConfig.class)
class ProductDaoTest {

    private static String PRODUCT_TITTLE = "Вода";

    private static String MANUFACTURER_NAME = "Tesla";

    @Autowired
    ProductDao productDao;

    @Test
    public void saveTest() {
        Manufacturer manufacturer  = Manufacturer.builder().name(MANUFACTURER_NAME).build();
        Product product = Product.builder().title(PRODUCT_TITTLE).cost(new BigDecimal(100)).
        manufactureDate(LocalDate.of(2021,2,1)).manufacturer(manufacturer).build();

        Product savedProduct = productDao.save(product);

        assertAll(
                () -> assertEquals(1L, savedProduct.getId()),
                () -> assertEquals(PRODUCT_TITTLE, savedProduct.getTitle()),
                () -> assertEquals(new BigDecimal(100), savedProduct.getCost()),
                () -> assertEquals(LocalDate.of(2021,2,1), savedProduct.getManufactureDate()),
                () -> assertEquals(0, savedProduct.getVersion()),
                () -> assertEquals("User", savedProduct.getCreatedBy()),
                () -> assertEquals("User", savedProduct.getLastModifiedBy()),
                () -> assertNotNull(savedProduct.getCreatedDate()),
                () -> assertNotNull(savedProduct.getLastModifiedDate())
        );
    }

}