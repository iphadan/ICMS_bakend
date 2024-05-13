package com.cbo.CBO_NFOS_ICMS.seeders;

import com.cbo.CBO_NFOS_ICMS.models.AllCategory;
import com.cbo.CBO_NFOS_ICMS.models.AllIrregularity;
import com.cbo.CBO_NFOS_ICMS.models.AllSubCategory;
import com.cbo.CBO_NFOS_ICMS.models.CIPM.CollateralType;
import com.cbo.CBO_NFOS_ICMS.models.CIPM.InsuranceCoverageType;
import com.cbo.CBO_NFOS_ICMS.models.CIPM.Status;
import com.cbo.CBO_NFOS_ICMS.models.CIPM.SuspectedFraudsterProfession;
import com.cbo.CBO_NFOS_ICMS.models.DACGM.ActivityStatus;
import com.cbo.CBO_NFOS_ICMS.models.DCQ.ActionTaken;
import com.cbo.CBO_NFOS_ICMS.models.DCQ.ChequeType;
import com.cbo.CBO_NFOS_ICMS.models.Finance.FinanceStatus;
import com.cbo.CBO_NFOS_ICMS.models.IFB.ProductType;
import com.cbo.CBO_NFOS_ICMS.models.IFR.CaseStatus;
import com.cbo.CBO_NFOS_ICMS.models.IFR.FraudType;
import com.cbo.CBO_NFOS_ICMS.models.SubModule;
import com.cbo.CBO_NFOS_ICMS.models.share.ShareStatus;
import com.cbo.CBO_NFOS_ICMS.repositories.AllCategoryRepository;
import com.cbo.CBO_NFOS_ICMS.repositories.AllIrregularityRepository;
import com.cbo.CBO_NFOS_ICMS.repositories.AllSubCategoryRepository;
import com.cbo.CBO_NFOS_ICMS.repositories.CIPMRepository.CollateralTypeRepository;
import com.cbo.CBO_NFOS_ICMS.repositories.CIPMRepository.InsuranceCoverageTypeRepository;
import com.cbo.CBO_NFOS_ICMS.repositories.CIPMRepository.StatusRepository;
import com.cbo.CBO_NFOS_ICMS.repositories.CIPMRepository.SuspectedFraudsterProfessionRepository;
import com.cbo.CBO_NFOS_ICMS.repositories.DACGMRepository.ActivityStatusRepository;
import com.cbo.CBO_NFOS_ICMS.repositories.DCQRepository.ActionTakenRepository;
import com.cbo.CBO_NFOS_ICMS.repositories.DCQRepository.ChequeTypeRepository;
import com.cbo.CBO_NFOS_ICMS.repositories.FinanceRepository.FinanceStatusRepository;
import com.cbo.CBO_NFOS_ICMS.repositories.IFBRepository.IfbStatusRepository;
import com.cbo.CBO_NFOS_ICMS.repositories.IFBRepository.ProductTypeRepository;
import com.cbo.CBO_NFOS_ICMS.repositories.IFRRepository.CaseStatusRepository;
import com.cbo.CBO_NFOS_ICMS.repositories.IFRRepository.FraudTypeRepository;
import com.cbo.CBO_NFOS_ICMS.repositories.SubModuleRepository;
import com.cbo.CBO_NFOS_ICMS.repositories.shareRepository.ShareStatusRepository;
import com.cbo.CBO_NFOS_ICMS.services.AllCategoryService;
import com.cbo.CBO_NFOS_ICMS.services.AllSubCategoryService;
import com.cbo.CBO_NFOS_ICMS.services.UserAndEmployeeService.SubModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class DatabaseSeeder {

    //private Logger logger = Logger.getLogger(DatabaseSeeder.class);
    private JdbcTemplate jdbcTemplate;
    private CollateralTypeRepository collateralTypeRepository;
    private ProductTypeRepository productTypeRepository;
    private StatusRepository statusRepository;
    private InsuranceCoverageTypeRepository insuranceCoverageTypeRepository;
    private ChequeTypeRepository chequeTypeRepository;
    private ActionTakenRepository actionTakenRepository;
    private CaseStatusRepository caseStatusRepository;
    private ActivityStatusRepository activityStatusRepository;
    private FinanceStatusRepository financeStatusRepository;

    private ShareStatusRepository shareStatusRepository;

    private AllCategoryRepository allCategoryRepository;
    private FraudTypeRepository fraudTypeRepository;
    private SuspectedFraudsterProfessionRepository suspectedFraudsterProfessionRepository;
    private AllSubCategoryRepository allSubCategoryRepository;
    private AllIrregularityRepository allIrregularityRepository;
    private SubModuleRepository subModuleRepository;
    private SubModuleService subModuleService;
    private AllCategoryService allCategoryService;
    private AllSubCategoryService allSubCategoryService;
    private List<SubModule> subModules;
    private List<AllCategory> allCategories;
    private List<AllSubCategory> allSubCategories;

    @Autowired
    public DatabaseSeeder(
            JdbcTemplate jdbcTemplate,
            AllSubCategoryRepository allSubCategoryRepository,
            CollateralTypeRepository collateralTypeRepository,
            ProductTypeRepository productTypeRepository,
            StatusRepository statusRepository,
            IfbStatusRepository ifbStatusRepository,
            InsuranceCoverageTypeRepository insuranceCoverageTypeRepository,
            ChequeTypeRepository chequeTypeRepository,
            ActionTakenRepository actionTakenRepository,
            CaseStatusRepository caseStatusRepository,
            ActivityStatusRepository activityStatusRepository,
            FinanceStatusRepository financeStatusRepository,
            AllCategoryRepository allCategoryRepository,
            FraudTypeRepository fraudTypeRepository,
            SuspectedFraudsterProfessionRepository suspectedFraudsterProfessionRepository,
            SubModuleRepository subModuleRepository,
            SubModuleService subModuleService,
            AllCategoryService allCategoryService,
            AllSubCategoryService allSubCategoryService,
            AllIrregularityRepository allIrregularityRepository,
            ShareStatusRepository shareStatusRepository

    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.allSubCategoryRepository = allSubCategoryRepository;
        this.collateralTypeRepository = collateralTypeRepository;
        this.shareStatusRepository= shareStatusRepository;
        this.productTypeRepository = productTypeRepository;
        this.insuranceCoverageTypeRepository = insuranceCoverageTypeRepository;
        this.chequeTypeRepository = chequeTypeRepository;
        this.actionTakenRepository = actionTakenRepository;
        this.statusRepository=statusRepository;
        this.caseStatusRepository = caseStatusRepository;
        this.activityStatusRepository = activityStatusRepository;
        this.financeStatusRepository = financeStatusRepository;
        this.allCategoryRepository = allCategoryRepository;
        this.fraudTypeRepository = fraudTypeRepository;
        this.suspectedFraudsterProfessionRepository = suspectedFraudsterProfessionRepository;
        this.subModuleRepository = subModuleRepository;
        this.subModuleService = subModuleService;
        this.allCategoryService = allCategoryService;
        this.allSubCategoryService = allSubCategoryService;
        this.allIrregularityRepository = allIrregularityRepository;
    }

    @PostConstruct
    public void seed() {

        // TYPES
        seedCollateralType("Premises and Buildings/Houses");
        seedCollateralType("Motor Vehicles");
        seedCollateralType("Construction Machineries");
        seedCollateralType("Agricultural Machinery");
        seedCollateralType("Merchandise");
        seedCollateralType("Land Lease Right/Coffee Plantation");
        seedCollateralType("Negotiable instruments");
        seedCollateralType("Import and Export documents");
        seedCollateralType("Warehouse Receipts");
        seedCollateralType("Share Certificates");
        seedCollateralType("Movable Collateral");
        seedCollateralType("Other");

        seedProductType("Murabaha");
        seedProductType("Salam");
        seedProductType("Istisna");
        seedProductType("Qard");
        seedProductType("Kafala");
        seedProductType("Deposit Account");
        seedProductType("Pool Administration");
        seedProductType("Profit/Loss Sharing Management");
        seedProductType("Other");


        seedStatus("Active");
        seedStatus("Closed");

        seedInsuranceCoverageType("Fire and related perils");
        seedInsuranceCoverageType("Motor Comprehensive; Third Party Liability");
        seedInsuranceCoverageType("Transit insurance for seized vehicles for foreclosure");
        seedInsuranceCoverageType("Fire and Theft for non-operational vehicles");
        seedInsuranceCoverageType("Bandits, Shifta and Gorilla (BSG)");
        seedInsuranceCoverageType("Fire, Theft and Allied Perils");
        seedInsuranceCoverageType("Burglary and House Breaking");
        seedInsuranceCoverageType("Goods-in-Transit insurance for imports L/C settlement and fertilizer loans");
        seedInsuranceCoverageType("Warehouse Operators Liability for agricultural produces");
        seedInsuranceCoverageType("Political");
        seedInsuranceCoverageType("Other");

        seedChequeTypesTable("Conventional 25 (OR)");
        seedChequeTypesTable("Conventional 50 (BA)");
        seedChequeTypesTable("Conventional 100 (CO)");
        seedChequeTypesTable("IFB 25 (AM)");
        seedChequeTypesTable("IFB 50 (NA)");
        seedChequeTypesTable("IFB 100 (HA)");

        seedFraudTypesTable("Embezzlement");
        seedFraudTypesTable("Cheating");
        seedFraudTypesTable("Forgery");
        seedFraudTypesTable("Other");


        //STATUS
        seedCasesStatusTable("Outstanding");
        seedCasesStatusTable("Closed");
        seedCasesStatusTable("Written Off");

        seedActivitiesStatusTable("Open");
        seedActivitiesStatusTable("Closed");

        seedFinanciesStatusTable("Open");
        seedFinanciesStatusTable("Closed");

        seedShareStatusTable("Open");
        seedShareStatusTable("Closed");

        //OTHER
        seedSuspectedFraudsterProfessionsTable("Director");
        seedSuspectedFraudsterProfessionsTable("Employee");
        seedSuspectedFraudsterProfessionsTable("Customer");
        seedSuspectedFraudsterProfessionsTable("Other");

        seedActionsTakenTable("All debit transactions blocked.");
        seedActionsTakenTable("All debit transactions blocked, and a fine of 3% of the cheque amount, but not exceeding Birr 25,000, was collected.");
        seedActionsTakenTable("All debit transactions blocked, and a fine of 5% of the cheque amount, but not exceeding Birr 50,000, was collected.");


        //SUB-MODULE, ALL-CATEGORY, ALL-SUB-CATEGORY, ALL-IRREGULARITY

        seedSubModulesTable("IFR", "Incident/Fraud Report");
        seedSubModulesTable("DACGM", "Daily Activity Control Gap Monitoring");
        seedSubModulesTable("SMPIC", "Share Management Process Internal Controller");
        seedSubModulesTable("IBDPIC", "IBD Process Internal Controller");
        seedSubModulesTable("IFBPIC", "IFB Process Internal Controller");
        seedSubModulesTable("FPIC", "Finance Process Internal Controller");
        seedSubModulesTable("PFPIC", "Procurement and Faculty Process Internal Controller");
        seedSubModulesTable("DCQ", "Dishonored Cheque");
        seedSubModulesTable("CIPM", "Collateral Insurance Policy Monitoring");

        this.subModules = this.subModuleService.findAllSubModule();
        seedAllCategoriesTable("Cash", findSubModuleByCode("IFR"));
        seedAllCategoriesTable("Deposit", findSubModuleByCode("IFR"));
        seedAllCategoriesTable("Loan", findSubModuleByCode("IFR"));
        seedAllCategoriesTable("Forex", findSubModuleByCode("IFR"));
        seedAllCategoriesTable("Interbank Account", findSubModuleByCode("IFR"));
        seedAllCategoriesTable("Clearing", findSubModuleByCode("IFR"));
        seedAllCategoriesTable("Off Balance Sheet", findSubModuleByCode("IFR"));
        seedAllCategoriesTable("Cheques/DDs", findSubModuleByCode("IFR"));
        seedAllCategoriesTable("Other", findSubModuleByCode("IFR"));


        seedAllCategoriesTable("Cash Management", findSubModuleByCode("DACGM")); //2
        seedAllCategoriesTable("Customer Account Operation", findSubModuleByCode("DACGM")); //2
        seedAllCategoriesTable("Suspense Account Management", findSubModuleByCode("DACGM"));
        seedAllCategoriesTable("Loan and Advances", findSubModuleByCode("DACGM")); //2
        seedAllCategoriesTable("Forex Trading", findSubModuleByCode("DACGM")); //2
        seedAllCategoriesTable("Expense Management", findSubModuleByCode("DACGM"));

        seedAllCategoriesTable("Digital Banking", findSubModuleByCode("DACGM"));
        seedAllCategoriesTable("Branch Opening Requirements", findSubModuleByCode("DACGM"));
        seedAllCategoriesTable("Key Book and Rubber Stamp", findSubModuleByCode("DACGM"));
        seedAllCategoriesTable("Interest Free Banking", findSubModuleByCode("DACGM"));
        seedAllCategoriesTable("Stock and Negotiable Instrument Management", findSubModuleByCode("DACGM"));
        seedAllCategoriesTable("Fixed Asset Management", findSubModuleByCode("DACGM"));
        seedAllCategoriesTable("Human Resource Management", findSubModuleByCode("DACGM"));

        seedAllCategoriesTable("Documentation", findSubModuleByCode("IFBPIC"));
        seedAllCategoriesTable("Data inputting to the CBS", findSubModuleByCode("IFBPIC"));
        seedAllCategoriesTable("Files Handling", findSubModuleByCode("IFBPIC"));
        seedAllCategoriesTable("Compliance", findSubModuleByCode("IFBPIC"));
        seedAllCategoriesTable("Fees, Commissions, Penalties and Charges Handling", findSubModuleByCode("IFBPIC"));
        seedAllCategoriesTable("Collateral", findSubModuleByCode("IFBPIC"));
        seedAllCategoriesTable("Insurance Policy", findSubModuleByCode("IFBPIC"));
        seedAllCategoriesTable("Loan Follow-Up", findSubModuleByCode("IFBPIC"));

        seedAllCategoriesTable("Liquidity Management", findSubModuleByCode("FPIC"));
        seedAllCategoriesTable("Financial Reports", findSubModuleByCode("FPIC"));
        seedAllCategoriesTable("Procurement", findSubModuleByCode("FPIC"));
        seedAllCategoriesTable("Depreciation", findSubModuleByCode("FPIC"));
        seedAllCategoriesTable("Disposal of Fixed Assets", findSubModuleByCode("FPIC"));
        seedAllCategoriesTable("Payroll", findSubModuleByCode("FPIC"));
        seedAllCategoriesTable("Fund Management", findSubModuleByCode("FPIC"));
        seedAllCategoriesTable("Tax", findSubModuleByCode("FPIC"));
        seedAllCategoriesTable("Reconciliation", findSubModuleByCode("FPIC"));

        seedAllCategoriesTable("A Deceased shareholder (individual)", findSubModuleByCode("SMPIC"));
        seedAllCategoriesTable("Capital Contribution In-kind", findSubModuleByCode("SMPIC"));
        seedAllCategoriesTable("Dividend Payment", findSubModuleByCode("SMPIC"));
        seedAllCategoriesTable("Floating of New Shares", findSubModuleByCode("SMPIC"));
        seedAllCategoriesTable("Joint shareholding", findSubModuleByCode("SMPIC"));
        seedAllCategoriesTable("Limitation on Share Acquisition", findSubModuleByCode("SMPIC"));
        seedAllCategoriesTable("Loss of share certificates", findSubModuleByCode("SMPIC"));
        seedAllCategoriesTable("Period for Deposit of proxy", findSubModuleByCode("SMPIC"));
        seedAllCategoriesTable("Preparation of Share Certificate", findSubModuleByCode("SMPIC"));
        seedAllCategoriesTable("Qualification shares", findSubModuleByCode("SMPIC"));
        seedAllCategoriesTable("Share and Subscription Right Transfer", findSubModuleByCode("SMPIC"));
        seedAllCategoriesTable("Share for Minors", findSubModuleByCode("SMPIC"));
        seedAllCategoriesTable("Share Registration", findSubModuleByCode("SMPIC"));
        seedAllCategoriesTable("Share Subscription", findSubModuleByCode("SMPIC"));
        seedAllCategoriesTable("Other ", findSubModuleByCode("SMPIC"));



        this.allCategories = this.allCategoryService.findAllAllCategory();
        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Cash Management"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Cash Management"));

        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Customer Account Operation"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Customer Account Operation"));

        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Suspense Account Management"));

        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Loan and Advances"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Loan and Advances"));

        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Forex Trading"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Forex Trading"));

        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Expense Management"));

        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Digital Banking"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Branch Opening Requirements"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Key Book and Rubber Stamp"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Interest Free Banking"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Stock and Negotiable Instrument Management"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Fixed Asset Management"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Human Resource Management"));


        //IFB

        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Documentation"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Documentation"));

        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Data inputting to the CBS"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Data inputting to the CBS"));

        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Files Handling"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Files Handling"));

        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Compliance"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Compliance"));

        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Fees, Commissions, Penalties and Charges Handling"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Fees, Commissions, Penalties and Charges Handling"));

        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Insurance Policy"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Insurance Policy"));

        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Loan Follow-Up"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Loan Follow-Up"));


        //FPIC

        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Liquidity Management"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Liquidity Management"));

        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Financial Reports"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Financial Reports"));

        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Procurement"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Procurement"));

        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Depreciation"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Depreciation"));

        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Disposal of Fixed Assets"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Disposal of Fixed Assets"));

        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Payroll"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Payroll"));

        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Fund Management"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Fund Management"));

        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Tax"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Tax"));


        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Reconciliation"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Reconciliation"));

        //Share
        seedAllSubCategoriesTable("Financial", findAllCategoryByName("A Deceased shareholder (individual)"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("A Deceased shareholder (individual)"));

        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Capital Contribution In-kind"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Capital Contribution In-kind"));

        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Dividend Payment"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Dividend Payment"));

        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Floating of New Shares"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Floating of New Shares"));

        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Joint shareholding"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Joint shareholding"));

        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Limitation on Share Acquisition"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Limitation on Share Acquisition"));

        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Loss of share certificates"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Loss of share certificates"));

        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Period for Deposit of proxy"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Period for Deposit of proxy"));

        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Preparation of Share Certificate"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Preparation of Share Certificate"));

        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Qualification shares"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Qualification shares"));

        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Share and Subscription Right Transfer"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Share and Subscription Right Transfer"));

        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Share for Minors"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Share for Minors"));

        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Share Registration"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Share Registration"));

        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Share Subscription"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Share Subscription"));

        seedAllSubCategoriesTable("Financial", findAllCategoryByName("Other"));
        seedAllSubCategoriesTable("Non-Financial", findAllCategoryByName("Other"));



        this.allSubCategories = this.allSubCategoryService.findAllSubCategory();
        seedAllIrregularitiesTable("Cash difference between physical count and GL balance", findAllSubCategoryByNameAndCategoryName("Financial", "Cash Management"));
        seedAllIrregularitiesTable("Operating in excess of average cash holding limit", findAllSubCategoryByNameAndCategoryName("Financial", "Cash Management"));
        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Financial", "Cash Management"));

        seedAllIrregularitiesTable("Cheques deposited in customers' account prior to passing the cheques clearance process", findAllSubCategoryByNameAndCategoryName("Financial", "Customer Account Operation"));
        seedAllIrregularitiesTable("Deposit made to third party without delegation of  account holder", findAllSubCategoryByNameAndCategoryName("Financial", "Customer Account Operation"));
        seedAllIrregularitiesTable("Payment effected made while amount in words and figures differs", findAllSubCategoryByNameAndCategoryName("Financial", "Customer Account Operation"));
        seedAllIrregularitiesTable("Receipt made while amount in words and figures differs", findAllSubCategoryByNameAndCategoryName("Financial", "Customer Account Operation"));
        seedAllIrregularitiesTable("Payment made on post/pre dated cheque", findAllSubCategoryByNameAndCategoryName("Financial", "Customer Account Operation"));
        seedAllIrregularitiesTable("Payment/Transfer made without confirmation", findAllSubCategoryByNameAndCategoryName("Financial", "Customer Account Operation"));
        seedAllIrregularitiesTable("Payments made without customers'/full signatories signature", findAllSubCategoryByNameAndCategoryName("Financial", "Customer Account Operation"));
        seedAllIrregularitiesTable("Transfer made without customers'/full signatories signature", findAllSubCategoryByNameAndCategoryName("Financial", "Customer Account Operation"));
        seedAllIrregularitiesTable("Payments made without retaining copy of payee ID", findAllSubCategoryByNameAndCategoryName("Financial", "Customer Account Operation"));
        seedAllIrregularitiesTable("Payments made on value cash bases", findAllSubCategoryByNameAndCategoryName("Financial", "Customer Account Operation"));
        seedAllIrregularitiesTable("Payments made without having sufficient balance", findAllSubCategoryByNameAndCategoryName("Financial", "Customer Account Operation"));
        seedAllIrregularitiesTable("Reversal Made Without Sufficient Reason", findAllSubCategoryByNameAndCategoryName("Financial", "Customer Account Operation"));
        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Financial", "Customer Account Operation"));

        seedAllIrregularitiesTable("Abnormal/Unknown balance kept on GL for long period of time", findAllSubCategoryByNameAndCategoryName("Financial", "Suspense Account Management"));
        seedAllIrregularitiesTable("Long outstanding payable Items", findAllSubCategoryByNameAndCategoryName("Financial", "Suspense Account Management"));
        seedAllIrregularitiesTable("Long outstanding receivables Items", findAllSubCategoryByNameAndCategoryName("Financial", "Suspense Account Management"));
        seedAllIrregularitiesTable("Suspense accounts do not have genuine tracer", findAllSubCategoryByNameAndCategoryName("Financial", "Suspense Account Management"));
        seedAllIrregularitiesTable("Tracer run up not  balanced with GL", findAllSubCategoryByNameAndCategoryName("Financial", "Suspense Account Management"));
        seedAllIrregularitiesTable("Written off/written back decision is made with out approval of authorized personnel's", findAllSubCategoryByNameAndCategoryName("Financial", "Suspense Account Management"));
        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Financial", "Suspense Account Management"));

        seedAllIrregularitiesTable("Appeal processing fee not collected", findAllSubCategoryByNameAndCategoryName("Financial", "Loan and Advances"));
        seedAllIrregularitiesTable("Cancellation/Suspension of foreclosure decision fee not collected", findAllSubCategoryByNameAndCategoryName("Financial", "Loan and Advances"));
        seedAllIrregularitiesTable("Collateral release/amendment fee not collected", findAllSubCategoryByNameAndCategoryName("Financial", "Loan and Advances"));
        seedAllIrregularitiesTable("Collateral replacement fee not collected", findAllSubCategoryByNameAndCategoryName("Financial", "Loan and Advances"));
        seedAllIrregularitiesTable("Credit information fee not collected", findAllSubCategoryByNameAndCategoryName("Financial", "Loan and Advances"));
        seedAllIrregularitiesTable("Extension of temporary OD or Overdraw or Guarantee not collected", findAllSubCategoryByNameAndCategoryName("Financial", "Loan and Advances"));
        seedAllIrregularitiesTable("Guarantee extension/Amendment fee not collected", findAllSubCategoryByNameAndCategoryName("Financial", "Loan and Advances"));
        seedAllIrregularitiesTable("Insurance premium payment charge not collected", findAllSubCategoryByNameAndCategoryName("Financial", "Loan and Advances"));
        seedAllIrregularitiesTable("Loan file Transfer fee not collected", findAllSubCategoryByNameAndCategoryName("Financial", "Loan and Advances"));
        seedAllIrregularitiesTable("Loan processing fee not collected", findAllSubCategoryByNameAndCategoryName("Financial", "Loan and Advances"));
        seedAllIrregularitiesTable("Merchandise is pledged charge not collected", findAllSubCategoryByNameAndCategoryName("Financial", "Loan and Advances"));
        seedAllIrregularitiesTable("Merchandise is released charge not collected", findAllSubCategoryByNameAndCategoryName("Financial", "Loan and Advances"));
        seedAllIrregularitiesTable("Property estimation charge not collected", findAllSubCategoryByNameAndCategoryName("Financial", "Loan and Advances"));
        seedAllIrregularitiesTable("Renewal fee of existing credit facility limit not collected", findAllSubCategoryByNameAndCategoryName("Financial", "Loan and Advances"));
        seedAllIrregularitiesTable("Repayment amendment charge not collected", findAllSubCategoryByNameAndCategoryName("Financial", "Loan and Advances"));
        seedAllIrregularitiesTable("Rescheduling fee not collected", findAllSubCategoryByNameAndCategoryName("Financial", "Loan and Advances"));
        seedAllIrregularitiesTable("Stamp duty charge not collected on disbursed loan facilities", findAllSubCategoryByNameAndCategoryName("Financial", "Loan and Advances"));
        seedAllIrregularitiesTable("Waiver of payment charge not collected", findAllSubCategoryByNameAndCategoryName("Financial", "Loan and Advances"));
        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Financial", "Loan and Advances"));

        seedAllIrregularitiesTable("Payments made without recipient signature", findAllSubCategoryByNameAndCategoryName("Financial", "Forex Trading"));
        seedAllIrregularitiesTable("Payments made without retaining copy of payee ID", findAllSubCategoryByNameAndCategoryName("Financial", "Forex Trading"));
        seedAllIrregularitiesTable("Purchase of forex made without the standard purchase invoice", findAllSubCategoryByNameAndCategoryName("Financial", "Forex Trading"));
        seedAllIrregularitiesTable("Sales of forex made without the standard purchase invoice", findAllSubCategoryByNameAndCategoryName("Financial", "Forex Trading"));
        seedAllIrregularitiesTable("KYC data not entered onto the remittance agent system correctly", findAllSubCategoryByNameAndCategoryName("Financial", "Forex Trading"));
        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Financial", "Forex Trading"));

        seedAllIrregularitiesTable("Expense payment made without having  genuine source document", findAllSubCategoryByNameAndCategoryName("Financial", "Expense Management"));
        seedAllIrregularitiesTable("Expense payment made without being approved by authorized person and/or within discretionary limit", findAllSubCategoryByNameAndCategoryName("Financial", "Expense Management"));
        seedAllIrregularitiesTable("Expense payment made above the branch discretionary limit", findAllSubCategoryByNameAndCategoryName("Financial", "Expense Management"));
        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Financial", "Expense Management"));

        seedAllIrregularitiesTable("Absence of segregating of deteriorated notes with good ones", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Cash Management"));
        seedAllIrregularitiesTable("Keeping unwrapped cash in vault", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Cash Management"));
        seedAllIrregularitiesTable("Neatness of cash book is not kept", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Cash Management"));
        seedAllIrregularitiesTable("Opening and closing of vault and atm cash safe  are not made by authorized persons", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Cash Management"));
        seedAllIrregularitiesTable("Deteriorated cash was kept for long period of time", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Cash Management"));
        seedAllIrregularitiesTable("Necessary remedial action is not carried out when cash shortage/overage is revealed", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Cash Management"));
        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Cash Management"));

        seedAllIrregularitiesTable("Accounts opened without fulfilling  valid KYC documents", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Customer Account Operation"));
        seedAllIrregularitiesTable("Authentications of power of attorney was not made", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Customer Account Operation"));
        seedAllIrregularitiesTable("Opening documents were not signed by the account holder/signatories", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Customer Account Operation"));
        seedAllIrregularitiesTable("Opening documents were not signed by authorized staff", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Customer Account Operation"));
        seedAllIrregularitiesTable("Separate file was not maintained for closed accounts", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Customer Account Operation"));
        seedAllIrregularitiesTable("Unused chequebooks of closed current accounts are Not  collected", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Customer Account Operation"));
        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Customer Account Operation"));

        seedAllIrregularitiesTable("Collateral held was partially/fully damaged", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Loan and Advances"));
        seedAllIrregularitiesTable("Credit information was not attached with the loan file", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Loan and Advances"));
        seedAllIrregularitiesTable("Insurance policy of the collateral expired", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Loan and Advances"));
        seedAllIrregularitiesTable("Loan and/or mortgage contract has not been signed by Borrower/his/her spouse and/or Mortgagor/his/her spouse", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Loan and Advances"));
        seedAllIrregularitiesTable("Missing tax clearance  of borrowers and /or mortgagors", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Loan and Advances"));
        seedAllIrregularitiesTable("Necessary documents were not collected before the loan was disbursed as per the check list", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Loan and Advances"));
        seedAllIrregularitiesTable("Original LHC and/or Blue print and /or Libre is not presented", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Loan and Advances"));
        seedAllIrregularitiesTable("Power of attorney not  authenticated", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Loan and Advances"));
        seedAllIrregularitiesTable("Valid Marriage certificate of borrowers and/or mortgagors not collected", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Loan and Advances"));
        seedAllIrregularitiesTable("Revenue stamp not affixed on loan and/or mortgage contracts", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Loan and Advances"));
        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Loan and Advances"));

        seedAllIrregularitiesTable("ATM are linked on the system prior to delivery", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Digital Banking"));
        seedAllIrregularitiesTable("ATM card and PIN are not managed dually", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Digital Banking"));
        seedAllIrregularitiesTable("Undelivered ATM kept for more than 3 months", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Digital Banking"));
        seedAllIrregularitiesTable("ATM card  delivery register book are not signed by the collector", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Digital Banking"));
        seedAllIrregularitiesTable("PIN delivery register book not signed by the collector", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Digital Banking"));
        seedAllIrregularitiesTable("Coopay accounts opened without fulfilling  valid KYC documents", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Digital Banking"));
        seedAllIrregularitiesTable("Coopay account opening documents were not signed by the account holder/signatories", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Digital Banking"));
        seedAllIrregularitiesTable("Coopay account opening documents were not signed by authorized staff", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Digital Banking"));
        seedAllIrregularitiesTable("Agent accounts opened without fulfilling  valid KYC documents", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Digital Banking"));
        seedAllIrregularitiesTable("Merchant accounts opened without fulfilling  valid KYC documents", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Digital Banking"));
        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Digital Banking"));




        seedAllIrregularitiesTable("The branch is not adequately guarded", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Branch Opening Requirements"));
        seedAllIrregularitiesTable("All windows and glass walls not grilled", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Branch Opening Requirements"));
        seedAllIrregularitiesTable("Appropriate training is not given to staff members of the branch", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Branch Opening Requirements"));
        seedAllIrregularitiesTable("Cash loading and unloading area is not suitable", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Branch Opening Requirements"));
        seedAllIrregularitiesTable("Cash-room access  is not  restricted to authorized persons only", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Branch Opening Requirements"));
        seedAllIrregularitiesTable("Customer compliant register book/box doesn't exist", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Branch Opening Requirements"));
        seedAllIrregularitiesTable("Fire extinguishers is not placed in appropriate area", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Branch Opening Requirements"));
        seedAllIrregularitiesTable("Fire extinguishers is expired", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Branch Opening Requirements"));
        seedAllIrregularitiesTable("Insufficient check register book doesn't exist", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Branch Opening Requirements"));
        seedAllIrregularitiesTable("Proper ventilation and circulation of fresh air is not available", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Branch Opening Requirements"));
        seedAllIrregularitiesTable("Sufficient and suitable lighting is not available", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Branch Opening Requirements"));
        seedAllIrregularitiesTable("Suitable and clean sanitary service is not available", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Branch Opening Requirements"));
        seedAllIrregularitiesTable("The branch hall and staff operating area are not suitable for the business", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Branch Opening Requirements"));
        seedAllIrregularitiesTable("The branch hasn't appropriate strong room or safe/vault;", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Branch Opening Requirements"));
        seedAllIrregularitiesTable("The branch’s location and building is not suitable for the business", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Branch Opening Requirements"));
        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Branch Opening Requirements"));

        seedAllIrregularitiesTable("Absence of rubber stamp register book", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Key Book and Rubber Stamp"));
        seedAllIrregularitiesTable("Absence of key book register book", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Key Book and Rubber Stamp"));
        seedAllIrregularitiesTable("Rubber stamp register book not updated to current holders", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Key Book and Rubber Stamp"));
        seedAllIrregularitiesTable("Key book  register book not updated to current holders", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Key Book and Rubber Stamp"));
        seedAllIrregularitiesTable("Handing/taking over of stamps are Not performed upon transfer to other of staff", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Key Book and Rubber Stamp"));
        seedAllIrregularitiesTable("Handing/taking over of key are Not performed upon transfer to other staff", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Key Book and Rubber Stamp"));
        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Key Book and Rubber Stamp"));

        seedAllIrregularitiesTable("Conventional account opening formats where used to open IFB type account and vise versa", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Interest Free Banking"));
        seedAllIrregularitiesTable("Employees assigned in IFB have not taken proper training", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Interest Free Banking"));
        seedAllIrregularitiesTable("Labeled segregated window for IFB service don't exist", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Interest Free Banking"));
        seedAllIrregularitiesTable("Mixed service delivery on window", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Interest Free Banking"));
        seedAllIrregularitiesTable("Proper separate maintenance of records is not kept", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Interest Free Banking"));
        seedAllIrregularitiesTable("Ticket of IFB used for conventional banking and vise versa", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Interest Free Banking"));
        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Interest Free Banking"));

        seedAllIrregularitiesTable("Cheque delivered to customer without collector's signature on the cheque delivery book", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Stock and Negotiable Instrument Management"));
        seedAllIrregularitiesTable("Physical Passbook stock  are not balance with control registered", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Stock and Negotiable Instrument Management"));
        seedAllIrregularitiesTable("Physical cheque book stock  are not balance with control registered", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Stock and Negotiable Instrument Management"));
        seedAllIrregularitiesTable("Physical  cpo’s stock  are not balanced with control registered", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Stock and Negotiable Instrument Management"));
        seedAllIrregularitiesTable("Passbook of our branch is not registered on register book item by item", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Stock and Negotiable Instrument Management"));
        seedAllIrregularitiesTable("Prepared cheques undelivered for long-period of time", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Stock and Negotiable Instrument Management"));
        seedAllIrregularitiesTable("Stock and negotiable instrument are not  kept in safe place", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Stock and Negotiable Instrument Management"));
        seedAllIrregularitiesTable("Stock and negotiable instrument are not handled by authorized personnel", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Stock and Negotiable Instrument Management"));
        seedAllIrregularitiesTable("Stock and negotiable instrument issuance is not made on FIFO method", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Stock and Negotiable Instrument Management"));
        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Stock and Negotiable Instrument Management"));

        seedAllIrregularitiesTable("Fixed assets are not being handled in a safe manner", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Fixed Asset Management"));
        seedAllIrregularitiesTable("Physical fixed asset count is not balanced with control register", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Fixed Asset Management"));
        seedAllIrregularitiesTable("Some fixed assets are not registered on its register book", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Fixed Asset Management"));
        seedAllIrregularitiesTable("Some/all fixed assets do not bear tag number", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Fixed Asset Management"));
        seedAllIrregularitiesTable("Timely fixed asset disposal were not taken on defective items", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Fixed Asset Management"));
        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Fixed Asset Management"));

        seedAllIrregularitiesTable("Attendance sheets were not properly managed", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Human Resource Management"));
        seedAllIrregularitiesTable("Dressing code of the bank is not  followed", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Human Resource Management"));
        seedAllIrregularitiesTable("There is exchange of password among staff", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Human Resource Management"));
        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Human Resource Management"));

        seedAllIrregularitiesTable("Daily forex exchange rate is not displayed", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Forex Trading"));
        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Forex Trading"));

        seedAllIrregularitiesTable("Shares transfer made without collecting the a completed share transfer form signed by the heir(s) of the deceased person not collected.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "A Deceased shareholder (individual)"));
        seedAllIrregularitiesTable("Shares transfer made without collecting the a completed share transfer form signed by the heir(s) of the deceased person not collected.", findAllSubCategoryByNameAndCategoryName("Financial", "A Deceased shareholder (individual)"));

        seedAllIrregularitiesTable("Shares transfer made without collecting the court documents showing the entitlement of the heir(s) of the shares", findAllSubCategoryByNameAndCategoryName("Non-Financial", "A Deceased shareholder (individual)"));
        seedAllIrregularitiesTable("Shares transfer made without collecting the court documents showing the entitlement of the heir(s) of the shares", findAllSubCategoryByNameAndCategoryName("Financial", "A Deceased shareholder (individual)"));

        seedAllIrregularitiesTable("Shares transfer made without collecting the instruction of the transfer with full particulars of the transferee not collected.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "A Deceased shareholder (individual)"));
        seedAllIrregularitiesTable("Shares transfer made without collecting the instruction of the transfer with full particulars of the transferee not collected.", findAllSubCategoryByNameAndCategoryName("Financial", "A Deceased shareholder (individual)"));

        seedAllIrregularitiesTable("Shares transfer made without collecting the share certificates and any other relevant document", findAllSubCategoryByNameAndCategoryName("Non-Financial", "A Deceased shareholder (individual)"));
        seedAllIrregularitiesTable("Shares transfer made without collecting the share certificates and any other relevant document", findAllSubCategoryByNameAndCategoryName("Financial", "A Deceased shareholder (individual)"));

        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Non-Financial", "A Deceased shareholder (individual)"));
        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Financial", "A Deceased shareholder (individual)"));

        seedAllIrregularitiesTable("Contribution in kind is not valued by professional valuators acceptable to the National Bank of Ethiopia.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Capital Contribution In-kind"));
        seedAllIrregularitiesTable("Contribution in kind is not valued by professional valuators acceptable to the National Bank of Ethiopia.", findAllSubCategoryByNameAndCategoryName("Financial", "Capital Contribution In-kind"));

        seedAllIrregularitiesTable("Contribution in kind is considered for the purposes of fulfilling the minimum required capital of the bank.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Capital Contribution In-kind"));
        seedAllIrregularitiesTable("Contribution in kind is considered for the purposes of fulfilling the minimum required capital of the bank.", findAllSubCategoryByNameAndCategoryName("Financial", "Capital Contribution In-kind"));

        seedAllIrregularitiesTable("Contributions in kind exceed 25% of paid-up capital more than minimum required capital.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Capital Contribution In-kind"));
        seedAllIrregularitiesTable("Contributions in kind exceed 25% of paid-up capital more than minimum required capital.", findAllSubCategoryByNameAndCategoryName("Financial", "Capital Contribution In-kind"));

        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Capital Contribution In-kind"));
        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Financial", "Capital Contribution In-kind"));

        seedAllIrregularitiesTable("Shareholder didn’t fill the form prepared for payment of dividends.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Dividend Payment"));
        seedAllIrregularitiesTable("Shareholder didn’t fill the form prepared for payment of dividends.", findAllSubCategoryByNameAndCategoryName("Financial", "Dividend Payment"));

        seedAllIrregularitiesTable("Verification and identification of shareholders is not conducted during divided payment to shareholders", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Dividend Payment"));
        seedAllIrregularitiesTable("Verification and identification of shareholders is not conducted during divided payment to shareholders", findAllSubCategoryByNameAndCategoryName("Financial", "Dividend Payment"));

        seedAllIrregularitiesTable("The dividend payable account doesn't seem to be balanced with the updated unpaid list.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Dividend Payment"));
        seedAllIrregularitiesTable("The dividend payable account doesn't seem to be balanced with the updated unpaid list.", findAllSubCategoryByNameAndCategoryName("Financial", "Dividend Payment"));

        seedAllIrregularitiesTable("Date of payment and amount paid is not considered while the bank's dividend is allocated.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Dividend Payment"));
        seedAllIrregularitiesTable("Date of payment and amount paid is not considered while the bank's dividend is allocated.", findAllSubCategoryByNameAndCategoryName("Financial", "Dividend Payment"));

        seedAllIrregularitiesTable("Payment of dividend is affected before deduction of taxes, the legal reserve, other reserves, provisions, previous losses, and other approved deductions after audit by External Auditors and final approval by the Annual general meeting", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Dividend Payment"));
        seedAllIrregularitiesTable("Payment of dividend is affected before deduction of taxes, the legal reserve, other reserves, provisions, previous losses, and other approved deductions after audit by External Auditors and final approval by the Annual general meeting", findAllSubCategoryByNameAndCategoryName("Financial", "Dividend Payment"));

        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Dividend Payment"));
        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Financial", "Dividend Payment"));

        seedAllIrregularitiesTable("New share have been issued without collecting previously issued shares fully.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Floating of New Shares"));
        seedAllIrregularitiesTable("New share have been issued without collecting previously issued shares fully.", findAllSubCategoryByNameAndCategoryName("Financial", "Floating of New Shares"));

        seedAllIrregularitiesTable("Proposal is not approved by the general assembly for issuance of new share.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Floating of New Shares"));
        seedAllIrregularitiesTable("Proposal is not approved by the general assembly for issuance of new share.", findAllSubCategoryByNameAndCategoryName("Financial", "Floating of New Shares"));

        seedAllIrregularitiesTable("Issued shares are not offered for subscriptions first to the shareholders of the bank and or as per the decision of the shareholders assembly.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Floating of New Shares"));
        seedAllIrregularitiesTable("Issued shares are not offered for subscriptions first to the shareholders of the bank and or as per the decision of the shareholders assembly.", findAllSubCategoryByNameAndCategoryName("Financial", "Floating of New Shares"));

        seedAllIrregularitiesTable("Issued shares are not offered to other existing share holder as per the decision of the general assembly if the privileged shareholders fail to use the opportunity within a given period.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Floating of New Shares"));
        seedAllIrregularitiesTable("Issued shares are not offered to other existing share holder as per the decision of the general assembly if the privileged shareholders fail to use the opportunity within a given period.", findAllSubCategoryByNameAndCategoryName("Financial", "Floating of New Shares"));

        seedAllIrregularitiesTable("The remainder shares are not managed based on additional share request made by the shareholder and allotted based on their shareholdings.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Floating of New Shares"));
        seedAllIrregularitiesTable("The remainder shares are not managed based on additional share request made by the shareholder and allotted based on their shareholdings.", findAllSubCategoryByNameAndCategoryName("Financial", "Floating of New Shares"));

        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Floating of New Shares"));
        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Financial", "Floating of New Shares"));

        seedAllIrregularitiesTable("Representative is not appointed for jointly acquired share.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Joint shareholding"));
        seedAllIrregularitiesTable("Representative is not appointed for jointly acquired share.", findAllSubCategoryByNameAndCategoryName("Financial", "Joint shareholding"));

        seedAllIrregularitiesTable("The right in the share have been granted to a person other than the survivor(s) and the heir(s) of the deceased", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Joint shareholding"));
        seedAllIrregularitiesTable("The right in the share have been granted to a person other than the survivor(s) and the heir(s) of the deceased", findAllSubCategoryByNameAndCategoryName("Financial", "Joint shareholding"));

        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Joint shareholding"));
        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Financial", "Joint shareholding"));

        seedAllIrregularitiesTable("Influential shareholder of other bank acquires the bank's shares.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Limitation on Share Acquisition"));
        seedAllIrregularitiesTable("Influential shareholder of other bank acquires the bank's shares.", findAllSubCategoryByNameAndCategoryName("Financial", "Limitation on Share Acquisition"));

        seedAllIrregularitiesTable("Minimum and the maximum shareholding limits is not maintained", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Limitation on Share Acquisition"));
        seedAllIrregularitiesTable("Minimum and the maximum shareholding limits is not maintained", findAllSubCategoryByNameAndCategoryName("Financial", "Limitation on Share Acquisition"));

        seedAllIrregularitiesTable("Share was bought using bank loans and advances.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Limitation on Share Acquisition"));
        seedAllIrregularitiesTable("Share was bought using bank loans and advances.", findAllSubCategoryByNameAndCategoryName("Financial", "Limitation on Share Acquisition"));

        seedAllIrregularitiesTable("Shareholder acquire more than 2% of the subscribed capital of the bank without getting approval from NBE.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Limitation on Share Acquisition"));
        seedAllIrregularitiesTable("Shareholder acquire more than 2% of the subscribed capital of the bank without getting approval from NBE.", findAllSubCategoryByNameAndCategoryName("Financial", "Limitation on Share Acquisition"));

        seedAllIrregularitiesTable("Shares is sold for foreign nationals or organizations fully or partially owned by foreign nationals.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Limitation on Share Acquisition"));
        seedAllIrregularitiesTable("Shares is sold for foreign nationals or organizations fully or partially owned by foreign nationals.", findAllSubCategoryByNameAndCategoryName("Financial", "Limitation on Share Acquisition"));

        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Limitation on Share Acquisition"));
        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Financial", "Limitation on Share Acquisition"));

        seedAllIrregularitiesTable("Loss of share certificate is not reported to the bank immediately.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Loss of share certificates"));
        seedAllIrregularitiesTable("Loss of share certificate is not reported to the bank immediately.", findAllSubCategoryByNameAndCategoryName("Financial", "Loss of share certificates"));

        seedAllIrregularitiesTable("Duplicate share certificate is issued without publishing a notice in a widely circulated newspaper and submit the newspaper to the bank.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Loss of share certificates"));
        seedAllIrregularitiesTable("Duplicate share certificate is issued without publishing a notice in a widely circulated newspaper and submit the newspaper to the bank.", findAllSubCategoryByNameAndCategoryName("Financial", "Loss of share certificates"));

        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Loss of share certificates"));
        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Financial", "Loss of share certificates"));

        seedAllIrregularitiesTable("Proxy is not deposited with Head office of the Bank before 3(three) full days before the date of a meeting.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Period for Deposit of proxy"));
        seedAllIrregularitiesTable("Proxy is not deposited with Head office of the Bank before 3(three) full days before the date of a meeting.", findAllSubCategoryByNameAndCategoryName("Financial", "Period for Deposit of proxy"));

        seedAllIrregularitiesTable("Forms of proxy, the place where and the time within which they are deposited are not mentioned in the call notice of a meeting of shareholders.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Period for Deposit of proxy"));
        seedAllIrregularitiesTable("Forms of proxy, the place where and the time within which they are deposited are not mentioned in the call notice of a meeting of shareholders.", findAllSubCategoryByNameAndCategoryName("Financial", "Period for Deposit of proxy"));

        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Period for Deposit of proxy"));
        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Financial", "Period for Deposit of proxy"));

        seedAllIrregularitiesTable("Public notice isn’t served for the general information of the members to collect share certificates", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Preparation of Share Certificate"));
        seedAllIrregularitiesTable("Public notice isn’t served for the general information of the members to collect share certificates", findAllSubCategoryByNameAndCategoryName("Financial", "Preparation of Share Certificate"));

        seedAllIrregularitiesTable("Share certificate isn’t timely issued to shareholders", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Preparation of Share Certificate"));
        seedAllIrregularitiesTable("Share certificate isn’t timely issued to shareholders", findAllSubCategoryByNameAndCategoryName("Financial", "Preparation of Share Certificate"));

        seedAllIrregularitiesTable("Share certificate register is not maintained in both hard and soft copy where void, delivered and non-delivered share certificates fully registered.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Preparation of Share Certificate"));
        seedAllIrregularitiesTable("Share certificate register is not maintained in both hard and soft copy where void, delivered and non-delivered share certificates fully registered.", findAllSubCategoryByNameAndCategoryName("Financial", "Preparation of Share Certificate"));

        seedAllIrregularitiesTable("Share-certificate is not prepared after full value of the subscribed shares deposits made.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Preparation of Share Certificate"));
        seedAllIrregularitiesTable("Share-certificate is not prepared after full value of the subscribed shares deposits made.", findAllSubCategoryByNameAndCategoryName("Financial", "Preparation of Share Certificate"));

        seedAllIrregularitiesTable("The share certificate is incomplete as it is missing one or more important details such as the name of the shareholders, ID, certificate number, serial number, and the amount of shares.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Preparation of Share Certificate"));
        seedAllIrregularitiesTable("The share certificate is incomplete as it is missing one or more important details such as the name of the shareholders, ID, certificate number, serial number, and the amount of shares.", findAllSubCategoryByNameAndCategoryName("Financial", "Preparation of Share Certificate"));

        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Preparation of Share Certificate"));
        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Financial", "Preparation of Share Certificate"));

        seedAllIrregularitiesTable("Directors of the Bank didn’t deposit a qualification share with the bank as security for the proper and correct fulfilment of their duties and functions.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Qualification shares"));
        seedAllIrregularitiesTable("Directors of the Bank didn’t deposit a qualification share with the bank as security for the proper and correct fulfilment of their duties and functions.", findAllSubCategoryByNameAndCategoryName("Financial", "Qualification shares"));

        seedAllIrregularitiesTable("Qualification shares handed back to owners while they are on duty.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Qualification shares"));
        seedAllIrregularitiesTable("Qualification shares handed back to owners while they are on duty.", findAllSubCategoryByNameAndCategoryName("Financial", "Qualification shares"));

        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Qualification shares"));
        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Financial", "Qualification shares"));

        seedAllIrregularitiesTable("The transferor is not registered share both on the share system and register book before initiation of share transfers.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Share and Subscription Right Transfer"));
        seedAllIrregularitiesTable("The transferor is not registered share both on the share system and register book before initiation of share transfers.", findAllSubCategoryByNameAndCategoryName("Financial", "Share and Subscription Right Transfer"));

        seedAllIrregularitiesTable("Transfer has made the user influential.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Share and Subscription Right Transfer"));
        seedAllIrregularitiesTable("Transfer has made the user influential.", findAllSubCategoryByNameAndCategoryName("Financial", "Share and Subscription Right Transfer"));

        seedAllIrregularitiesTable("Proper formats of share and subscription right transfer dedicated for this operation are not used and signed by both parties.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Share and Subscription Right Transfer"));
        seedAllIrregularitiesTable("Proper formats of share and subscription right transfer dedicated for this operation are not used and signed by both parties.", findAllSubCategoryByNameAndCategoryName("Financial", "Share and Subscription Right Transfer"));

        seedAllIrregularitiesTable("Proper KYC document id not collected.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Share and Subscription Right Transfer"));
        seedAllIrregularitiesTable("Proper KYC document id not collected.", findAllSubCategoryByNameAndCategoryName("Financial", "Share and Subscription Right Transfer"));

        seedAllIrregularitiesTable("The share certificate which consists of the transferred shares is not collected and bear 'Void' Across the face of the certificates.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Share and Subscription Right Transfer"));
        seedAllIrregularitiesTable("The share certificate which consists of the transferred shares is not collected and bear 'Void' Across the face of the certificates.", findAllSubCategoryByNameAndCategoryName("Financial", "Share and Subscription Right Transfer"));

        seedAllIrregularitiesTable("Revenue stamps are not affixed on the agreement at the cost of the transferee/buyer.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Share and Subscription Right Transfer"));
        seedAllIrregularitiesTable("Revenue stamps are not affixed on the agreement at the cost of the transferee/buyer.", findAllSubCategoryByNameAndCategoryName("Financial", "Share and Subscription Right Transfer"));

        seedAllIrregularitiesTable("Proper approval is not sought from the finance director for the transfer,", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Share and Subscription Right Transfer"));
        seedAllIrregularitiesTable("Proper approval is not sought from the finance director for the transfer,", findAllSubCategoryByNameAndCategoryName("Financial", "Share and Subscription Right Transfer"));

        seedAllIrregularitiesTable("Capital gain tax is not collected.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Share and Subscription Right Transfer"));
        seedAllIrregularitiesTable("Capital gain tax is not collected.", findAllSubCategoryByNameAndCategoryName("Financial", "Share and Subscription Right Transfer"));

        seedAllIrregularitiesTable("Fees are not collected as per the 'Terms and tariff' Of the bank.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Share and Subscription Right Transfer"));
        seedAllIrregularitiesTable("Fees are not collected as per the 'Terms and tariff' Of the bank.", findAllSubCategoryByNameAndCategoryName("Financial", "Share and Subscription Right Transfer"));

        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Share and Subscription Right Transfer"));
        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Financial", "Share and Subscription Right Transfer"));

        seedAllIrregularitiesTable("Custodian acts on behalf of the minor after the minor reaches the age of majority.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Share for Minors"));
        seedAllIrregularitiesTable("Custodian acts on behalf of the minor after the minor reaches the age of majority.", findAllSubCategoryByNameAndCategoryName("Financial", "Share for Minors"));

        seedAllIrregularitiesTable("The minor is not named in the share certificate", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Share for Minors"));
        seedAllIrregularitiesTable("The minor is not named in the share certificate", findAllSubCategoryByNameAndCategoryName("Financial", "Share for Minors"));

        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Share for Minors"));
        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Financial", "Share for Minors"));

        seedAllIrregularitiesTable("Register of shareholders maintained by the bank didn't contain the name and address of shareholders, the number of shares, the amount paid up and the date of entry of shareholder in the register.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Share Registration"));
        seedAllIrregularitiesTable("Register of shareholders maintained by the bank didn't contain the name and address of shareholders, the number of shares, the amount paid up and the date of entry of shareholder in the register.", findAllSubCategoryByNameAndCategoryName("Financial", "Share Registration"));

        seedAllIrregularitiesTable("Shares register is not open to the public without charge at the head office of the bank.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Share Registration"));
        seedAllIrregularitiesTable("Shares register is not open to the public without charge at the head office of the bank.", findAllSubCategoryByNameAndCategoryName("Financial", "Share Registration"));

        seedAllIrregularitiesTable("Re registration of shares is not done in line with the requirement of the commercial code.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Share Registration"));
        seedAllIrregularitiesTable("Re registration of shares is not done in line with the requirement of the commercial code.", findAllSubCategoryByNameAndCategoryName("Financial", "Share Registration"));

        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Share Registration"));
        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Financial", "Share Registration"));

        seedAllIrregularitiesTable("Confirm signed forms are not collected from the shareholder.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Share Subscription"));
        seedAllIrregularitiesTable("Confirm signed forms are not collected from the shareholder.", findAllSubCategoryByNameAndCategoryName("Financial", "Share Subscription"));

        seedAllIrregularitiesTable("Share transfer process is not conducted as per the relevant laws and regulations.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Share Subscription"));
        seedAllIrregularitiesTable("Share transfer process is not conducted as per the relevant laws and regulations.", findAllSubCategoryByNameAndCategoryName("Financial", "Share Subscription"));

        seedAllIrregularitiesTable("Relevant fees and service charge are not collected up on issuance shares.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Share Subscription"));
        seedAllIrregularitiesTable("Relevant fees and service charge are not collected up on issuance shares.", findAllSubCategoryByNameAndCategoryName("Financial", "Share Subscription"));

        seedAllIrregularitiesTable("Approved dividends are not properly allocated to the shareholder.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Share Subscription"));
        seedAllIrregularitiesTable("Approved dividends are not properly allocated to the shareholder.", findAllSubCategoryByNameAndCategoryName("Financial", "Share Subscription"));

        seedAllIrregularitiesTable("At least 25% of share subscriptions is not paid to fulfill the legal requirement as per the commercial code of ethiopia.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Share Subscription"));
        seedAllIrregularitiesTable("At least 25% of share subscriptions is not paid to fulfill the legal requirement as per the commercial code of ethiopia.", findAllSubCategoryByNameAndCategoryName("Financial", "Share Subscription"));

        seedAllIrregularitiesTable("Branches didn't credit the amount collected to share sold payable account", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Share Subscription"));
        seedAllIrregularitiesTable("Branches didn't credit the amount collected to share sold payable account", findAllSubCategoryByNameAndCategoryName("Financial", "Share Subscription"));

        seedAllIrregularitiesTable("Relevant KYC documents are not collected and documented accordingly.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Share Subscription"));
        seedAllIrregularitiesTable("Relevant KYC documents are not collected and documented accordingly.", findAllSubCategoryByNameAndCategoryName("Financial", "Share Subscription"));

        seedAllIrregularitiesTable("All relevant data of the shareholder is not inserted into the share management system.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Share Subscription"));
        seedAllIrregularitiesTable("All relevant data of the shareholder is not inserted into the share management system.", findAllSubCategoryByNameAndCategoryName("Financial", "Share Subscription"));

        seedAllIrregularitiesTable("The transaction is not executed correctly.", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Share Subscription"));
        seedAllIrregularitiesTable("The transaction is not executed correctly.", findAllSubCategoryByNameAndCategoryName("Financial", "Share Subscription"));

        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Share Subscription"));
        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Financial", "Share Subscription"));

        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Non-Financial", "Other"));
        seedAllIrregularitiesTable("Other", findAllSubCategoryByNameAndCategoryName("Financial", "Other"));


    }

    // First, define a method to find a subModule by its name
    private SubModule findSubModuleByCode(String code) {
        return this.subModules.stream()
                .filter(subModule -> subModule.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

    private AllCategory findAllCategoryByName(String name) {
        return this.allCategories.stream()
                .filter(category -> category.getName().trim().equalsIgnoreCase(name.trim()))
                .findFirst()
                .orElse(null);
    }

    private AllSubCategory findAllSubCategoryByNameAndCategoryName(String subCategoryName, String categoryName) {
        return this.allSubCategories.stream()
                .filter(subCategory -> subCategory.getName().equals(subCategoryName)
                        && subCategory.getAllcategory().getName().equals(categoryName))
                .findFirst()
                .orElse(null);
    }


    @Transactional
    public void seedChequeTypesTable(String name) {
        String sql = "SELECT name FROM cheque_types CT WHERE CT.name = ? LIMIT 1";
        List<ChequeType> ct = jdbcTemplate.query(sql, new Object[]{name}, (resultSet, rowNum) -> null);
        if (ct == null || ct.size() == 0) {
            ChequeType allType = new ChequeType(name);
            chequeTypeRepository.save(allType);
        } else {
            //logger.info("Users Seeding Not Required");
        }
    }

    @Transactional
    public void seedCasesStatusTable(String name) {
        String sql = "SELECT name FROM cases_status CS WHERE CS.name = ? LIMIT 1";
        List<CaseStatus> cs = jdbcTemplate.query(sql, new Object[]{name}, (resultSet, rowNum) -> null);
        if (cs == null || cs.size() == 0) {
            CaseStatus caseStatus = new CaseStatus(name);
            caseStatusRepository.save(caseStatus);
        } else {
            //logger.info("Users Seeding Not Required");
        }
    }

    @Transactional
    public void seedFraudTypesTable(String name) {
        String sql = "SELECT name FROM fraud_types FT WHERE FT.name = ? LIMIT 1";
        List<FraudType> ft = jdbcTemplate.query(sql, new Object[]{name}, (resultSet, rowNum) -> null);
        if (ft == null || ft.size() == 0) {
            FraudType fraudType = new FraudType(name);
            fraudTypeRepository.save(fraudType);
        } else {
            //logger.info("Users Seeding Not Required");
        }
    }

    @Transactional
    public void seedSuspectedFraudsterProfessionsTable(String name) {
        String sql = "SELECT name FROM suspected_fraudster_professions SFP WHERE SFP.name = ? LIMIT 1";
        List<SuspectedFraudsterProfession> sfp = jdbcTemplate.query(sql, new Object[]{name}, (resultSet, rowNum) -> null);
        if (sfp == null || sfp.size() == 0) {
            SuspectedFraudsterProfession allOther = new SuspectedFraudsterProfession(name);
            suspectedFraudsterProfessionRepository.save(allOther);
        } else {
            //logger.info("Users Seeding Not Required");
        }
    }

    @Transactional
    public void seedActionsTakenTable(String name) {
        String sql = "SELECT name FROM actions_taken A WHERE A.name = ? LIMIT 1";
        List<ActionTaken> a = jdbcTemplate.query(sql, new Object[]{name}, (resultSet, rowNum) -> null);
        if (a == null || a.size() == 0) {
            ActionTaken actionTaken = new ActionTaken(name);
            actionTakenRepository.save(actionTaken);
        } else {
            //logger.info("Users Seeding Not Required");
        }
    }


    @Transactional
    public void seedCollateralType(String name) {
        String sql = "SELECT name FROM collateral_types CT WHERE CT.name = ? LIMIT 1";
        List<CollateralType> ct = jdbcTemplate.query(sql, new Object[]{name}, (resultSet, rowNum) -> null);
        if (ct == null || ct.size() == 0) {
            CollateralType collateralType = new CollateralType();
            collateralType.setName(name);
            collateralTypeRepository.save(collateralType);
            // logger.info("SubProcess Seeded");
        } else {
            //  logger.info("SubProcess Seeding Not Required");
        }
    }

    @Transactional
    public void seedProductType(String name) {
        String sql = "SELECT name FROM product_types PT WHERE PT.name = ? LIMIT 1";
        List<ProductType> pt = jdbcTemplate.query(sql, new Object[]{name}, (resultSet, rowNum) -> null);
        if (pt == null || pt.size() == 0) {
            ProductType productType = new ProductType();
            productType.setName(name);
            productTypeRepository.save(productType);
        } else {
            //  logger.info("SubProcess Seeding Not Required");
        }
    }
    @Transactional
    public void seedStatus(String name) {
        String sql = "SELECT name FROM statuses s WHERE s.name = ? LIMIT 1";
        List<Status> s = jdbcTemplate.query(sql, new Object[]{name}, (resultSet, rowNum) -> null);
        if (s == null || s.size() == 0) {
            Status status = new Status();
            status.setName(name);
            statusRepository.save(status);
            // logger.info("SubProcess Seeded");
        } else {
            //  logger.info("SubProcess Seeding Not Required");
        }
    }


    @Transactional
    public void seedInsuranceCoverageType(String name) {
        String sql = "SELECT name FROM insurance_coverage_types ICT WHERE ICT.name = ? LIMIT 1";
        List<InsuranceCoverageType> ict = jdbcTemplate.query(sql, new Object[]{name}, (resultSet, rowNum) -> null);
        if (ict == null || ict.size() == 0) {
            InsuranceCoverageType insuranceCoverageType = new InsuranceCoverageType();
            insuranceCoverageType.setName(name);
            insuranceCoverageTypeRepository.save(insuranceCoverageType);
            // logger.info("SubProcess Seeded");
        } else {
            //  logger.info("SubProcess Seeding Not Required");
        }
    }

    @Transactional
    public void seedSubModulesTable(String code, String name) {
        String sql = "SELECT name FROM sub_modules SM WHERE SM.name = ? LIMIT 1";
        List<SubModule> o = jdbcTemplate.query(sql, new Object[]{name}, (resultSet, rowNum) -> null);
        if (o == null || o.size() == 0) {
            SubModule subModule = new SubModule();
            subModule.setCode(code);
            subModule.setName(name);
            subModuleRepository.save(subModule);
            // logger.info("SubProcess Seeded");
        } else {
            //  logger.info("SubProcess Seeding Not Required");
        }
    }

    @Transactional
    public void seedAllCategoriesTable(String name, SubModule subModule) {
        String sql = "SELECT name FROM all_categories AC WHERE AC.name = ? and AC.sub_module_id = ? LIMIT 1";
        List<AllCategory> ac = jdbcTemplate.query(sql, new Object[]{name, subModule.getId()}, (resultSet, rowNum) -> null);
//        List<AllCategory> ac = allCategoryService.findAllAllCategory();
        if (ac == null || ac.size() == 0) {
            AllCategory allCategory = new AllCategory(name, subModule);
            allCategoryRepository.save(allCategory);
            // logger.info("SubProcess Seeded");
        } else {
            //  logger.info("SubProcess Seeding Not Required");
        }
    }

    @Transactional
    public void seedAllSubCategoriesTable(String name, AllCategory allCategory) {
        String sql = "SELECT name FROM all_sub_categories WHERE name = ? AND all_category_id = ? LIMIT 1";
        List<AllSubCategory> asc = jdbcTemplate.query(sql, new Object[]{name, allCategory.getId()}, (resultSet, rowNum) -> null);
        if (asc == null || asc.size() == 0) {
            AllSubCategory allSubCategory = new AllSubCategory();
            allSubCategory.setName(name);
            allSubCategory.setAllcategory(allCategory);
            ;
            allSubCategoryRepository.save(allSubCategory);
            // logger.info("SubProcess Seeded");
        } else {
            //  logger.info("SubProcess Seeding Not Required");
        }
    }

    @Transactional
    public void seedAllIrregularitiesTable(String name, AllSubCategory allSubCategory) {
//        System.out.println("seedAllIrregularitiesTable seeding");
        AllCategory allCategory = allSubCategory.getAllcategory();

        String sql = "SELECT AI.name FROM all_irregularities AI JOIN all_sub_categories ASC2 ON AI.all_sub_category_id = ASC2.id WHERE AI.name = ? AND ASC2.all_category_id = ? AND ASC2.name = ? LIMIT 1";
        List<AllSubCategory> asc = jdbcTemplate.query(sql, new Object[]{name, allCategory.getId(), allSubCategory.getName()}, (resultSet, rowNum) -> null);

        if (asc == null || asc.size() == 0) {
            AllIrregularity allIrregularity = new AllIrregularity();
            allIrregularity.setName(name);
            allIrregularity.setAllSubCategory(allSubCategory);
            allIrregularityRepository.save(allIrregularity);
            // logger.info("SubProcess Seeded");
        } else {
            //  logger.info("SubProcess Seeding Not Required");
        }
    }

    @Transactional
    public void seedActivitiesStatusTable(String name) {
        String sql = "SELECT name FROM activities_status activityS WHERE activityS.name = ? LIMIT 1";
        List<ActivityStatus> as = jdbcTemplate.query(sql, new Object[]{name}, (resultSet, rowNum) -> null);
        if (as == null || as.size() == 0) {
            ActivityStatus activityStatus = new ActivityStatus(name);
            activityStatusRepository.save(activityStatus);
        } else {
            //logger.info("Activities Status Seeding Not Required");
        }
    }

    @Transactional
    public void seedFinanciesStatusTable(String name) {
        String sql = "SELECT name FROM financies_status financeS WHERE financeS.name = ? LIMIT 1";
        List<FinanceStatus> as = jdbcTemplate.query(sql, new Object[]{name}, (resultSet, rowNum) -> null);
        if (as == null || as.size() == 0) {
            FinanceStatus financeStatus = new FinanceStatus(name);
            financeStatusRepository.save(financeStatus);
        } else {
            //logger.info("Activities Status Seeding Not Required");
        }
    }
    @Transactional
    public void seedShareStatusTable(String name) {
        String sql = "SELECT name FROM sharemodule_status shareS WHERE shareS.name = ? LIMIT 1";
        List<ShareStatus> as = jdbcTemplate.query(sql, new Object[]{name}, (resultSet, rowNum) -> new ShareStatus(resultSet.getString("name")));
        if (as.isEmpty()) {
            ShareStatus shareStatus = new ShareStatus(name);
            shareStatusRepository.save(shareStatus);
        } else {
            //logger.info("Activities Status Seeding Not Required");
        }
    }


}


