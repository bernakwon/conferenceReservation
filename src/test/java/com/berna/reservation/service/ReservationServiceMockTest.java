package com.berna.reservation.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.berna.domain.reservation.dao.ReservationFindDao;
import com.berna.domain.reservation.service.ReservationComparedByFilter;
import com.berna.domain.reservation.service.ReservationComparedByHash;
import com.berna.domain.reservation.service.ReservationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.berna.global.error.exception.ReservationReduplicationException;
import com.berna.global.error.exception.TimeErrorException;
import com.berna.domain.reservation.dto.ReservationRegistParam;
import com.berna.domain.conference.domain.Conference;
import com.berna.domain.reservation.domain.ReservatedDate;
import com.berna.domain.reservation.domain.Reservation;
import com.berna.domain.reservation.domain.ReservationDetail;
import com.berna.domain.reservation.domain.ReservationDetailPrimaryKey;
import com.berna.domain.reservation.dao.ReservatedDateRepository;
import com.berna.domain.reservation.dao.ReservationDetailRepository;
import com.berna.domain.reservation.dao.ReservationRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ReservationServiceMockTest {

	@InjectMocks
	ReservationComparedByHash reservationService;
	@InjectMocks
	ReservationComparedByFilter ReservationComparedByFilter;
	@InjectMocks
	ReservationFindDao reservationFindDao;
	@Mock
	ReservationRepository reservationRepository;
	@Mock
	ReservatedDateRepository reservatedDateRepository;
	@Mock
	ReservationDetailRepository reservationDetailRepository;
	
	/*테스트 RegistParam*/
	private ReservationRegistParam testRegistParam() {
		return ReservationRegistParam.builder().conferenceId(1L).endTime("14:00").startTime("12:00")
				.reservationDate(LocalDate.now()).repetitionOfNum(1).reservationId(3L).reservationName("testConference")
				.registedName("testRegistedName").build();
	}
	
	/*테스트 ReservatedDate*/
	private ReservatedDate testReservatedDate() {

		return ReservatedDate.builder().id(1L).reservationDate(LocalDate.now()).build();
	}
	
	/*테스트 ReservationDetail*/
	private ReservationDetail testReservationDetail() {

		return ReservationDetail.builder().reservationDetailPrimaryKey(ReservationDetailPrimaryKey.builder()
				.conference(Conference.builder().id(1L).build()).reservatedDate(testReservatedDate()).reservation(testReservation()).build()).build();
	}
	
	/*테스트 Reservation*/
	private Reservation testReservation() {

		return Reservation.builder().id(3L).build();
	}
	
	/**
	 * 예약목록 테스트
	 * */
	@Test
	public void success_getReservationList() {
		
		when(reservatedDateRepository.findByReservationDate(any(LocalDate.class))).thenReturn(testReservatedDate());
		
		ReservatedDate resultReservatedDate = reservationFindDao.getReservationList(LocalDate.now());
		
		assertThat(resultReservatedDate).isNotNull();
		
		verify(reservatedDateRepository,times(1)).findByReservationDate(any(LocalDate.class));
	}

	/**
	 * 성공적인 예약저장
	 * */
	@Test
	public void success_when_is_not_duplicated_time() throws Exception {

		List<LocalDate> reservatedDate = new ArrayList<LocalDate>();
		reservatedDate.add(LocalDate.now());
		when(reservationDetailRepository
				.countByReservationDetailPrimaryKeyConferenceIdAndReservationDetailPrimaryKeyReservatedDateReservationDateInAndStartTimeLessThanAndEndTimeGreaterThan(
						testRegistParam().getConferenceId(), reservatedDate, testRegistParam().getEndTime(),
						testRegistParam().getStartTime())).thenReturn(0);
		when(reservationRepository.save(any(Reservation.class))).thenReturn(testReservation());
		when(reservatedDateRepository.save(any(ReservatedDate.class))).thenReturn(testReservatedDate());
		when(reservationDetailRepository.save(any(ReservationDetail.class))).thenReturn(testReservationDetail());

		Long id = reservationService.create(testRegistParam());

		assertThat(id).isNotNull();

		verify(reservationRepository, times(1)).save(any(Reservation.class));
		verify(reservationDetailRepository, times(1))
				.countByReservationDetailPrimaryKeyConferenceIdAndReservationDetailPrimaryKeyReservatedDateReservationDateInAndStartTimeLessThanAndEndTimeGreaterThan(
						testRegistParam().getConferenceId(), reservatedDate, testRegistParam().getEndTime(),
						testRegistParam().getStartTime());
		verify(reservatedDateRepository, times(1)).save(any(ReservatedDate.class));
		verify(reservationDetailRepository, times(1)).save(any(ReservationDetail.class));
	}
	

	/**
	 * 중복오류 발생
	 * */
	@Test(expected= ReservationReduplicationException.class)
	public void must_throws_ReservationReduplicationException_when_reservation_was_reduplicated() throws Exception {
		List<LocalDate> reservatedDate = new ArrayList<LocalDate>();
		reservatedDate.add(LocalDate.now());
		when(reservationDetailRepository
				.countByReservationDetailPrimaryKeyConferenceIdAndReservationDetailPrimaryKeyReservatedDateReservationDateInAndStartTimeLessThanAndEndTimeGreaterThan(
						testRegistParam().getConferenceId(), reservatedDate, testRegistParam().getEndTime(),
						testRegistParam().getStartTime())).thenReturn(1);
		when(reservationRepository.save(any(Reservation.class))).thenReturn(testReservation());
		when(reservatedDateRepository.save(any(ReservatedDate.class))).thenReturn(testReservatedDate());
		when(reservationDetailRepository.save(any(ReservationDetail.class))).thenReturn(testReservationDetail());
		reservationService.create(testRegistParam());

	}

	
	/**
	 * 정시/30분단위 체크 오류 발생
	 * */
	@Test(expected = TimeErrorException.class)
	public void must_throws_TimeErrorException_when_reservation_was_reduplicated() {
		ReservationRegistParam timeErrorTest = ReservationRegistParam.builder().conferenceId(1L).endTime("14:20").startTime("12:00")
		.reservationDate(LocalDate.now()).repetitionOfNum(1).reservationId(3L).reservationName("11")
		.registedName("11").build();
		reservationService.create(timeErrorTest);

	}

}
