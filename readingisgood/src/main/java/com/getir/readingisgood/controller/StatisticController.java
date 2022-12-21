package com.getir.readingisgood.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.getir.readingisgood.common.RestResponse;
import com.getir.readingisgood.constant.ApiPath;
import com.getir.readingisgood.dto.MonthlyOrderStatisticDto;
import com.getir.readingisgood.entity.UserEntity;
import com.getir.readingisgood.service.StatisticService;
import com.getir.readingisgood.service.UserService;
import com.getir.readingisgood.util.AuthUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiPath.StatisticCtrl.CTRL)
@RequiredArgsConstructor
public class StatisticController {
	
	private final StatisticService statisticService;
	private final UserService userService;
	private final AuthUtil authUtil;
	
	@RequestMapping(value = "/monthly-order-statistic", method = RequestMethod.GET)
    public ResponseEntity<RestResponse<List<MonthlyOrderStatisticDto>>> getMonthlyOrderStatistic() {
		UserEntity foundUser = userService.findByEmail(authUtil.getUsername());
        return ResponseEntity.ok(RestResponse.of(statisticService.findMontlyOrderStatisticByUserId(foundUser.getId())));
    }

}
