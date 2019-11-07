package com.berna.domain.reservation.service;

import java.time.LocalDate;
import com.berna.domain.reservation.dto.ReservationRegistParam;
import com.berna.domain.reservation.domain.ReservatedDate;

/**
 * @author hrkwon
 * @interfaceName ReservationService
 * 
 */
public interface ReservationService {

	public ReservatedDate getReservationList(LocalDate localDate);

	public Long create(ReservationRegistParam ReservationRegistParam);

}
