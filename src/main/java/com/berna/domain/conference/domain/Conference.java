package com.berna.domain.conference.domain;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.berna.domain.reservation.domain.ReservationDetail;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;

/**
 * @author hrkwon
 * @className Conference
 * @description 회의실관리
 * **/
@Entity
@Getter
public class Conference {
	
	/*ID*/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	/*회의실이름*/
	@Column(name="CONFERENCE_NAME")
	private String conferenceName;
	
	/**
	 * ReservationDetail과 1:n관계, Json조회시 Conference쪽에서는 조회되지 않는다
	 * */
	@OneToMany(mappedBy = "reservationDetailPrimaryKey.conference", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.EAGER)
	@JsonBackReference
	private Set<ReservationDetail> reservationDetail = new LinkedHashSet<>();
	
	@Builder
	public Conference(long id) {
		super();
		this.id = id;
	}

	public Conference() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
