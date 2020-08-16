package com.sb.latest.hibernate.validate.exception;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

@ControllerAdvice
public class CustomHandlerException extends ResponseEntityExceptionHandler {
    /**
     * Customize the response for HttpRequestMethodNotSupportedException.
     * <p>
     * This method logs a warning, sets the "Allow" header, and delegates to
     * {@link #handleExceptionInternal}.
     * 
     * @param ex      the exception
     * @param headers the headers to be written to the response
     * @param status  the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        pageNotFoundLogger.warn(ex.getMessage());

        Set<HttpMethod> supportedMethods = ex.getSupportedHttpMethods();
        if (!CollectionUtils.isEmpty(supportedMethods)) {
            headers.setAllow(supportedMethods);
        }
        return handleCustomExceptionResponse(ex.getMessage(), ex.getStackTrace(), headers, status);
    }

    /**
     * Customize the response for HttpMediaTypeNotSupportedException.
     * <p>
     * This method sets the "Accept" header and delegates to
     * {@link #handleExceptionInternal}.
     * 
     * @param ex      the exception
     * @param headers the headers to be written to the response
     * @param status  the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<MediaType> mediaTypes = ex.getSupportedMediaTypes();
        if (!CollectionUtils.isEmpty(mediaTypes)) {
            headers.setAccept(mediaTypes);
        }

        return handleCustomExceptionResponse(ex.getMessage(), ex.getStackTrace(), headers, status);
    }

    /**
     * Customize the response for HttpMediaTypeNotAcceptableException.
     * <p>
     * This method delegates to {@link #handleExceptionInternal}.
     * 
     * @param ex      the exception
     * @param headers the headers to be written to the response
     * @param status  the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleCustomExceptionResponse(ex.getMessage(), ex.getStackTrace(), headers, status);
    }

    /**
     * Customize the response for MissingPathVariableException.
     * <p>
     * This method delegates to {@link #handleExceptionInternal}.
     * 
     * @param ex      the exception
     * @param headers the headers to be written to the response
     * @param status  the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     * @since 4.2
     */
    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        return handleCustomExceptionResponse(ex.getMessage(), ex.getStackTrace(), headers, status);
    }

    /**
     * Customize the response for MissingServletRequestParameterException.
     * <p>
     * This method delegates to {@link #handleExceptionInternal}.
     * 
     * @param ex      the exception
     * @param headers the headers to be written to the response
     * @param status  the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleCustomExceptionResponse(ex.getMessage(), ex.getStackTrace(), headers, status);
    }

    /**
     * Customize the response for ServletRequestBindingException.
     * <p>
     * This method delegates to {@link #handleExceptionInternal}.
     * 
     * @param ex      the exception
     * @param headers the headers to be written to the response
     * @param status  the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleCustomExceptionResponse(ex.getMessage(), ex.getStackTrace(), headers, status);
    }

    /**
     * Customize the response for ConversionNotSupportedException.
     * <p>
     * This method delegates to {@link #handleExceptionInternal}.
     * 
     * @param ex      the exception
     * @param headers the headers to be written to the response
     * @param status  the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleCustomExceptionResponse(ex.getMessage(), ex.getStackTrace(), headers, status);
    }

    /**
     * Customize the response for TypeMismatchException.
     * <p>
     * This method delegates to {@link #handleExceptionInternal}.
     * 
     * @param ex      the exception
     * @param headers the headers to be written to the response
     * @param status  the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        return handleCustomExceptionResponse(ex.getMessage(), ex.getStackTrace(), headers, status);
    }

    /**
     * Customize the response for HttpMessageNotReadableException.
     * <p>
     * This method delegates to {@link #handleExceptionInternal}.
     * 
     * @param ex      the exception
     * @param headers the headers to be written to the response
     * @param status  the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleCustomExceptionResponse(ex.getMessage(), ex.getStackTrace(), headers, status);
    }

    /**
     * Customize the response for HttpMessageNotWritableException.
     * <p>
     * This method delegates to {@link #handleExceptionInternal}.
     * 
     * @param ex      the exception
     * @param headers the headers to be written to the response
     * @param status  the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleCustomExceptionResponse(ex.getMessage(), ex.getStackTrace(), headers, status);
    }

    /**
     * Customize the response for MethodArgumentNotValidException.
     * <p>
     * This method delegates to {@link #handleExceptionInternal}.
     * 
     * @param ex      the exception
     * @param headers the headers to be written to the response
     * @param status  the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleCustomExceptionResponse(ex.getMessage(), ex.getStackTrace(), headers, status);
    }

    /**
     * Customize the response for MissingServletRequestPartException.
     * <p>
     * This method delegates to {@link #handleExceptionInternal}.
     * 
     * @param ex      the exception
     * @param headers the headers to be written to the response
     * @param status  the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleCustomExceptionResponse(ex.getMessage(), ex.getStackTrace(), headers, status);
    }

    /**
     * Customize the response for BindException.
     * <p>
     * This method delegates to {@link #handleExceptionInternal}.
     * 
     * @param ex      the exception
     * @param headers the headers to be written to the response
     * @param status  the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
            WebRequest request) {

        return handleCustomExceptionResponse(ex.getMessage(), ex.getStackTrace(), headers, status);
    }

    /**
     * Customize the response for NoHandlerFoundException.
     * <p>
     * This method delegates to {@link #handleExceptionInternal}.
     * 
     * @param ex      the exception
     * @param headers the headers to be written to the response
     * @param status  the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     * @since 4.0
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        return handleCustomExceptionResponse(ex.getMessage(), ex.getStackTrace(), headers, status);
    }

    /**
     * Customize the response for AsyncRequestTimeoutException.
     * <p>
     * This method delegates to {@link #handleExceptionInternal}.
     * 
     * @param ex         the exception
     * @param headers    the headers to be written to the response
     * @param status     the selected response status
     * @param webRequest the current request
     * @return a {@code ResponseEntity} instance
     * @since 4.2.8
     */
    @Nullable
    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex,
            HttpHeaders headers, HttpStatus status, WebRequest webRequest) {

        if (webRequest instanceof ServletWebRequest) {
            ServletWebRequest servletWebRequest = (ServletWebRequest) webRequest;
            HttpServletResponse response = servletWebRequest.getResponse();
            if (response != null && response.isCommitted()) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Async request timed out");
                }
                return null;
            }
        }

        return handleCustomExceptionResponse(ex.getMessage(), ex.getStackTrace(), headers, status);
    }

    /**
     * A single place to customize the response body of all exception types.
     * <p>
     * The default implementation sets the
     * {@link WebUtils#ERROR_EXCEPTION_ATTRIBUTE} request attribute and creates a
     * {@link ResponseEntity} from the given body, headers, and status.
     * 
     * @param ex      the exception
     * @param body    the body for the response
     * @param headers the headers for the response
     * @param status  the response status
     * @param request the current request
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        return handleCustomExceptionResponse(ex.getMessage(), ex.getStackTrace(), headers, status);
    }

    private String getStackTrace(StackTraceElement[] stackTrace) {
        return Arrays.toString(Arrays.copyOfRange(stackTrace, 0, 10));
    }

    private ResponseEntity<Object> handleCustomExceptionResponse(String message, StackTraceElement[] stackTrace,  HttpHeaders headers,
            HttpStatus status) {
        CustomExceptionResponse customExceptionResponse = new CustomExceptionResponse(message, getStackTrace(stackTrace));
        return new ResponseEntity<>(customExceptionResponse, headers, status);
    }
}