//package com.java.reactive.service;
//
//import com.java.reactive.dto.ProductDto;
//import com.java.reactive.entity.Product;
//import com.java.reactive.repository.ProductRepository;
//import com.java.reactive.utils.AppUtils;
//import org.junit.Assert;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.reactivestreams.Publisher;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.junit4.SpringRunner;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//import reactor.test.StepVerifier;
//
//@DataMongoTest
//@RunWith(SpringRunner.class)
//class ProductServiceTest {
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    private ProductService productService;
//
//    private Mono<ProductDto> productDtoMono;
//    private Flux<ProductDto> productDtoFlux;
//    private ProductDto productDto;
//    private Product product;
//
//    @BeforeEach
//    void setUp() {
//        productService = new ProductService(productRepository);
//        productDto = new ProductDto("120", "Mobile", 1, 100);
//        productDtoMono = Mono.just(productDto);
//        productDtoFlux = Flux.just(productDto,productDto);
//        product = new Product("120", "Mobile", 1, 10022);
//    }
////
////    @Test
////    public void getProducts() {
////        productRepository.save(product);
////        Flux<Product> response = productRepository.findAll();
////        response.collectList().subscribe(p->Assert.assertEquals(p.get(0).getName(),product.getName()));
//////        StepVerifier
//////                .create(response)
//////                .expectSubscription()
//////                .expectNextMatches(p->p.getName().equals(productDto.getName()))
//////                .verifyComplete();
////
////    }
//
//    @Test
//    public void getProducts() {
//
//        Publisher<ProductDto> setup =  this.productRepository.deleteAll().thenMany(productDtoFlux);
//
//        Flux<Product> response = this.productRepository.findAll();
//
//        Publisher<ProductDto> composite = Flux.from(response).thenMany(setup);
//
//        StepVerifier.create(composite).expectNext(productDto,productDto).verifyComplete();
//
//    }
//}
