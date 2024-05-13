package com.cbo.CBO_NFOS_ICMS.models.Trade;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tradeService_status")
public class TradeStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)

    private Long id;
    @Column(nullable = false, length = 64)
    private String name;

    public TradeStatus(String name) {
        this.name = name;
    }
}
