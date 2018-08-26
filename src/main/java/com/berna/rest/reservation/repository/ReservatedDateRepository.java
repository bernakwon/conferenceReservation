package com.berna.rest.reservation.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.berna.rest.reservation.domain.ReservatedDate;


/**
 * @author hrkwon
 * @interfaceName ReservatedDateRepository
 * 
 */
public interface ReservatedDateRepository extends JpaRepository<ReservatedDate,Long>{
	
	/**
	 * @author hrkwon
	 * @return ReservatedDate
	 * @param LocalDate
	 * @description 예약날짜로 예약데이터 조회
	 * 
	 */
	ReservatedDate findByReservationDate(LocalDate reservationDate);
	
}
