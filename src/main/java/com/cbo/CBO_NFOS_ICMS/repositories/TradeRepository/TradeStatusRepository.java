package com.cbo.CBO_NFOS_ICMS.repositories.TradeRepository;

import com.cbo.CBO_NFOS_ICMS.models.Finance.FinanceStatus;
import com.cbo.CBO_NFOS_ICMS.models.Trade.TradeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TradeStatusRepository extends JpaRepository<TradeStatus,Long> {
    Optional<TradeStatus> findStatusById(Long id);

    Optional<TradeStatus> findTradeStatusById(Long id);

}
