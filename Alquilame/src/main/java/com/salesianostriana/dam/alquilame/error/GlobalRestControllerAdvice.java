package com.salesianostriana.dam.alquilame.error;

import com.salesianostriana.dam.alquilame.error.model.impl.ApiErrorImpl;
import com.salesianostriana.dam.alquilame.error.model.impl.ApiValidationSubError;
import com.salesianostriana.dam.alquilame.exception.*;
import com.salesianostriana.dam.alquilame.exception.dwelling.DwellingAccessDeniedException;
import com.salesianostriana.dam.alquilame.exception.dwelling.DwellingBadRequestDeleteException;
import com.salesianostriana.dam.alquilame.exception.favourite.FavouriteAlreadyInListException;
import com.salesianostriana.dam.alquilame.exception.favourite.FavouriteDeleteBadRequestException;
import com.salesianostriana.dam.alquilame.exception.favourite.FavouriteNotFoundException;
import com.salesianostriana.dam.alquilame.exception.jwt.JwtTokenException;
import com.salesianostriana.dam.alquilame.exception.province.ProvinceBadRequestDeleteException;
import com.salesianostriana.dam.alquilame.exception.province.ProvinceNotFoundException;
import com.salesianostriana.dam.alquilame.exception.user.UserDwellingsNotFoundException;
import com.salesianostriana.dam.alquilame.exception.user.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalRestControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildApiError(ex.getMessage(), request, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildApiErrorWithSubErrors("Validation error. Please check the sublist.", request, status, ex.getAllErrors());
    }

    @ExceptionHandler({ AuthenticationException.class })
    public ResponseEntity<?> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
        return buildApiError(ex.getMessage(), request, HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        return buildApiError(ex.getMessage(), request, HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler({JwtTokenException.class})
    public ResponseEntity<?> handleTokenException(JwtTokenException ex, WebRequest request) {
        return buildApiError(ex.getMessage(), request, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({UsernameNotFoundException.class})
    public ResponseEntity<?> handleUserNotExistsException(UsernameNotFoundException ex, WebRequest request) {
        return buildApiError(ex.getMessage(), request, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({EmptyListNotFoundException.class})
    public ResponseEntity<?> handleEmptyListNotFoundException(EmptyListNotFoundException ex, WebRequest request) {
        return buildApiError(ex.getMessage(), request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        return buildApiError(ex.getMessage(), request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UserDwellingsNotFoundException.class})
    public ResponseEntity<?> handleUserDwellingsNotFoundException(UserDwellingsNotFoundException ex, WebRequest request) {
        return buildApiError(ex.getMessage(), request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ProvinceNotFoundException.class})
    public ResponseEntity<?> handleCityNotFoundException(ProvinceNotFoundException ex, WebRequest request) {
        return buildApiError(ex.getMessage(), request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DwellingBadRequestDeleteException.class)
    public ResponseEntity<?> handleDwellingBadRequestDeleteException(DwellingBadRequestDeleteException ex, WebRequest request) {
        return buildApiError(ex.getMessage(), request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({DwellingAccessDeniedException.class})
    public ResponseEntity<?> handleDwellingAccessDeniedException(DwellingAccessDeniedException ex, WebRequest request) {
        return buildApiError(ex.getMessage(), request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FavouriteAlreadyInListException.class)
    public ResponseEntity<?> handleFavouriteAlreadyInListException(FavouriteAlreadyInListException ex, WebRequest request) {
        return buildApiError(ex.getMessage(), request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProvinceBadRequestDeleteException.class)
    public ResponseEntity<?> handleProvinceBadRequestDeleteException(ProvinceBadRequestDeleteException ex, WebRequest request) {
        return buildApiError(ex.getMessage(), request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FavouriteNotFoundException.class)
    public ResponseEntity<?> handleFavouriteNotFoundException(FavouriteNotFoundException ex, WebRequest request) {
        return buildApiError(ex.getMessage(), request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FavouriteDeleteBadRequestException.class)
    public ResponseEntity<?> handleFavouriteDeleteBadRequestException(FavouriteDeleteBadRequestException ex, WebRequest request) {
        return buildApiError(ex.getMessage(), request, HttpStatus.BAD_REQUEST);
    }

    private final ResponseEntity<Object> buildApiError(String message, WebRequest request, HttpStatus status) {
        return ResponseEntity
                .status(status)
                .body(
                        ApiErrorImpl.builder()
                                .status(status)
                                .message(message)
                                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                                .build()
                );
    }

    private final ResponseEntity<Object> buildApiErrorWithSubErrors(String message, WebRequest request, HttpStatus status, List<ObjectError> subErrors) {
        return ResponseEntity
                .status(status)
                .body(
                        ApiErrorImpl.builder()
                                .status(status)
                                .message(message)
                                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                                .subErrors(subErrors.stream()
                                        .map(ApiValidationSubError::fromObjectError)
                                        .collect(Collectors.toList())
                                )
                                .build()
                );

    }

}
