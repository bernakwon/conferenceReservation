package com.berna.rest.reservation.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.berna.exception.ReservationReduplicationException;
import com.berna.exception.TimeErrorException;
import com.berna.rest.ReservationRegistParam;
import com.berna.rest.conference.domain.Conference;
import com.berna.rest.reservation.domain.ReservatedDate;
import com.berna.rest.reservation.domain.Reservation;
import com.berna.rest.reservation.domain.ReservationDetail;
import com.berna.rest.reservation.domain.ReservationDetailPrimaryKey;
import com.berna.rest.reservation.repository.ReservatedDateRepository;
import com.berna.rest.reservation.repository.ReservationDetailRepository;
import com.berna.rest.reservation.repository.ReservationRepository;

/**
 * @author hrkwon
 * @className ReservationServiceImpl
 * 
 */
@Service("reservationService")
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	ReservatedDateRepository reservatedDateRepository;

	@Autowired
	ReservationDetailRepository reservationDetailRepository;

	/**
	 * @author hrkwon
	 * @return ReservatedDate
	 * @param LocalDate
	 * @Description 예약일 기준으로 예약목록을 조회한다.
	 * 
	 */
	@Override
	public ReservatedDate getReservationList(LocalDate reservationDate) {

		return reservatedDateRepository.findByReservationDate(reservationDate);
	}

	/**
	 * @author hrkwon
	 * @return Long
	 * @param ReservationRegistParam
	 * @Description 예약을 생성한다.
	 * 
	 */
	@Transactional
	@Override
	public Long create(ReservationRegistParam reservationRegistParam) {
		Long resultId = 0L;
		// 30분/정시, 시간체크
		if (!isthirtyMinuteCheck(reservationRegistParam.getStartTime(), reservationRegistParam.getEndTime())) {
			throw new TimeErrorException("The minute unit is 30 minutes.");
		}

		// 중복체크
		if (isDuplicationCheck(reservationRegistParam)) {

			resultId = upsertReservation(reservationRegistParam);

		} else {
			throw new ReservationReduplicationException("It is already booked. Please select another time.");
		}
		return resultId;
	}

	/**
	 * @author hrkwon
	 * @return Long
	 * @param Reservation , ReservationRegistParam
	 * @Description 예약저장 보조 method
	 * 
	 */
	public Long upsertReservation(ReservationRegistParam reservationRegistParam) {
		Reservation saveReservation = Reservation.builder().registedName(reservationRegistParam.getRegistedName())
				.repetitionOfNum(reservationRegistParam.getRepetitionOfNum())
				.reservationName(reservationRegistParam.getReservationName()).build();

		Reservation resultReservation = reservationRepository.save(saveReservation);

		saveReservatedDatesAndDetails(resultReservation, reservationRegistParam);

		return resultReservation.getId();
	}

	/**
	 * @author hrkwon
	 * @return void
	 * @param Reservation , ReservationRegistParam
	 * @Description 예약일과 예약상세를 수정한다.
	 * 
	 */
	public void saveReservatedDatesAndDetails(Reservation saveReservation,
			ReservationRegistParam reservationRegistParam) {

		List<LocalDate> reservatedDates = culculateDates(reservationRegistParam.getReservationDate(),
				reservationRegistParam.getRepetitionOfNum());

		for (LocalDate reservatedDate : reservatedDates) {

			ReservatedDate saveReservatedDate = upsertReservatedDate(reservatedDate);

			ReservationDetailPrimaryKey pk = ReservationDetailPrimaryKey.builder()
					.conference(Conference.builder().id(reservationRegistParam.getConferenceId()).build()).reservatedDate(saveReservatedDate)
					.reservation(saveReservation).build();

			ReservationDetail detail = upsertReservationDetail(reservationRegistParam, pk);
			reservationDetailRepository.save(detail);
		}
	}

	/**
	 * @author hrkwon
	 * @return void
	 * @param ReservationRegistParam , ReservationDetailPrimaryKey
	 * @Description 예약상세를 수정한다.
	 * 
	 */
	public ReservationDetail upsertReservationDetail(ReservationRegistParam reservationRegistParam,
			ReservationDetailPrimaryKey pk) {
		ReservationDetail detail = reservationDetailRepository.findByReservationDetailPrimaryKey(pk);
		if (detail == null) {

			detail = ReservationDetail.builder().endTime(reservationRegistParam.getEndTime())
					.startTime(reservationRegistParam.getStartTime()).reservationDetailPrimaryKey(pk).build();
		}
		return detail;
	}

	/**
	 * @author hrkwon
	 * @return ReservatedDate
	 * @param LocalDate
	 * @Description 예약일이 예약일 관리 테이블에 존재하는지 체크 후 , 없으면 생성한다.
	 * 
	 */
	public ReservatedDate upsertReservatedDate(LocalDate reservatedDate) {

		ReservatedDate resultReservatedDate = reservatedDateRepository.findByReservationDate(reservatedDate);
		if (resultReservatedDate == null) {

			ReservatedDate saveReservatedDate = ReservatedDate.builder().reservationDate(reservatedDate).build();
			reservatedDateRepository.save(saveReservatedDate);
			return saveReservatedDate;
		}
		return resultReservatedDate;
	}

	/**
	 * @author hrkwon
	 * @return List<LocalDate>
	 * @param LocalDate,int
	 * @Description 반복체크시 예약일 리스트를 생성한다.
	 * 
	 */
	public List<LocalDate> culculateDates(LocalDate reservationDate, int repetitionOfNum) {
		List<LocalDate> culculateDateList = new ArrayList<LocalDate>();

		for (int loopCnt = 0; loopCnt < repetitionOfNum; loopCnt++) {
			culculateDateList.add(reservationDate.plusWeeks(loopCnt));
		}
		return culculateDateList;
	}

	/**
	 * @author hrkwon
	 * @return Boolean
	 * @param ReservationRegistParam
	 * @Description 예약이 중복인지 체크
	 * 
	 */
	public Boolean isDuplicationCheck(ReservationRegistParam reservationRegistParam) {
		Boolean FLAG = true;
		List<LocalDate> reservatedDates = culculateDates(reservationRegistParam.getReservationDate(),
				reservationRegistParam.getRepetitionOfNum());
		int duplicationCheck = reservationDetailRepository
				.countByReservationDetailPrimaryKeyConferenceIdAndReservationDetailPrimaryKeyReservatedDateReservationDateInAndStartTimeLessThanAndEndTimeGreaterThan(
						reservationRegistParam.getConferenceId(), reservatedDates, reservationRegistParam.getEndTime(),
						reservationRegistParam.getStartTime());

		if (duplicationCheck > 0) {
			FLAG = false;
		}

		return FLAG;
	}

	/**
	 * @author hrkwon
	 * @return Boolean
	 * @param startTime,endTime
	 * @Description 분단위가 정시나 30분 단위인지 체크
	 * 
	 */
	public Boolean isthirtyMinuteCheck(String startTime, String endTime) {
		Boolean thirtyMinuteCheck = false;
		int compareStartTimeMinute = LocalTime.parse(startTime).getMinute();
		int compareEndTimeMinute = LocalTime.parse(endTime).getMinute();
		if ((compareStartTimeMinute == 0 || compareStartTimeMinute == 30)
				&& (compareEndTimeMinute == 0 || compareEndTimeMinute == 30)) {
			thirtyMinuteCheck = true;
		}
		return thirtyMinuteCheck;
	}

}
