package br.com.lab.impacta.account.api;

import br.com.lab.impacta.account.application.AccountApplication;
import br.com.lab.impacta.account.application.dto.request.DebitAccountRequest;
import br.com.lab.impacta.account.application.dto.response.DebitAccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class DebitController {

    private final AccountApplication accountApplication;

    @PostMapping("/{accountId}/debit")
    public ResponseEntity<DebitAccountResponse> debit(
            @PathVariable Long accountId,
            @RequestBody DebitAccountRequest debitAccountRequest) {
        DebitAccountResponse debitAccountResponse = accountApplication.debit(accountId, debitAccountRequest);

        return ResponseEntity.ok(debitAccountResponse);
    }
}
