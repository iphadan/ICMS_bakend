package com.cbo.CBO_NFOS_ICMS.services.TradeService;

import com.cbo.CBO_NFOS_ICMS.exception.UserNotFoundException;
import com.cbo.CBO_NFOS_ICMS.models.Finance.FinanceStatus;
import com.cbo.CBO_NFOS_ICMS.models.Trade.TradeStatus;
import com.cbo.CBO_NFOS_ICMS.repositories.FinanceRepository.FinanceStatusRepository;
import com.cbo.CBO_NFOS_ICMS.repositories.TradeRepository.TradeStatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TradeStatusService {

    private final TradeStatusRepository tradeStatusRepository;

    public TradeStatusService(
            TradeStatusRepository tradeStatusRepository) {
        this.tradeStatusRepository = tradeStatusRepository;
    }

    public TradeStatus findTradeStatusById(Long id) {
        return tradeStatusRepository.findTradeStatusById(id)
                .orElseThrow(() -> new UserNotFoundException("Trade Status by id = " + id + " was not found"));
    }

    public TradeStatus addTradeStatus(TradeStatus as) {
        return tradeStatusRepository.save(as);
    }

    public List<TradeStatus> findAllTradeStatus() {
        return tradeStatusRepository.findAll();
    }

}
