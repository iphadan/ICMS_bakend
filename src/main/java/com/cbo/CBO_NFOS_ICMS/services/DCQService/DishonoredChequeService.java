package com.cbo.CBO_NFOS_ICMS.services.DCQService;

import com.cbo.CBO_NFOS_ICMS.exception.UserNotFoundException;
import com.cbo.CBO_NFOS_ICMS.models.UserAndEmployee.OrganizationalUnit;
import com.cbo.CBO_NFOS_ICMS.models.DCQ.DishonoredCheque;
import com.cbo.CBO_NFOS_ICMS.models.UserAndEmployee.SubProcess;
import com.cbo.CBO_NFOS_ICMS.repositories.DCQRepository.DishonoredChequeRepository;
import com.cbo.CBO_NFOS_ICMS.services.UserAndEmployeeService.OrganizationalUnitService;
import com.cbo.CBO_NFOS_ICMS.services.UserAndEmployeeService.SubProcessService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class DishonoredChequeService {
    private final SubProcessService subProcessService;
    private final OrganizationalUnitService organizationalUnitService;
    private final DishonoredChequeRepository dishonoredChequeRepository;

    public DishonoredChequeService(SubProcessService subProcessService, OrganizationalUnitService organizationalUnitService, DishonoredChequeRepository dishonoredChequeRepository) {

        this.subProcessService = subProcessService;
        this.organizationalUnitService = organizationalUnitService;
        this.dishonoredChequeRepository = dishonoredChequeRepository;
    }

    public DishonoredCheque addDishonouredCheque(DishonoredCheque DishonoredCheque) {

        return dishonoredChequeRepository.save(DishonoredCheque);
    }

    public List<DishonoredCheque> findAllDishonouredCheque() {
        return dishonoredChequeRepository.findAll();
    }

    public DishonoredCheque updateDishonouredCheque(DishonoredCheque DishonoredCheque) {
        return dishonoredChequeRepository.save(DishonoredCheque);
    }

    public DishonoredCheque findDishonouredChequeById(Long id) {
        return dishonoredChequeRepository.findDishonouredChequeById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id" + id + " was not found"));
    }
    public int findDishonouredChequeByAccountNumber(String accountNumber) {
        return (dishonoredChequeRepository.findDishonouredChequeByAccountNumber(accountNumber)).size();
    }

    public void deleteDishonouredCheque(Long id) {
        dishonoredChequeRepository.deleteById(id);
    }

    public List<DishonoredCheque> findAllDishonouredChequeInSpecificOrganizationalUnit(Long id) {
        OrganizationalUnit organizationalUnit = organizationalUnitService.findOrganizationalUnitById(id);
        return dishonoredChequeRepository.findDishonouredChequeByOrganizationalUnit(organizationalUnit);
    }

    public List<DishonoredCheque> findAllDishonouredChequeInSpecificSubProcess(Long id) {
        SubProcess subProcess = subProcessService.findSubProcessById(id);
        List<OrganizationalUnit> organizationalUnits = organizationalUnitService.findOrganizationalUnitBySubProcess(subProcess);
        List<DishonoredCheque> dchques = new ArrayList<>();
        for (int i = 0; i < organizationalUnits.size(); i++) {
            List<DishonoredCheque> clmfjhd = dishonoredChequeRepository.findDishonouredChequeByOrganizationalUnit(organizationalUnits.get(i));
            for (int j = 0; j < clmfjhd.size(); j++) {
                dchques.add(dishonoredChequeRepository.findDishonouredChequeByOrganizationalUnit(organizationalUnits.get(i)).get(j));

            }
        }
        return dchques;
    }
    public int findDishonouredChequeCountForYear(int year) {
        List<DishonoredCheque> dishonoredCheques = findAllDishonouredCheque();
        int count = 0;
        for (DishonoredCheque dcq : dishonoredCheques) {
            String datePresented = dcq.getDatePresented();
            SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy");
            try {
                Date date = formatter.parse(datePresented);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                int yearOfCheque = calendar.get(Calendar.YEAR);
                if (yearOfCheque == year) {
                    count++;
                }
            } catch (ParseException e) {
                // handle parse exception
            }
        }
        return count;
    }
    public int countDishonouredChequesThreeTimesInLastWeek() {
        LocalDate startDate = LocalDate.now().minusDays(7);
        LocalDate endDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        int count = 0;
        for (DishonoredCheque dcq : dishonoredChequeRepository.findByFrequencyGreaterThanEqual(3)) {
            LocalDate datePresented = LocalDate.parse(dcq.getDatePresented(), formatter);
            if (datePresented.isAfter(startDate) && datePresented.isBefore(endDate.plusDays(1))) {
                count++;
            }
        }
        return count;
    }

}