package com.berna.rest.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.berna.rest.reservation.domain.Reservation;

/**
 * @author hrkwon
 * @interfaceName ReservationRepository
 * 
 */
public interface ReservationRepository extends JpaRepository<Reservation,Long>{

}
