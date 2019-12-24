package com.berna.reservation.service;


import com.berna.domain.reservation.dao.ReservatedDateRepository;
import com.berna.domain.reservation.dao.ReservationDetailRepository;
import com.berna.domain.reservation.dao.ReservationRepository;
import com.berna.domain.reservation.dto.ReservationRegistParam;
import com.berna.domain.reservation.service.ReservationComparedByFilter;
import com.berna.domain.reservation.service.ReservationComparedByHash;
import com.berna.domain.reservation.service.ReservationComparedBySQL;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;

public class ReservationDuplicationTest extends MockTest{
    private static ReservationRegistParam reservationRegistParam = null;

    @InjectMocks
    ReservationComparedByHash reservationComparedByHash;
    @InjectMocks
    ReservationComparedByFilter reservationComparedByFilter;
    @InjectMocks
    ReservationComparedBySQL reservationComparedBySQL;
    @Mock
    ReservationRepository reservationRepository;
    @Mock
    ReservatedDateRepository reservatedDateRepository;
    @Mock
    ReservationDetailRepository reservationDetailRepository;

    @Before
    public void createTestParam() {
        //3번 반복되는 예약정보
        reservationRegistParam = ReservationRegistParam.builder().conferenceId(1L).endTime("14:00").startTime("12:00")
                .reservationDate(LocalDate.now()).repetitionOfNum(3).reservationId(3L).reservationName("testConference")
                .registedName("testRegistedName").build();
    }

}
