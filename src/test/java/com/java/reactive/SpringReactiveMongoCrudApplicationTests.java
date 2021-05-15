package com.java.reactive;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class SpringReactiveMongoCrudApplicationTests {

	@Test
	void contextLoads() {
		SpringReactiveMongoCrudApplication.main(new String[]{});
	}

}
