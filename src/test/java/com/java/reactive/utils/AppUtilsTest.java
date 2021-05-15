package com.java.reactive.utils;

import com.java.reactive.dto.ProductDto;
import com.java.reactive.entity.Product;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
class AppUtilsTest {

    private Product product;
    private ProductDto productDto;

    @BeforeEach
    void setUp(){
        product = new Product("1","mobile",1,200);
        productDto = new ProductDto("1","mobile",1,200);
    }

    @Test
    public void returnEntityToDto(){
        ProductDto result = AppUtils.entityToDto(product);
        Assert.assertEquals(result,productDto);
    }

    @Test
    public void returnDtoEntity(){
        Product result = AppUtils.dtoToEntity(productDto);
        Assert.assertEquals(result,product);
    }
}
