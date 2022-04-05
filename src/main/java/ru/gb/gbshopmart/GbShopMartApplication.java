package ru.gb.gbshopmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.gb.gbshopmart.entity.Product;
import org.springframework.beans.factory.BeanCreationException;
import org.hibernate.AnnotationException;

@SpringBootApplication
public class GbShopMartApplication {

    public static void main(String[] args) {
        SpringApplication.run(GbShopMartApplication.class, args);
    }

}
