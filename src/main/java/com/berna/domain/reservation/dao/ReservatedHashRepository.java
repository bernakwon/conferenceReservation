package com.berna.domain.reservation.dao;

import com.berna.domain.reservation.domain.ReservatedHash;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


/**
 * @author hrkwon
 * @interfaceName ReservatedHashRepository
 * 
 */
public interface ReservatedHashRepository extends JpaRepository<ReservatedHash,Long>{
	
	/**
	 * @author hrkwon
	 * @return ReservatedDate
	 * @param LocalDate
	 * @description 예약해시값으로 예약데이터 조회
	 * 
	 */
	ReservatedHash findByReservatedHash(String reservatedHash);

    ReservatedHash findByReservatedHashIn(List<String> reservatedIndex);

	int countByReservatedHashIn(List<String> reservatedIndex);
}
