package br.com.lab.impacta.investment.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorMessageResponse {
    private LocalDateTime timeStamp;
    private String message;
    private String description;
}
