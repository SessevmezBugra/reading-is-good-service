package com.getir.readingisgood.common;

import java.nio.charset.Charset;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.getir.readingisgood.ReadingIsGoodApplication;
import com.getir.readingisgood.constant.ApiPath;
import com.getir.readingisgood.dto.LoginDto;
import com.getir.readingisgood.dto.TokenDto;

@SpringBootTest(classes = ReadingIsGoodApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class ServiceTestSupport {

	@Autowired
    protected TestRestTemplate testRestTemplate;
	
	@Autowired
	protected Environment environment;

    protected HttpHeaders getHeaderForAdmin() {
        return getHeader(environment.getProperty("admin.username"), environment.getProperty("admin.password"));
    }
    
    protected HttpHeaders getHeader(final String userName, final String password) {
        final ResponseEntity<TokenDto> response = testRestTemplate.postForEntity(ApiPath.CustomerCtrl.CTRL + "/login", new HttpEntity<>(new LoginDto(userName, password)),
        		TokenDto.class);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        headers.add("Authorization", "Bearer " + response.getBody().getToken());
        return headers;
    }
    
    
}
