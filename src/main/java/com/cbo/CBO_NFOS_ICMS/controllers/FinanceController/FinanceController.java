package com.cbo.CBO_NFOS_ICMS.controllers.FinanceController;

import com.cbo.CBO_NFOS_ICMS.models.DACGM.DailyActivityGapControl;
import com.cbo.CBO_NFOS_ICMS.models.Finance.Finance;
import com.cbo.CBO_NFOS_ICMS.models.IFB.IFB;
import com.cbo.CBO_NFOS_ICMS.services.FinanceService.FinanceService;
import com.cbo.CBO_NFOS_ICMS.services.IFBService.IFBService;
import com.cbo.CBO_NFOS_ICMS.services.UserAndEmployeeService.SubProcessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("Finance")
public class FinanceController {
    private final FinanceService financeService;
    private final SubProcessService subProcessService;

    public FinanceController(FinanceService financeService, SubProcessService subProcessService) {
        this.financeService = financeService;
        this.subProcessService = subProcessService;
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('ICMS_FINANCE_OWNER', 'ICMS_ADMIN')")
    public ResponseEntity<List<Finance>> getFinance() {
        List<Finance> Finance = financeService.findAllFinance();
        return new ResponseEntity<>(Finance, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Finance> getFinanceId
            (@PathVariable("id") Long id) {
        Finance Finance = financeService.findFinanceById(id);
        return new ResponseEntity<>(Finance, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ICMS_FINANCE_IC')")
    public ResponseEntity<Finance> addIFB
            (@RequestBody Finance finance) {
        String caseId = finance.getCaseId();
        while (financeService.isCaseIdExists(caseId)) {
            // Increment the caseId until it is unique
            caseId = incrementCaseId(caseId);
        }
        finance.setCaseId(caseId);
        Finance newFinance = financeService.addIFB(finance);
        return new ResponseEntity<>(newFinance, HttpStatus.CREATED);
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
    @PreAuthorize("hasAnyRole('ICMS_FINANCE_IC')")
    public ResponseEntity<Finance> updateIFB
            (@RequestBody Finance finance) {
        Finance updateFinance = financeService.updateFinance(finance);
        return new ResponseEntity<>(updateFinance, HttpStatus.CREATED);

    }

    @GetMapping("/getSize")
    public Long getFinanceSize() {
        return financeService.findFinanceSize();
    }

//    @GetMapping("/getSize")
//    @PreAuthorize("hasAnyRole('ICMS_FINANCE_IC')")
//    public int getTradeLastId(){
//        return Math.toIntExact(financeService.getLastId());
//    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ICMS_ADMIN')")

    public ResponseEntity<?> deleteIFB(@PathVariable("id") Long id) {
        financeService.deleteFinance(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ICMS_FINANCE_OWNER')")
    @PatchMapping("/approveActionPlan/{id}")
    public ResponseEntity<Finance> approveActionPlan(@PathVariable Long id, @RequestBody Finance finance) {
        try {
            if (!id.equals(finance.getId())) {
                throw new IllegalArgumentException("ID in the path variable and finance object must match");
            }
            Finance updatedFinance = financeService.approveActionPlan(finance);
            return ResponseEntity.ok(updatedFinance);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PatchMapping("/escalate/{id}")
    @PreAuthorize("hasAnyRole('ICMS_FINANCE_IC')")
    public ResponseEntity<Finance>escalatePlan(@PathVariable("id") Long id)
    {
        try
        {
            Finance row = financeService.escalatePlan(id);
            return ResponseEntity.ok(row);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/findByOrganizationalUnitId/{id}")
    @PreAuthorize("hasAnyRole('ICMS_FINANCE_IC')")
    public ResponseEntity<List<Finance>> getAllFinanceInSpecificOrganizationalUnit(@PathVariable("id") Long id) {
        List<Finance> Finance;
        Finance = financeService.findAllFinanceInSpecificOrganizationalUnit(id);
        return new ResponseEntity<>(Finance, HttpStatus.OK);
    }

    @GetMapping("/findBySubProcessId/{id}")
    @PreAuthorize("hasAnyRole('ICMS_FINANCE_IC', 'ICMS_FINANCE_OWNER')")
    public ResponseEntity<List<Finance>> getAllFinanceInSpecificSubProcess(@PathVariable("id") Long subProcessId) {
        List<Finance> Finance;
        Finance = financeService.findAllFinanceInSpecificSubProcess(subProcessId);
        return new ResponseEntity<>(Finance, HttpStatus.OK);
    }

}

