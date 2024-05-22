package com.cbo.CBO_NFOS_ICMS.controllers;

import com.cbo.CBO_NFOS_ICMS.models.AllCategory;
import com.cbo.CBO_NFOS_ICMS.models.TradeType;
import com.cbo.CBO_NFOS_ICMS.services.AllTradeTypeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/allTradeType")
public class AllTradeTypeController {
    private final AllTradeTypeService allTradeTypeService;

    public AllTradeTypeController(AllTradeTypeService allTradeTypeService) {
        this.allTradeTypeService = allTradeTypeService;
    }

    @PostMapping("/getAllTradeType")
    public List<TradeType> findAllTradeTypeBySubModuleName(@RequestBody Map<String, String> requestBody) {
        String subModuleName = requestBody.get("subModuleName");
        return allTradeTypeService.findAllTradeTypeBySubModuleName(subModuleName);
    }
}
