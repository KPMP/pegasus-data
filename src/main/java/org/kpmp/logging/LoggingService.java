package org.kpmp.logging;

import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggingService {

	private static final String UNKNOWN_URI = "Unknown URI";

	private static final String LOG_MESSAGE_FORMAT = "URI: {} | MSG: {} ";

	@SuppressWarnings("rawtypes")
	public void logInfoMessage(Class clazz, String message, HttpServletRequest request) {
		Logger log = LoggerFactory.getLogger(clazz);
		log.info(LOG_MESSAGE_FORMAT, getRequestURI(request), message);
	}

	@SuppressWarnings("rawtypes")
	public void logInfoMessage(Class clazz, String uri, String message) {
		Logger log = LoggerFactory.getLogger(clazz);
		log.info(LOG_MESSAGE_FORMAT, uri, message);
	}

	@SuppressWarnings("rawtypes")
	public void logErrorMessage(Class clazz, String message, HttpServletRequest request) {
		Logger log = LoggerFactory.getLogger(clazz);
		log.error(LOG_MESSAGE_FORMAT, getRequestURI(request), message);
	}

	@SuppressWarnings("rawtypes")
	public void logErrorMessage(Class clazz, String uri, String message) {
		Logger log = LoggerFactory.getLogger(clazz);
		log.error(LOG_MESSAGE_FORMAT, uri, message);
	}

	@SuppressWarnings("rawtypes")
	public void logWarnMessage(Class clazz, String message, HttpServletRequest request) {
		Logger log = LoggerFactory.getLogger(clazz);
		log.warn(LOG_MESSAGE_FORMAT, getRequestURI(request), message);
	}

	@SuppressWarnings("rawtypes")
	public void logWarnMessage(Class clazz, String uri, String message) {
		Logger log = LoggerFactory.getLogger(clazz);
		log.warn(LOG_MESSAGE_FORMAT, uri, message);
	}

	private String getRequestURI(HttpServletRequest request) {
		return request != null ? request.getRequestURI() : UNKNOWN_URI;
	}

}