package com.java.reactive.controller;

import com.java.reactive.dto.ProductDto;
import com.java.reactive.service.ProductService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.net.URI;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebFluxTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ProductService productService;

    private static Mono<ProductDto> productDtoMono;
    private static Flux<ProductDto> productDtoFlux;
    private static ProductDto productDto;

    private static final String PRODUCT_PATH = "/products";

    @BeforeAll
    static void setUp() {
        productDto = new ProductDto("120", "Mobile", 1, 100);
        productDtoMono = Mono.just(productDto);
        productDtoFlux = Flux.just(productDto, productDto);
    }

    @Test
    public void getProducts() {
        when(productService.getProducts()).thenReturn(productDtoFlux);
        String url = PRODUCT_PATH;
        Flux<ProductDto> response = webTestClient
                .get()
                .uri(url)
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(ProductDto.class)
                .getResponseBody();

        StepVerifier.create(response)
                .expectSubscription()
                .expectNext(productDto)
                .expectNext(productDto)
                .verifyComplete();

    }

    @Test
    public void getProduct() {
        when(productService.getProduct("120")).thenReturn(productDtoMono);
        String url = PRODUCT_PATH + "/120";
        Flux<ProductDto> response = webTestClient
                .get()
                .uri(url)
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(ProductDto.class)
                .getResponseBody();

        StepVerifier.create(response)
                .expectSubscription()
                .expectNext(productDto)
                .verifyComplete();
    }

    @Test
    public void getProductBetweenRange(){
        double min = 10;
        double max = 101;
        String url = PRODUCT_PATH;

        URI uri = UriComponentsBuilder
                .fromUriString(url)
                .path("/product-range")
                .queryParam("min",min)
                .queryParam("max",max)
                .build()
                .toUri();

        when(productService.getProductInRange(min,max)).thenReturn(productDtoFlux);

        Flux<ProductDto> response = webTestClient
                .get()
                .uri(uri)
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(ProductDto.class)
                .getResponseBody();

        StepVerifier.create(response)
                .expectSubscription()
                .expectNextMatches(p-> p.getName().equals(productDto.getName()))
                .expectNextMatches(p-> p.getName().equals(productDto.getName()))
                .verifyComplete();
    }

    @Test
    public void addProductTest() {
        when(productService.saveProduct(productDtoMono)).thenReturn(productDtoMono);
        String url = PRODUCT_PATH;
        webTestClient
                .post()
                .uri(url)
                .body(Mono.just(productDtoMono), ProductDto.class)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void updateProduct() {
        when(productService.updateProduct(productDtoMono, productDto.getId())).thenReturn(productDtoMono);
        String url = PRODUCT_PATH + "/update/" + productDto.getId();

        webTestClient
                .put()
                .uri(url)
                .body(Mono.just(productDtoMono), ProductDto.class)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void deleteProduct() {
        when(productService.deleteProduct(productDto.getId())).thenReturn(Mono.empty());
        String url = PRODUCT_PATH + "/delete/" + productDto.getId();
        webTestClient
                .delete()
                .uri(url)
                .exchange()
                .expectStatus()
                .isOk();
    }
}

