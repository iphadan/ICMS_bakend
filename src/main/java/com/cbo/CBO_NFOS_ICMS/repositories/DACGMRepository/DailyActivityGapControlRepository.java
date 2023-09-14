package com.cbo.CBO_NFOS_ICMS.repositories.DACGMRepository;

import com.cbo.CBO_NFOS_ICMS.models.UserAndEmployee.OrganizationalUnit;
import com.cbo.CBO_NFOS_ICMS.models.DACGM.DailyActivityGapControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DailyActivityGapControlRepository extends JpaRepository<DailyActivityGapControl, Long> {
    void deleteDACGMById(Long id);
//    @Query("SELECT dc FROM daily_activities_gap_control dc WHERE dc.accountNumber = ?1")
//    List<DailyActivityGapControl> findDACGMByAccountNumber(String accountNumber);

    Optional<DailyActivityGapControl> findDACGMById(Long id);

    //List<CollateralInsurancePolicy> findCollateralInsurancePolicyBySubProcess(SubProcess subProcess);

    List<DailyActivityGapControl> findDACGMByOrganizationalUnit(OrganizationalUnit organizationalUnit);

//    @Query("SELECT dc FROM daily_activities_gap_control dc JOIN Organizational_unit b ON dc.organizational_unit.id = b.id JOIN sub_process d ON b.sub_process.id = d.id WHERE d.id = :id")
//    List<DailyActivityGapControl> findDACGMBySubProcessId(Long id);
}