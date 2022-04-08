package ru.gb.gbshopmart.web.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.gb.gbapi.product.dto.ProductDto;
import ru.gb.gbshopmart.entity.Manufacturer;
import ru.gb.gbshopmart.entity.Product;
import ru.gb.gbshopmart.service.ProductService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// BDD (Behavior Driven Development)
@ExtendWith(MockitoExtension.class)
class ProductControllerMockitoTest {

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController;

    List<ProductDto> products = new ArrayList<>();

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        products.add(ProductDto.builder().id(1L).title("Вода").cost(new BigDecimal(100)).build());
        products.add(ProductDto.builder().id(2L).title("Хлеб").cost(new BigDecimal(20)).build());

        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void mockMvcGetProductListTest() throws Exception {

        given(productService.findAll()).willReturn(products);

        mockMvc.perform(get("/api/v1/product"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("id")))
                .andExpect(jsonPath("$.[0].id").value("1"))
                .andExpect(jsonPath("$.[0].title").value("Вода"))
                .andExpect(jsonPath("$.[1].id").value("2"));

    }


    @Test
    void getProductListTest() {
        // given
        given(productService.findAll()).willReturn(products);

        // when
        List<ProductDto> productList = productController.getProductList();

        // then
        then(productService).should().findAll();

        assertAll(
                () -> assertEquals(2, productList.size(), "Size must be equals 2"),
                () -> assertEquals("Вода", productList.get(0).getTitle())
        );
    }
}