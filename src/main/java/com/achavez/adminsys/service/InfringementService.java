package com.achavez.adminsys.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.achavez.adminsys.entity.Infringement;

public interface InfringementService {
	public List<Infringement> findAll(Pageable page);

	public Infringement findById(int id);

	public List<Infringement> findByDni(String dni, Pageable page);

	public Infringement create(Infringement obj);

	public Infringement update(Infringement obj);

	public int delete(int id);
}
