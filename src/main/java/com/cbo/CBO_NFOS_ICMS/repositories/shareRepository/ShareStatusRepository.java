package com.cbo.CBO_NFOS_ICMS.repositories.shareRepository;



import com.cbo.CBO_NFOS_ICMS.models.Finance.FinanceStatus;
import com.cbo.CBO_NFOS_ICMS.models.share.ShareStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShareStatusRepository extends JpaRepository<ShareStatus, Long> {

    Optional<ShareStatus> findStatusById(Long id);

    Optional<ShareStatus> findShareStatusById(Long id);
}
