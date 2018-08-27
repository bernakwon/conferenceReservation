package com.berna.rest.reservation.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import com.berna.rest.conference.domain.Conference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Embeddable
@Getter@Setter
public class ReservationDetailPrimaryKey implements Serializable{


	private static final long serialVersionUID = 1L;
	
	/**
	 * 회의실 , Conference와 n:1 관계, Json조회시 회의실정보가 조회된다.
	 * 
	 * */
	@ManyToOne
	@JoinColumn(name="CONFERENCE_ID")
	@JsonManagedReference
	private Conference conference;
	
	/**
	 * 예약일 , ReservatedDate와 n:1 관계, Json조회시 ReservationDetail쪽에서는 조회되지 않는다
	 * 
	 **/
	@ManyToOne
    @JoinColumn(name = "RESERVATED_DATE_ID", nullable = false, updatable = false)
	@JsonBackReference
	private ReservatedDate reservatedDate;

	/**
	 * 예약메인 , Reservation와 1:1 관계, Json조회시 예약정보가 조회된다.
	 * 
	 **/
	@OneToOne
	@JoinColumn(name="RESERVATION_ID")
	private Reservation reservation;

	@Builder
	public ReservationDetailPrimaryKey(Conference conference, ReservatedDate reservatedDate, Reservation reservation) {
		super();
		this.conference = conference;
		this.reservatedDate = reservatedDate;
		this.reservation = reservation;
	}

	public ReservationDetailPrimaryKey() {
		super();
	}
	
	
}
