package com.cbo.CBO_NFOS_ICMS.services.share;

import com.cbo.CBO_NFOS_ICMS.exception.ResourceNotFoundException;
import com.cbo.CBO_NFOS_ICMS.exception.UserNotFoundException;

import com.cbo.CBO_NFOS_ICMS.models.share.Share;
import com.cbo.CBO_NFOS_ICMS.repositories.shareRepository.ShareRepository;
import com.cbo.CBO_NFOS_ICMS.services.UserAndEmployeeService.BranchService;
import com.cbo.CBO_NFOS_ICMS.services.UserAndEmployeeService.SubProcessService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShareService {

    private final SubProcessService subProcessService;
    private final BranchService branchService;
    private final ShareRepository shareRepository;

    public ShareService(SubProcessService subProcessService, BranchService branchService, ShareRepository shareRepository) {
        this.subProcessService = subProcessService;
        this.branchService = branchService;
        this.shareRepository = shareRepository;
    }

    public Share addShare(Share share) {

        return shareRepository.save(share);
    }

    public Share authorizeShare(Share share) {

        return shareRepository.save(share);
    }


    public List<Share> findAllShare() {
        return shareRepository.findAll();
    }

    public Share approveActionPlan(Share share) {
        Share row = shareRepository.findById(share.getId())
                .orElseThrow(() -> new UserNotFoundException("Share by id = " + share.getId() + " was not found"));
        row.setActionPlanDueDate(share.getActionPlanDueDate());

        row.setActionTaken(true);
        return shareRepository.save(row);
    }
    public boolean isCaseIdExists(String caseId) {
        return shareRepository.existsByCaseId(caseId);
    }

    public int findShareSize() {
        return shareRepository.findAll().size();
    }

    public Share updateShare(Share share) {
        Optional<Share> optionalShare = shareRepository.findById(share.getId());
        if (optionalShare.isPresent()) {
            Share existingShare = optionalShare.get();
            existingShare.setShareStatus(share.getShareStatus());
            existingShare.setShareDate(share.getShareDate()); // Add this line
            return shareRepository.save(existingShare);
        } else {
            throw new ResourceNotFoundException("Share not found");
        }
    }

    public Share findShareById(Long id) {
        return shareRepository.findShareById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id" + id + " was not found"));
    }


    public void deleteShare(Long id) {
        shareRepository.deleteById(id);
    }

    public List<Share> findAllShareInSpecificOrganizationalUnit(Long id) {
        return shareRepository.findShareByTeamId(id);
    }

    public List<Share> findAllShareInSpecificSubProcess(Long subProcessId) {
//        Branch branch = organizationalUnitService.findBranchById(id);
        return shareRepository.findShareBySubProcessId(subProcessId);
    }


    public void deleteRow(int id) {
        Optional<Share> data = shareRepository.findById((long) id);
        if (data.isPresent()) {

            List<Share> dataList = shareRepository.findAll();
            for (int i = id; i - 1 < dataList.size(); i++) {
                Share d = dataList.get(i - 1);

                d.setId(d.getId() - 1);
                shareRepository.save(d);
            }
            shareRepository.deleteById((long) id);
        }
    }

}