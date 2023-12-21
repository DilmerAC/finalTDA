package com.achavez.adminsys.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.achavez.adminsys.converter.InfringementConverter;
import com.achavez.adminsys.dto.InfringementDTO;
import com.achavez.adminsys.entity.Infringement;
import com.achavez.adminsys.service.InfringementService;
import com.achavez.adminsys.utils.WrapperResponse;


@RestController
@RequestMapping("/api/infringements")
public class InfringementApi {
	@Autowired
	private InfringementService service;

	@Autowired
	private InfringementConverter converter;

	@GetMapping()
	public ResponseEntity<WrapperResponse<List<InfringementDTO>>> getAll(
			@RequestParam(value = "name", required = false, defaultValue = "") String name,
			@RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize) {

		Pageable page = PageRequest.of(pageNumber, pageSize);
		List<Infringement> infringements;
		if (name == null) {
			infringements = service.findAll(page);
		} else {
			infringements = service.findByDni(name, page);
		}

		List<InfringementDTO> infringementsDTO = converter.fromEntity(infringements);

		return new WrapperResponse<List<InfringementDTO>>(true, "success", infringementsDTO).createResponse(HttpStatus.OK);

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<WrapperResponse<InfringementDTO>> getById(@PathVariable("id") int id) {
		Infringement infr = service.findById(id);
		InfringementDTO infrDTO = converter.fromEntity(infr);

		return new WrapperResponse<InfringementDTO>(true, "succes", infrDTO).createResponse(HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<WrapperResponse<InfringementDTO>> create(@RequestBody InfringementDTO infrDTO) {
		Infringement infrDb = service.create(converter.fromDTO(infrDTO));
		InfringementDTO infrDTORes = converter.fromEntity(infrDb);
		return new WrapperResponse<InfringementDTO>(true, "success", infrDTORes).createResponse(HttpStatus.CREATED);

	}

	@PutMapping
	public ResponseEntity<WrapperResponse<InfringementDTO>> update(@RequestBody InfringementDTO infrDTO) {
		Infringement infrDb = service.update(converter.fromDTO(infrDTO));
		InfringementDTO infrDTORes = converter.fromEntity(infrDb);

		return new WrapperResponse<InfringementDTO>(true, "success", infrDTORes).createResponse(HttpStatus.CREATED);

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<WrapperResponse<InfringementDTO>> delete(@PathVariable("id") int id) {
		service.delete(id);
		return new WrapperResponse<InfringementDTO>(true, "success", null).createResponse(HttpStatus.OK);

	}
}
