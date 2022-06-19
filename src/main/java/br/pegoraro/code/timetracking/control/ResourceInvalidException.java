package br.pegoraro.code.timetracking.control;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceInvalidException extends RuntimeException {

	public ResourceInvalidException(String message) {
        super(message);
    }

}
