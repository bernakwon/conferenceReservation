package com.berna.domain.reservation.controller;

import java.time.LocalDate;

import com.berna.domain.reservation.dao.ReservationFindDao;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.berna.domain.reservation.dto.ReservationRegistParam;
import com.berna.domain.reservation.domain.ReservatedDate;
import com.berna.domain.reservation.service.ReservationService;

/**
 * @author hrkwon
 * @className ReservationController
 * @description 예약을 조회,저장 데이터호출
 */
@RestController
@RequiredArgsConstructor
public class ReservationController {
	Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

	private final ReservationService reservationService;
	private final ReservationFindDao reservationFindDao;
	/**
	 * @author hrkwon
	 * @method Get
	 * @param LocalDate
	 * @return ReservatedDate
	 * @Description 예약일 기준으로 예약목록을 조회한다.
	 */
	@GetMapping("/reservation")
	public ReservatedDate getReservationList(@RequestParam("reservationDate") String reservationDate) throws Exception {
		return reservationFindDao.getReservationList(LocalDate.parse(reservationDate));

	}


	/**
	 * @author hrkwon
	 * @method Post
	 * @param ReservationRegistParam
	 * @return Long
	 * @Description 예약을 저장
	 */
	@PostMapping("/reservation/new")
	public Long create(@RequestBody ReservationRegistParam ReservationRegistParam) throws Exception {
		return reservationService.create(ReservationRegistParam);

	}

}
