package com.achavez.adminsys.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.achavez.adminsys.entity.Infringement;

@Repository
public interface InfringementRepository extends JpaRepository<Infringement, Integer> {
	List<Infringement> findByDniContaining(String dni, Pageable page);

	Infringement findByDni(String dni);
}
