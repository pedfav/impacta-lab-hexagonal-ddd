package br.com.lab.impacta.investment.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class InvestmentResponse {

    private Long id;
    private Double amount;
    private LocalDateTime creation;

}
