package com.cbo.CBO_NFOS_ICMS.models.Procurement;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "procurement_status")
public class ProcurementStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false, length = 64)
    private String name;

    public ProcurementStatus(String name) {
        this.name = name;
    }
}
