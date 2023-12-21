package com.achavez.adminsys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.achavez.adminsys.entity.Infringement;
import com.achavez.adminsys.exceptions.NoDataFoundException;
import com.achavez.adminsys.exceptions.ValidateServiceException;
import com.achavez.adminsys.repository.InfringementRepository;
import com.achavez.adminsys.service.InfringementService;
import com.achavez.adminsys.validator.InfringementValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InfringementServiceImpl implements InfringementService {
	@Autowired
	private InfringementRepository repo;

	@Override
	@Transactional(readOnly = true)
	public List<Infringement> findAll(Pageable page) {
		try {
			return repo.findAll(page).toList();
		} catch (NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Infringement findById(int id) {
		try {
			return repo.findById(id)
					.orElseThrow(() -> new NoDataFoundException("The record with that id does not exist!"));

		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Infringement> findByDni(String name, Pageable page) {
		try {
			return repo.findByDniContaining(name, page);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	@Transactional
	public Infringement create(Infringement obj) {
		try {
			InfringementValidator.save(obj);
			return repo.save(obj);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	@Transactional
	public Infringement update(Infringement obj) {
		try {
			InfringementValidator.save(obj);
			Infringement infr = repo.findById(obj.getId())
					.orElseThrow(() -> new NoDataFoundException("The record with that id does not exist!"));

			Infringement infrD = repo.findByDni(obj.getDni());

			if (repo.findByDni(obj.getDni()) != null && infrD.getId() != obj.getId()) {
				throw new ValidateServiceException("The infregory " + obj.getDni() + " already exists!");
			}
			infr.setDni(obj.getDni());
			infr.setDescription(obj.getDescription());
			infr.setDat(obj.getDat());
			infr.setPlate(obj.getPlate());
			infr.setInfringement(obj.getInfringement());
			repo.save(infr);
			return infr;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	@Transactional
	public int delete(int id) {
		try {
			repo.deleteById(id);
			return 1;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}
}
