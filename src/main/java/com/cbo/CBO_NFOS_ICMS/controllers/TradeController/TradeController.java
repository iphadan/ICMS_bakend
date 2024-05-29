package com.cbo.CBO_NFOS_ICMS.controllers.TradeController;

import com.cbo.CBO_NFOS_ICMS.models.Finance.Finance;
import com.cbo.CBO_NFOS_ICMS.models.Trade.Trade;
import com.cbo.CBO_NFOS_ICMS.services.TradeService.TradeService;
import com.cbo.CBO_NFOS_ICMS.services.UserAndEmployeeService.SubProcessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("Trade")
public class TradeController {

    private final TradeService tradeService;
    private final SubProcessService subProcessService;

    public TradeController(TradeService tradeService,SubProcessService subProcessService) {
        this.tradeService = tradeService;
        this.subProcessService= subProcessService;
    }


    @GetMapping("/getAll")
 @PreAuthorize("hasAnyRole('ICMS_TRADE_OWNER','ICMS_ADMIN')")
    public ResponseEntity<List<Trade>> getAllTrade() {
        List<Trade> trades = tradeService.findAllTrade();
        return new ResponseEntity<>(trades, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Trade> getTradeById
            (@PathVariable("id") Long id) {
        Trade trade = tradeService.findTradeById(id);
        return new ResponseEntity<>(trade, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ICMS_TRADE_IC')")
    public ResponseEntity<Trade> addTrade
            (@RequestBody Trade trade) {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        String formattedDate = date.format(formatter);

        String caseId = trade.getCaseId();
        while (tradeService.isCaseIdExists(caseId)) {
            // Increment the caseId until it is unique
            caseId = incrementCaseId(caseId);
        }
        trade.setCaseId(caseId);
        Trade trade1 = tradeService.addTrade(trade);
        return new ResponseEntity<>(trade1, HttpStatus.CREATED);
    }

    private String incrementCaseId(String caseId) {
        String[] parts = caseId.split("/");
        int year = Integer.parseInt(parts[3]);
        int month = Integer.parseInt(parts[2]);
        int day = Integer.parseInt(parts[1]);
        int caseNumber = Integer.parseInt(parts[0]);

        // Increment the case number
        caseNumber++;

        // Reset the case number to 1 if the year has changed
        if (year > Integer.parseInt(parts[3])) {
            caseNumber = 1;
        }

        // Format the incremented values into the new caseId
        return String.format("%04d/%02d/%02d/%04d", caseNumber, day, month, year);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ICMS_TRADE_IC')")
    public ResponseEntity<Trade> updateTrade
            (@RequestBody Trade trade) {
        System.out.println(trade.getTradeStatus());
        Trade updateTrade = tradeService.updateTrade(trade);
        return new ResponseEntity<>(trade, HttpStatus.CREATED);

    }
//
    @GetMapping("/getSize")
    @PreAuthorize("hasAnyRole('ICMS_TRADE_IC')")
    public int getTradeSize() {
        return tradeService.findTradeSize();
    }
//
//
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ICMS_ADMIN')")
    public ResponseEntity<?> deleteTrade(@PathVariable("id") Long id) {
        tradeService.deleteTrade(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ICMS_TRADE_OWNER')")
    @PatchMapping("/approveActionPlan/{id}")
    public ResponseEntity<Trade> approveActionPlan(@PathVariable Long id, @RequestBody Trade trade) {

        try {
            if (!id.equals(trade.getId())) {
                throw new IllegalArgumentException("ID in the path variable and trade object must match");
            }
            Trade updateTrade = tradeService.approveActionPlan(trade);
            return ResponseEntity.ok(updateTrade);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
//
//
    @PatchMapping("/escalate/{id}")
    @PreAuthorize("hasAnyRole('ICMS_TRADE_IC')")
    public ResponseEntity<Trade>escalatePlan(@PathVariable("id") Long id)
    {
        try
        {
            Trade row = tradeService.escalatePlan(id);
            return ResponseEntity.ok(row);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
//
    @GetMapping("/findByOrganizationalUnitId/{id}")
    @PreAuthorize("hasAnyRole('ICMS_TRADE_IC')")
    public ResponseEntity<List<Trade>> getAllFinanceInSpecificOrganizationalUnit(@PathVariable("id") Long id) {
        List<Finance> Finance;
        List<Trade> trades = tradeService.findAllTradeInSpecificOrganizationalUnit(id);
        return new ResponseEntity<>(trades, HttpStatus.OK);
    }
//
    @GetMapping("/findBySubProcessId/{id}")
    @PreAuthorize("hasAnyRole('ICMS_TRADE_IC','ICMS_TRADE_OWNER')")
    public ResponseEntity<List<Trade>> getAllFinanceInSpecificSubProcess(@PathVariable("id") Long subProcessId) {
        List<Trade> trades;
        trades = tradeService.findAllFinanceSubProcess(subProcessId);
        return new ResponseEntity<>(trades, HttpStatus.OK);
    }

}
