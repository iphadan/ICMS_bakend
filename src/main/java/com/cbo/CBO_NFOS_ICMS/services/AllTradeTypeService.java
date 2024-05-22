package com.cbo.CBO_NFOS_ICMS.services;

import com.cbo.CBO_NFOS_ICMS.exception.UserNotFoundException;
import com.cbo.CBO_NFOS_ICMS.models.AllCategory;
import com.cbo.CBO_NFOS_ICMS.models.TradeType;
import com.cbo.CBO_NFOS_ICMS.repositories.AllCategoryRepository;

import com.cbo.CBO_NFOS_ICMS.repositories.AllTradeTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AllTradeTypeService {
    private final AllTradeTypeRepository allTradeTypeRepository;

    public AllTradeTypeService(AllTradeTypeRepository allTradeTypeRepository) {
        this.allTradeTypeRepository = allTradeTypeRepository;
    }

    public TradeType addAllTradeType(TradeType tradeType) {

        return allTradeTypeRepository.save(tradeType);
    }

    public List<TradeType> findAllTradeType() {
        return allTradeTypeRepository.findAll();

    }


    public List<TradeType> findAllTradeTypeBySubModuleName(String name) {
        List<TradeType> allTradeTypes = allTradeTypeRepository.findAll();
        allTradeTypes.removeIf(tradeType -> !tradeType.getSubModule().getCode().equals(name));
        return allTradeTypes;
    }



}
