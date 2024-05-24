package com.cbo.CBO_NFOS_ICMS.repositories.TradeRepository;

import com.cbo.CBO_NFOS_ICMS.models.Finance.Finance;
import com.cbo.CBO_NFOS_ICMS.models.Trade.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade,Long> {
    List<Trade> findTradeByBranchId(Long branchId);
    List<Trade> findTradeBySubProcessId(Long subProcessId);
    List<Trade> findFinanceByTeamId(Long unitID);
    boolean existsByCaseId(String caseId);
}
