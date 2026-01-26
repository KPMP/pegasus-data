package org.kpmp.errors;

import jakarta.servlet.http.HttpServletRequest;

import org.kpmp.logging.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Controller
@Tag(name = "Error Management", description = "API for logging client-side errors")
public class ErrorController {

	private LoggingService logger;

	@Autowired
	public ErrorController(LoggingService logger) {
		this.logger = logger;
	}

	@Operation(summary = "Log frontend error", description = "Logs client-side errors to the server for monitoring and debugging")
	@ApiResponse(responseCode = "200", description = "Error logged successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class)))
	@RequestMapping(value = "/v1/error", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseEntity<Boolean> logError(@RequestBody FrontEndError errorMessage,
			HttpServletRequest request) {

		logger.logErrorMessage(this.getClass(),
				errorMessage.getError() + " with stacktrace: " + errorMessage.getStackTrace(), request);

		return new ResponseEntity<>(true, HttpStatus.OK);
	}
}
