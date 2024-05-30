package com.cbo.CBO_NFOS_ICMS.models.Procurement;

import com.cbo.CBO_NFOS_ICMS.models.AllCategory;
import com.cbo.CBO_NFOS_ICMS.models.AllIrregularity;
import com.cbo.CBO_NFOS_ICMS.models.AllSubCategory;
import com.cbo.CBO_NFOS_ICMS.models.UserAndEmployee.Branch;
import com.cbo.CBO_NFOS_ICMS.models.UserAndEmployee.SubProcess;
import com.cbo.CBO_NFOS_ICMS.models.UserAndEmployee.Team;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
@Table(name = "procurement_table")
public class Procurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column(length = 64)
    private String ProcurementDate;

    @Column(length = 64)
    private String caseId;

    @ManyToOne
    @JoinColumn(name = "all_category_id")
    private AllCategory allCategory;
    @ManyToOne
    @JoinColumn(name = "all_sub_category_id")
    private AllSubCategory allSubCategory;

    @Column(length = 64)
    private String referenceNumber;

    @Column(length = 64)
    private String customerName;

    @ManyToOne
    @JoinColumn(name = "all_irregularity_id")
    private AllIrregularity irregularity;

    @Column( length = 64)
    private String amountInvolved;

    @Column( length = 64)
    private String responsiblePerson;

    @ManyToOne
    @JoinColumn(name = "procurement_status_id")
    private ProcurementStatus procurementStatus;

    @Column(length = 64)
    private String authorizedBy;
    @Column(length = 64)
    private String authorizationTimeStamp;
    private Boolean isAuthorized = false;

    @Column(length = 64)
    private String actionPlanDueDate;
    @Column(length = 64)
    private String approvedBy;
    private Boolean actionTaken = false;
    private Boolean escalatedByManager = false;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;


    @ManyToOne
    @JoinColumn(name = "sub_process_id")
    private SubProcess subProcess;

}


