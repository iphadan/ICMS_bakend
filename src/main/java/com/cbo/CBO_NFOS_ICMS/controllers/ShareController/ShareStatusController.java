package com.cbo.CBO_NFOS_ICMS.controllers.share;


import com.cbo.CBO_NFOS_ICMS.models.Finance.FinanceStatus;
import com.cbo.CBO_NFOS_ICMS.models.share.ShareStatus;
import com.cbo.CBO_NFOS_ICMS.services.FinanceService.FinanceStatusService;
import com.cbo.CBO_NFOS_ICMS.services.share.ShareStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ShareStatus")
public class ShareStatusController {
    private final ShareStatusService shareStatusService;

    public ShareStatusController(ShareStatusService shareStatusService) {
        this.shareStatusService = shareStatusService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ShareStatus>> getAllShareStatus() {
        List<ShareStatus> activitiesStatus = shareStatusService.findAllShareStatus();
        return new ResponseEntity<>(activitiesStatus, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ShareStatus> getAllShareInSpecificOrganizationalUnit(@PathVariable("id") Long id) {
        ShareStatus shareStatus = shareStatusService.findShareStatusById(id);
        return new ResponseEntity<>(shareStatus, HttpStatus.OK);
    }

}