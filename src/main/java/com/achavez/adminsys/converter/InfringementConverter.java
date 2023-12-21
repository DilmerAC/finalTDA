package com.achavez.adminsys.converter;

import org.springframework.stereotype.Component;

import com.achavez.adminsys.dto.InfringementDTO;
import com.achavez.adminsys.entity.Infringement;

@Component
public class InfringementConverter extends AbstractConverter<Infringement, InfringementDTO> {

	@Override
	public InfringementDTO fromEntity(Infringement entity) {
		if (entity == null)
			return null;
		return InfringementDTO.builder().id(entity.getId()).dni(entity.getDni()).plate(entity.getPlate())
				.dat(entity.getDat()).infringement(entity.getInfringement()).description(entity.getDescription())
				.build();
	}

	@Override
	public Infringement fromDTO(InfringementDTO dto) {
		if (dto == null)
			return null;
		return Infringement.builder().id(dto.getId()).dni(dto.getDni()).plate(dto.getPlate()).dat(dto.getDat())
				.infringement(dto.getInfringement()).description(dto.getDescription()).build();
	}

}
