package com.cbo.CBO_NFOS_ICMS.services.ProcurementService;

import com.cbo.CBO_NFOS_ICMS.exception.ResourceNotFoundException;
import com.cbo.CBO_NFOS_ICMS.exception.UserNotFoundException;
import com.cbo.CBO_NFOS_ICMS.models.DACGM.DailyActivityGapControl;
import com.cbo.CBO_NFOS_ICMS.models.Procurement.Procurement;
import com.cbo.CBO_NFOS_ICMS.repositories.ProcurementRepository.ProcurementRepository;
import com.cbo.CBO_NFOS_ICMS.services.UserAndEmployeeService.BranchService;
import com.cbo.CBO_NFOS_ICMS.services.UserAndEmployeeService.SubProcessService;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


@Service
public class ProcurementService {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private final SubProcessService subProcessService;
    private final BranchService organizationalUnitService;
    private final ProcurementRepository procurementRepository;

    public ProcurementService(SubProcessService subProcessService, BranchService organizationalUnitService, ProcurementRepository procurementRepository) {
        this.subProcessService = subProcessService;
        this.organizationalUnitService = organizationalUnitService;
        this.procurementRepository = procurementRepository;
    }
//I added addProcurement
    public Procurement addProcurement(Procurement procurement) {
        return procurementRepository.save(procurement);
    }

    public List<Procurement> findProcurement() {
        return procurementRepository.findAll();
    }

    public Procurement updateProcurement(Procurement procurement) {
        Optional<Procurement> optionalProcurement = procurementRepository.findById(procurement.getId());
        if (optionalProcurement.isPresent()) {
            Procurement existingProcurement = optionalProcurement.get();
            existingProcurement.setProcurementStatus(procurement.getProcurementStatus());
            existingProcurement.setProcurementDate(procurement.getProcurementDate()); // Add this line
            return procurementRepository.save(existingProcurement);
        } else {
            throw new ResourceNotFoundException("Procurement not found");
        }
    }



    public Procurement findProcurementById(Long id) {
        return procurementRepository.findProcurementById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id" + id + " was not found"));
    }

    public void deleteProcurement(Long id) {
        procurementRepository.deleteById(id);
    }

    public List<Procurement> findAllProcurementBYBranch(Long branchId) {
        return procurementRepository.findProcurementByBranchId(branchId);
    }

    public List<Procurement> findAllProcurement() {
        return procurementRepository.findAll();
    }
    public List<Procurement> findAllProcurementSubProcess(Long subProcessId) {
        return procurementRepository.findProcurementBySubProcessId(subProcessId);
    }

    public Procurement approveActionPlan(Procurement procurement) {
        Procurement row = procurementRepository.findById(procurement.getId())
                .orElseThrow(() -> new UserNotFoundException("Procurement by id = " + procurement.getId() + " was not found"));
        row.setActionPlanDueDate(procurement.getActionPlanDueDate());

        row.setActionTaken(true);
        return procurementRepository.save(row);
    }


    public Procurement escalatePlan(Long id) {
        Procurement row = procurementRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Procurement by id = " + id + " was not found"));
        row.setEscalatedByManager(true);
        return procurementRepository.save(row);
    }

    public List<Procurement> findAllProcurementInSpecificOrganizationalUnit(Long id) {

        return procurementRepository.findProcurementByTeamId(id);
    }

    public List<Procurement> findAllProcurementInSpecificSubProcess(Long subProcessId) {
        return procurementRepository.findProcurementBySubProcessId(subProcessId);
    }

    public boolean isCaseIdExists(String caseId) {
        return procurementRepository.existsByCaseId(caseId);
    }

    public int findProcurementSize() {
        return procurementRepository.findAll().size();
    }
}
