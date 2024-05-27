package com.cbo.CBO_NFOS_ICMS.repositories.ProcurementRepository;


import com.cbo.CBO_NFOS_ICMS.models.DACGM.ActivityStatus;
import com.cbo.CBO_NFOS_ICMS.models.Procurement.ProcurementStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProcurementStatusRepository extends JpaRepository<ProcurementStatus, Long> {

    Optional<ProcurementStatus> findStatusById(Long id);

    Optional<ProcurementStatus> findProcurementStatusById(Long id);
}
