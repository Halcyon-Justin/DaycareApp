package halcyon.clemncare.app.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Illegal Arguments";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = PermissionDeniedException.class)
    protected ResponseEntity<Object> handlePermissionDeniedException(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Permission denied";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler(value = FamilyNotFoundException.class)
    protected ResponseEntity<Object> handleFamilyNotFoundException(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Family not found";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = ChildNotFoundException.class)
    protected ResponseEntity<Object> handleChildNotFoundException(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Child not found";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = InvoiceNotFoundException.class)
    protected ResponseEntity<Object> handleInvoiceNotFoundException(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Invoice not found";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = GuardianNotFoundException.class)
    protected ResponseEntity<Object> handleGuardianNotFoundException(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Guardian not found";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = AddressNotFoundException.class)
    protected ResponseEntity<Object> handleHomeAddressNotFoundException(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Home Address not found";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = ReportCardNotFoundException.class)
    protected ResponseEntity<Object> handleReportCardNotFoundException(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Report Card not found";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    // Add more exception handling methods for other exception types as needed
}
