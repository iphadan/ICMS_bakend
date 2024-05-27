package com.cbo.CBO_NFOS_ICMS.repositories.ProcurementRepository;

import com.cbo.CBO_NFOS_ICMS.models.Procurement.Procurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProcurementRepository extends JpaRepository<Procurement, Long> {
    void deleteProcurementById(Long id);

    Optional<Procurement> findProcurementById(Long id);
    List<Procurement> findProcurementByBranchId(Long branchId);

    List<Procurement> findProcurementBySubProcessId(Long subprocessId);

    boolean existsByCaseId(String caseId);

    List<Procurement> findProcurementByTeamId(Long id);
}
