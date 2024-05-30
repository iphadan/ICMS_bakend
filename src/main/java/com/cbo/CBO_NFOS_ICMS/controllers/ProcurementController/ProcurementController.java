package com.cbo.CBO_NFOS_ICMS.controllers.ProcurementController;

import com.cbo.CBO_NFOS_ICMS.models.DACGM.DailyActivityGapControl;
import com.cbo.CBO_NFOS_ICMS.models.Procurement.Procurement;
//import com.cbo.CBO_NFOS_ICMS.models.IFB.IFB;
import com.cbo.CBO_NFOS_ICMS.services.ProcurementService.ProcurementService;
//import com.cbo.CBO_NFOS_ICMS.services.IFBService.IFBService;
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
@RequestMapping("Procurement")
public class ProcurementController {
    private final ProcurementService procurementService;
    private final SubProcessService subProcessService;

    public ProcurementController(ProcurementService procurementService, SubProcessService subProcessService) {
        this.procurementService = procurementService;
        this.subProcessService = subProcessService;
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('ICMS_PROCUREMENT_OWNER')")
    public ResponseEntity<List<Procurement>> getProcurement() {
        List<Procurement> Procurement = procurementService.findAllProcurement();
        return new ResponseEntity<>(Procurement, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Procurement> getProcurementId
            (@PathVariable("id") Long id) {

        Procurement Procurement = procurementService.findProcurementById(id);
        return new ResponseEntity<>(Procurement, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ICMS_PROCUREMENT_IC')")
    public ResponseEntity<Procurement> addProcurement
            (@RequestBody Procurement procurement) {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        String formattedDate = date.format(formatter);

        String caseId = procurement.getCaseId();
        while (procurementService.isCaseIdExists(caseId)) {
            // Increment the caseId until it is unique
            caseId = incrementCaseId(caseId);
        }
        procurement.setCaseId(caseId);
        Procurement newProcurement = procurementService.addProcurement(procurement);
        System.out.println(procurement.getProcurementDate());
        return new ResponseEntity<>(newProcurement, HttpStatus.CREATED);
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
    @PreAuthorize("hasAnyRole('ICMS_PROCUREMENT_IC')")
    public ResponseEntity<Procurement> updateProcurement
            (@RequestBody Procurement procurement) {
        System.out.println(procurement.getProcurementStatus());
        Procurement updateProcurement = procurementService.updateProcurement(procurement);
        return new ResponseEntity<>(updateProcurement, HttpStatus.CREATED);

    }

    @GetMapping("/getSize")
    @PreAuthorize("hasAnyRole('ICMS_PROCUREMENT_IC')")
    public int getProcurementSize() {
        return procurementService.findProcurementSize();
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ICMS_ADMIN')")

    public ResponseEntity<?> deleteProcurement(@PathVariable("id") Long id) {
        procurementService.deleteProcurement(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ICMS_PROCUREMENT_OWNER')")
    @PatchMapping("/approveActionPlan/{id}")
    public ResponseEntity<Procurement> approveActionPlan(@PathVariable Long id, @RequestBody Procurement procurement) {
        try {
            if (!id.equals(procurement.getId())) {
                throw new IllegalArgumentException("ID in the path variable and procurement object must match");
            }
            Procurement updatedProcurement = procurementService.approveActionPlan(procurement);
            return ResponseEntity.ok(updatedProcurement);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PatchMapping("/escalate/{id}")
    @PreAuthorize("hasAnyRole('ICMS_PROCUREMENT_IC')")
    public ResponseEntity<Procurement>escalatePlan(@PathVariable("id") Long id)
    {
        try
        {
            Procurement row = procurementService.escalatePlan(id);
            return ResponseEntity.ok(row);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/findByOrganizationalUnitId/{id}")
    @PreAuthorize("hasAnyRole('ICMS_PROCUREMENT_IC')")
    public ResponseEntity<List<Procurement>> getAllFinanceInSpecificOrganizationalUnit(@PathVariable("id") Long id) {
        List<Procurement> Procurement;
        Procurement = procurementService.findAllProcurementInSpecificOrganizationalUnit(id);
        return new ResponseEntity<>(Procurement, HttpStatus.OK);
    }

    @GetMapping("/findBySubProcessId/{id}")
    @PreAuthorize("hasAnyRole('ICMS_PROCUREMENT_IC')")
    public ResponseEntity<List<Procurement>> getAllFinanceInSpecificSubProcess(@PathVariable("id") Long subProcessId) {
        List<Procurement> Procurement;
        Procurement = procurementService.findAllProcurementInSpecificSubProcess(subProcessId);
        return new ResponseEntity<>(Procurement, HttpStatus.OK);
    }

}

