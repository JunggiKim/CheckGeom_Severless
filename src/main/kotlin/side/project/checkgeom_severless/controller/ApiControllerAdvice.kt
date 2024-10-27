package side.project.checkgeom_severless.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.logging.LogLevel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import side.project.checkgeom_severless.support.error.CoreApiException
import side.project.checkgeom_severless.support.error.ErrorType
import side.project.checkgeom_severless.support.response.ApiResponse
import side.project.checkgeom_severless.support.response.ApiResponse.Companion.error
import java.time.LocalDateTime

@RestControllerAdvice
class ApiControllerAdvice {
    private val log: Logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(CoreApiException::class)
    fun handleCoreApiException(e: CoreApiException): ResponseEntity<ApiResponse<*>?> {
        when (e.errorType.logLevel) {
            LogLevel.ERROR -> log.error("CoreApiException : {}", e.message, e)
            LogLevel.WARN -> log.warn("CoreApiException : {}", e.message, e)
            else -> log.info("CoreApiException : {}", e.message, e)
        }
        return ResponseEntity(error(e.errorType, e.data), e.errorType.status)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ApiResponse<*>?> {
        log.error("Exception : {}", e.message, e)
        log.error("Time : {}", LocalDateTime.now())
        return ResponseEntity(error(ErrorType.DEFAULT_ERROR), ErrorType.DEFAULT_ERROR.status)
    }

    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException::class)
    fun MethodArgumentNotValidException(e: Exception): ResponseEntity<ApiResponse<*>?> {
        log.error("Exception : {}", e.message, e)
        return ResponseEntity(error(ErrorType.DEFAULT_ERROR), ErrorType.DEFAULT_ERROR.status)
    }
}
