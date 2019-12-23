package com.berna.domain.reservation.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import com.berna.domain.reservation.domain.ReservationDetail;
import com.berna.domain.reservation.domain.ReservationDetailPrimaryKey;

/**
 * @author hrkwon
 * @interfaceName ReservationDetailRepository
 * 
 */
public interface ReservationDetailRepository extends JpaRepository<ReservationDetail, Long> {

	/**
	 * @author hrkwon
	 * @return int
	 * @description 회의ID, 예약일, 종료날짜, 시작날짜로 저장된 데이터가 있는지 중복체크
	 * 
	 */
	int countByReservationDetailPrimaryKeyConferenceIdAndReservationDetailPrimaryKeyReservatedDateReservationDateInAndStartTimeLessThanAndEndTimeGreaterThan(
			Long conferenceId, List<LocalDate> reservationDate, String endTime, String startTime);

	/**
	 * @author hrkwon
	 * @return ReservationDetail
	 */
	ReservationDetail findByReservationDetailPrimaryKey(ReservationDetailPrimaryKey pk);


	Stream<ReservationDetail> findByReservationDetailPrimaryKeyConferenceIdAndReservationDetailPrimaryKeyReservatedDateReservationDateIn(Long conferenceId, List<LocalDate> reservatedDates);
}
