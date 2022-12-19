package com.getir.readingisgood.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.getir.readingisgood.constant.ApiPath;
import com.getir.readingisgood.dto.LoginDto;
import com.getir.readingisgood.dto.OrderDto;
import com.getir.readingisgood.dto.PageDto;
import com.getir.readingisgood.dto.RegistrationDto;
import com.getir.readingisgood.dto.TokenDto;
import com.getir.readingisgood.entity.UserEntity;
import com.getir.readingisgood.service.OrderService;
import com.getir.readingisgood.service.UserService;
import com.getir.readingisgood.util.JwtTokenUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiPath.CustomerCtrl.CTRL)
@RequiredArgsConstructor
public class CustomerRestController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;
    private final OrderService orderService;
    
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        final String token = jwtTokenUtil.generateToken(loginDto.getEmail());
        return ResponseEntity.ok(new TokenDto(loginDto.getEmail(), token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Void> register(@RequestBody RegistrationDto registrationDto) {
         userService.register(registrationDto);
        return ResponseEntity.ok().build();
    }
    
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public ResponseEntity<PageDto<OrderDto>> getOrders(Pageable pageable) {
    	String username = SecurityContextHolder.getContext().getAuthentication().getName();
    	UserEntity foundUser = userService.findByEmail(username);
    	PageDto<OrderDto> orderPage = orderService.findByUserId(foundUser.getId(), pageable);
        return ResponseEntity.ok(orderPage);
    }
    
}
