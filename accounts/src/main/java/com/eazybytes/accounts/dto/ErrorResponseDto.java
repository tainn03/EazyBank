package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response information"
)
@FieldDefaults(level = PRIVATE)
public class ErrorResponseDto {

    @Schema(
            description = "API path invoked by client"
    )
    String apiPath;

    @Schema(
            description = "Error code representing the error happened"
    )
    HttpStatus errorCode;

    @Schema(
            description = "Error message representing the error happened"
    )
    String errorMessage;

    @Schema(
            description = "Time representing when the error happened"
    )
    LocalDateTime errorTime;
}

