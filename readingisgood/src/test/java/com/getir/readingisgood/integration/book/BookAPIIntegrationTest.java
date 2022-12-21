package com.getir.readingisgood.integration.book;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.getir.readingisgood.ReadingIsGoodApplication;
import com.getir.readingisgood.common.ServiceTestSupport;

@SpringBootTest(classes = ReadingIsGoodApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class BookAPIIntegrationTest extends ServiceTestSupport {

	@Test
	void contextLoads() {
	}
	
}
