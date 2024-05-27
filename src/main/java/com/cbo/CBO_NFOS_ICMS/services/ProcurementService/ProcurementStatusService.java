package com.cbo.CBO_NFOS_ICMS.services.ProcurementService;

import com.cbo.CBO_NFOS_ICMS.exception.UserNotFoundException;
import com.cbo.CBO_NFOS_ICMS.models.Procurement.ProcurementStatus;
import com.cbo.CBO_NFOS_ICMS.repositories.ProcurementRepository.ProcurementStatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcurementStatusService {
    private final ProcurementStatusRepository procurementStatusRepository;

    public ProcurementStatusService(
            ProcurementStatusRepository procurementStatusRepository) {
        this.procurementStatusRepository = procurementStatusRepository;
    }

    public ProcurementStatus findProcurementStatusById(Long id) {
        return procurementStatusRepository.findProcurementStatusById(id)
                .orElseThrow(() -> new UserNotFoundException("Procurement Status by id = " + id + " was not found"));
    }

    public ProcurementStatus addProcurementStatus(ProcurementStatus as) {
        return procurementStatusRepository.save(as);
    }

    public List<ProcurementStatus> findAllProcurementStatus() {
        return procurementStatusRepository.findAll();
    }

}