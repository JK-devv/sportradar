package sportradar.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class SportRadarExceptionHandler {

    @ExceptionHandler(Exception.class)
    private ResponseEntity<Object> handler(Exception e) {
        log.error("SportradarExceptionHandler {}", e.getMessage(), e);
        return ResponseEntity.ok(e.getMessage());
    }
}
