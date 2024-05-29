package com.cbo.CBO_NFOS_ICMS.services.share;

import com.cbo.CBO_NFOS_ICMS.exception.UserNotFoundException;
import com.cbo.CBO_NFOS_ICMS.models.Finance.FinanceStatus;
import com.cbo.CBO_NFOS_ICMS.models.share.ShareStatus;
import com.cbo.CBO_NFOS_ICMS.repositories.FinanceRepository.FinanceStatusRepository;
import com.cbo.CBO_NFOS_ICMS.repositories.shareRepository.ShareStatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShareStatusService {
    private final ShareStatusRepository shareStatusRepository;

    public ShareStatusService(
            ShareStatusRepository shareStatusRepository) {
        this.shareStatusRepository = shareStatusRepository;
    }

    public ShareStatus findShareStatusById(Long id) {
        return shareStatusRepository.findShareStatusById(id)
                .orElseThrow(() -> new UserNotFoundException("Share Status by id = " + id + " was not found"));
    }

    public ShareStatus addShareStatus(ShareStatus as) {
        return shareStatusRepository.save(as);
    }

    public List<ShareStatus> findAllShareStatus() {
        return shareStatusRepository.findAll();
    }

}