package com.getir.readingisgood.integration.customer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.getir.readingisgood.ReadingIsGoodApplication;
import com.getir.readingisgood.common.ServiceTestSupport;
import com.getir.readingisgood.common.TestUser;
import com.getir.readingisgood.constant.ApiPath;
import com.getir.readingisgood.dto.RegistrationDto;

@SpringBootTest(classes = ReadingIsGoodApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class CustomerAPIIntegrationTest extends ServiceTestSupport {
	
	@Autowired
    protected TestRestTemplate testRestTemplate;

	protected void registerCustomers(final String userName, final String password) {
        testRestTemplate.postForEntity(ApiPath.CustomerCtrl.CTRL + "/register", new HttpEntity<>(new RegistrationDto(userName, password)),
        		Void.class);
    }
	
	@Test
	void contextLoads() {
//		registerCustomers(TestUser.TestUser1.getUsername(), TestUser.TestUser1.getPassword());
	}
}
