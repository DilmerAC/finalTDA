package com.achavez.adminsys.validator;

import com.achavez.adminsys.entity.Infringement;
import com.achavez.adminsys.exceptions.ValidateServiceException;

public class InfringementValidator {

	public static void save(Infringement infringement) {
		if (infringement.getDni() == null || infringement.getDni().isEmpty()) {
			throw new ValidateServiceException("The name is required!");
		}

		if (infringement.getDni().length() > 8) {
			throw new ValidateServiceException("You use 100 characters as max!");
		}
	}

}
