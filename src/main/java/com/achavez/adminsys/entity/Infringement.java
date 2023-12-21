package com.achavez.adminsys.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "infringements")
@EntityListeners(AuditingEntityListener.class)
public class Infringement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true, nullable = false, length = 8)
	private String dni;
	
	@Column(nullable = false, length = 7)
	private String plate;
	
	@Column(nullable = false, length = 255)
	private String description;
	
	@Column(nullable = true, length = 200)
	private String infringement;
	
	@Column(name = "issued_at", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dat;

	@CreatedDate
	@Column(name = "created_at", nullable = true, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@LastModifiedDate
	@Column(name = "updated_at", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

}
