package com.cbo.CBO_NFOS_ICMS.controllers.share;


import com.cbo.CBO_NFOS_ICMS.models.Finance.Finance;
import com.cbo.CBO_NFOS_ICMS.models.share.Share;
import com.cbo.CBO_NFOS_ICMS.services.share.ShareService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Share")
public class ShareController {
    private final ShareService shareService;

    public ShareController(ShareService shareService) {
        this.shareService = shareService;
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('ICMS_SHARE_OWNER', 'ICMS_ADMIN')")
    public ResponseEntity<List<Share>> getAllShare() {
        List<Share> share = shareService.findAllShare();
        return new ResponseEntity<>(share, HttpStatus.OK);
    }


    @GetMapping("/getSize")
    @PreAuthorize("hasAnyRole('ICMS_SHARE_IC')")
    public int  getShareSize(){

        return shareService.findShareSize();
    }

    @GetMapping("/findByOrganizationalUnitId/{id}")
    @PreAuthorize("hasAnyRole('ICMS_SHARE_IC')")
    public ResponseEntity<List<Share>> getAllShareInSpecificOrganizationalUnit(@PathVariable("id") Long id) {
        List<Share> share;
        share = shareService.findAllShareInSpecificOrganizationalUnit(id);
        return new ResponseEntity<>(share, HttpStatus.OK);
    }


    @GetMapping("/findBySubProcessId/{id}")
    @PreAuthorize("hasAnyRole('ICMS_SHARE_IC')")
    public ResponseEntity<List<Share>> getAllDailyActivityGapInSpecificSubProcess(@PathVariable("id") Long subProcessId) {
        List<Share> Share;
        Share = shareService.findAllShareInSpecificSubProcess(subProcessId);
        return new ResponseEntity<>(Share, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Share> getShareId
            (@PathVariable("id") Long id) {
        Share share = shareService.findShareById(id);
        return new ResponseEntity<>(share, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ICMS_SHARE_IC')")
    public ResponseEntity<Share> addShare
            (@RequestBody Share share) {
        String caseId = share.getCaseId();
        while (shareService.isCaseIdExists(caseId)) {
            caseId = incrementCaseId(caseId);
        }
        share.setCaseId(caseId);
        System.out.println(caseId);
        Share newShare = shareService.addShare(share);
        return new ResponseEntity<>(newShare, HttpStatus.CREATED);
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
    @PreAuthorize("hasRole('ICMS_SHARE_IC')")
    public ResponseEntity<Share> updateShare
            (@RequestBody Share share) {
        Share updateShare = shareService.updateShare(share);
        return new ResponseEntity<>(updateShare, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ICMS_SHARE_OWNER', 'ICMS_ADMIN')")

    public ResponseEntity<?> deleteShare(@PathVariable("id") Long id) {
        shareService.deleteShare(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
