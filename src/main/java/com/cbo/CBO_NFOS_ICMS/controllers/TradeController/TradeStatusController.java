package com.cbo.CBO_NFOS_ICMS.controllers.TradeController;

import com.cbo.CBO_NFOS_ICMS.models.Finance.FinanceStatus;
import com.cbo.CBO_NFOS_ICMS.models.Trade.TradeStatus;
import com.cbo.CBO_NFOS_ICMS.services.FinanceService.FinanceStatusService;
import com.cbo.CBO_NFOS_ICMS.services.TradeService.TradeStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/TradeStatus")
public class TradeStatusController {

    private final TradeStatusService tradeStatusService;

    public TradeStatusController(TradeStatusService tradeStatusService) {
        this.tradeStatusService = tradeStatusService;
    }

    @GetMapping("/getAll")

    public ResponseEntity<List<TradeStatus>> getAllTradeStatus() {
        List<TradeStatus> activitiesStatus = tradeStatusService.findAllTradeStatus();
        return new ResponseEntity<>(activitiesStatus, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<TradeStatus> getAllDACGMInSpecificOrganizationalUnit(@PathVariable("id") Long id) {
        TradeStatus tradeStatus = tradeStatusService.findTradeStatusById(id);
        return new ResponseEntity<>(tradeStatus, HttpStatus.OK);
    }

}
