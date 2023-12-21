package com.achavez.adminsys.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfringementDTO {
	private int id;
	private String dni;
	private String plate;
	private String description;
	private String infringement;
	private Date dat;
}
