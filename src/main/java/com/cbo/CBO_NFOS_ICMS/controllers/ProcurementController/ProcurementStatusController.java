package com.cbo.CBO_NFOS_ICMS.controllers.ProcurementController;


import com.cbo.CBO_NFOS_ICMS.models.Procurement.ProcurementStatus;
import com.cbo.CBO_NFOS_ICMS.services.ProcurementService.ProcurementStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ProcurementStatus")
public class ProcurementStatusController {
    private final ProcurementStatusService procurementStatusService;

    public ProcurementStatusController(ProcurementStatusService procurementStatusService) {
        this.procurementStatusService = procurementStatusService;
    }

    @GetMapping("/getAll")

    public ResponseEntity<List<ProcurementStatus>> getAllProcurementStatus() {
        List<ProcurementStatus> activitiesStatus = procurementStatusService.findAllProcurementStatus();
        return new ResponseEntity<>(activitiesStatus, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ProcurementStatus> getAllProcurementInSpecificOrganizationalUnit(@PathVariable("id") Long id) {
        ProcurementStatus procurementStatus = procurementStatusService.findProcurementStatusById(id);
        return new ResponseEntity<>(procurementStatus, HttpStatus.OK);
    }

}