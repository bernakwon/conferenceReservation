package com.berna.rest.reservation.service;

import java.time.LocalDate;
import com.berna.rest.ReservationRegistParam;
import com.berna.rest.reservation.domain.ReservatedDate;

/**
 * @author hrkwon
 * @interfaceName ReservationService
 * 
 */
public interface ReservationService {

	public ReservatedDate getReservationList(LocalDate localDate);

	public Long create(ReservationRegistParam ReservationRegistParam);

}
