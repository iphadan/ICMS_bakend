package com.cbo.CBO_NFOS_ICMS.repositories.TradeRepository;

import com.cbo.CBO_NFOS_ICMS.models.Trade.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends JpaRepository<Trade,Long> {
}
