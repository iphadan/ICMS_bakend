package com.cbo.CBO_NFOS_ICMS.services.TradeService;

import com.cbo.CBO_NFOS_ICMS.exception.ResourceNotFoundException;
import com.cbo.CBO_NFOS_ICMS.exception.UserNotFoundException;
import com.cbo.CBO_NFOS_ICMS.models.Finance.Finance;
import com.cbo.CBO_NFOS_ICMS.models.Trade.Trade;
import com.cbo.CBO_NFOS_ICMS.repositories.FinanceRepository.FinanceRepository;
import com.cbo.CBO_NFOS_ICMS.repositories.TradeRepository.TradeRepository;
import com.cbo.CBO_NFOS_ICMS.services.UserAndEmployeeService.BranchService;
import com.cbo.CBO_NFOS_ICMS.services.UserAndEmployeeService.SubProcessService;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
@Service
public class TradeService {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
private final TradeRepository tradeRepository;
    private final SubProcessService subProcessService;
    private final BranchService organizationalUnitService;

    public TradeService(TradeRepository tradeRepository,SubProcessService subProcessService, BranchService organizationalUnitService) {
        this.tradeRepository = tradeRepository;
        this.subProcessService = subProcessService;
        this.organizationalUnitService = organizationalUnitService;
    }

    public Trade addTrade(Trade trade) {
        return tradeRepository.save(trade);
    }

    public List<Trade> findTrade() {
        return tradeRepository.findAll();
    }

    public Trade updateTrade(Trade trade) {
        Optional<Trade> optionalTrade = tradeRepository.findById(trade.getId());
        if (optionalTrade.isPresent()) {
            Trade existingTrade = optionalTrade.get();
            existingTrade.setTradeStatus(trade.getTradeStatus());
            existingTrade.setTradeDate(trade.getTradeDate()); // Add this line
            return tradeRepository.save(existingTrade);
        } else {
            throw new ResourceNotFoundException("Trade not found");
        }
    }



    public Trade findTradeById(Long id) {
        return tradeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trade by id" + id + " was not found"));
    }

    public void deleteTrade(Long id) {
        tradeRepository.deleteById(id);
    }

//    public List<Trade> findAllFinanceBYBranch(Long branchId) {
//        return financeRepository.findFinanceByBranchId(branchId);
//    }

    public List<Trade> findAllTrade() {
        return tradeRepository.findAll();
    }
//    public List<Trade> findAllFinanceSubProcess(Long subProcessId) {
//        return financeRepository.findFinanceBySubProcessId(subProcessId);
//    }
//
//    public Finance approveActionPlan(Finance finance) {
//        Finance row = financeRepository.findById(finance.getId())
//                .orElseThrow(() -> new UserNotFoundException("Finance by id = " + finance.getId() + " was not found"));
//        row.setActionPlanDueDate(finance.getActionPlanDueDate());
//
//        row.setActionTaken(true);
//        return financeRepository.save(row);
//    }
//
//
//    public Finance escalatePlan(Long id) {
//        Finance row = financeRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Finance by id = " + id + " was not found"));
//        row.setEscalatedByManager(true);
//        return financeRepository.save(row);
//    }
//
//    public List<Finance> findAllFinanceInSpecificOrganizationalUnit(Long id) {
//
//        return financeRepository.findFinanceByTeamId(id);
//    }
//
//    public List<Finance> findAllFinanceInSpecificSubProcess(Long subProcessId) {
//        return financeRepository.findFinanceBySubProcessId(subProcessId);
//    }
//
//    public boolean isCaseIdExists(String caseId) {
//        return financeRepository.existsByCaseId(caseId);
//    }
//
//    public int findFinanceSize() {
//        return financeRepository.findAll().size();
//    }

}
