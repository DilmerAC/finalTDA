package com.achavez.adminsys.converter;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractConverter<E, D> {

	public abstract D fromEntity(E entity);

	public abstract E fromDTO(D dto);

	public List<D> fromEntity(List<E> entities) {
		return entities.stream()
				.map(e -> fromEntity(e))
				.collect(Collectors.toList());

	}

	public  List<E> fromDTO(List<D> DTOs) {
		return DTOs.stream()
				.map(d -> fromDTO(d))
				.collect(Collectors.toList());
	}
}
