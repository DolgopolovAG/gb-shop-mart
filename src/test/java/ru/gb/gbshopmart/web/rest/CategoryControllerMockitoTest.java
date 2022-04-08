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
import ru.gb.gbapi.category.dto.CategoryDto;
import ru.gb.gbshopmart.service.CategoryService;

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
class CategoryControllerMockitoTest {

    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    List<CategoryDto> categorys = new ArrayList<>();

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        categorys.add(CategoryDto.builder().id(1L).title("Еда").build());
        categorys.add(CategoryDto.builder().id(2L).title("Напитки").build());

        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    void mockMvcGetCategoryListTest() throws Exception {

        given(categoryService.findAll()).willReturn(categorys);

        mockMvc.perform(get("/api/v1/category"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("id")))
                .andExpect(jsonPath("$.[0].id").value("1"))
                .andExpect(jsonPath("$.[0].title").value("Еда"))
                .andExpect(jsonPath("$.[1].id").value("2"));

    }

    @Test
    void testSaveCategoryTest() throws Exception {

        given(categoryService.save(any())).willReturn(new CategoryDto(3L, "Бакалея"));

        mockMvc.perform(post("/api/v1/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Бакалея\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void getCategoryListTest() {
        // given
        given(categoryService.findAll()).willReturn(categorys);

        // when
        List<CategoryDto> categoryList = categoryController.getCategoryList();

        // then
        then(categoryService).should().findAll();

        assertAll(
                () -> assertEquals(2, categoryList.size(), "Size must be equals 2"),
                () -> assertEquals("Еда", categoryList.get(0).getTitle())
        );
    }
}