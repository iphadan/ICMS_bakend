package com.cbo.CBO_NFOS_ICMS.repositories;

import com.cbo.CBO_NFOS_ICMS.models.TradeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllTradeTypeRepository extends JpaRepository<TradeType, Long> {
}
