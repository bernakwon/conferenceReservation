package com.berna.rest.reservation.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.data.annotation.PersistenceConstructor;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * @author hrkwon
 * @className ReservatedDate
 * @description 예약일관리
 * **/
@Entity
@Table(name = "RESERVATED_DATE")
@Getter@Setter
public class ReservatedDate implements Serializable{

	private static final long serialVersionUID = 1L;

	/*ID*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/*예약일*/
	@Column(name = "RESERVATION_DATE")
	private LocalDate reservationDate;
	
	/**
	 * ReservationDetail과 1:n관계, Json조회시 ReservatedDate쪽에서 조회된다.
	 * */
	@OneToMany(mappedBy = "reservationDetailPrimaryKey.reservatedDate", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.EAGER)
	@JsonManagedReference
	private Set<ReservationDetail> reservationDetail = new LinkedHashSet<>();
	
	/*버전*/
	@Version
	private long version;

	@Builder
	public ReservatedDate(Long id,LocalDate reservationDate) {
		super();
		this.id = id;
		this.reservationDate = reservationDate;
	}
	
	
	public ReservatedDate() {
		super();

	}


}
