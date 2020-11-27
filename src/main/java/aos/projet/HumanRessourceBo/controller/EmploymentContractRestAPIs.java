package aos.projet.HumanRessourceBo.controller;

import aos.projet.HumanRessourceBo.exception.NotFoundException;
import aos.projet.HumanRessourceBo.model.EmploymentContract;
import aos.projet.HumanRessourceBo.repository.EmploymentContractRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
@RestController
public class EmploymentContractRestAPIs {
    @Autowired
    private EmploymentContractRepository employmentContractRepository;


    @PostMapping("/employmentContract")
    @PreAuthorize("hasRole('ADMIN')")
    public EmploymentContract saveEmploymentContract(@RequestBody EmploymentContract employmentContract) {

        return employmentContractRepository.save(employmentContract);
    }


    @GetMapping("/employmentContract/getByWorker/{idWorker}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<EmploymentContract> getEmploymentContractByPerson(@PathVariable Long idWorker) throws Exception{
        return employmentContractRepository.findByWorkerIdPerson(idWorker);

    }

    @GetMapping("/employmentContract/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<EmploymentContract> getEmploymentContractById(@PathVariable Long id) throws Exception{
        return employmentContractRepository.findById(id);

    }

    @GetMapping("/employmentContract/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    public List<EmploymentContract> getAllEmploymentContract() throws Exception{
        return employmentContractRepository.findAll();

    }


    @DeleteMapping("/employmentContract/deleteEveryState/{idEmploymentContract}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('ROLE_PM')")
    public void deleteAdministrativeDocuments(@PathVariable Long idEmploymentContract) {

            employmentContractRepository.deleteById(idEmploymentContract);



    }

    @PutMapping("/employmentContract/{idContrat}")
    @PreAuthorize("hasRole('ADMIN')")
    public EmploymentContract updateChildren(@PathVariable Long idContrat,@RequestBody EmploymentContract employmentContract) {
        return employmentContractRepository.findById(idContrat).map(
                contract -> {
                    contract.setCode_employment_contract(employmentContract.getCode_employment_contract());
                    contract.setComplementaryPointsEmploymentContract(employmentContract.getComplementaryPointsEmploymentContract());
                    contract.setEndDateEmploymentContract(employmentContract.getEndDateEmploymentContract());
                    contract.setNoteEmploymentContract(employmentContract.getNoteEmploymentContract());
                    contract.setPointBase(employmentContract.getPointBase());
                    contract.setPointsSeniorityEmploymentContract(employmentContract.getPointsSeniorityEmploymentContract());
                    contract.setStartDateEmploymentContract(employmentContract.getStartDateEmploymentContract());
                    contract.setStateEmploymentContract(employmentContract.getStateEmploymentContract());
                    contract.setStatusEmploymentContract(employmentContract.getStatusEmploymentContract());
                    contract.setTypeEmploymentContract(employmentContract.getTypeEmploymentContract());
                    contract.setWorkTimeEmploymentContract(employmentContract.getWorkTimeEmploymentContract());
                    contract.setFormContract(employmentContract.getFormContract());
                    contract.setContractAnnexation(employmentContract.getContractAnnexation());
                    return employmentContractRepository.save(contract);
                }
        ).orElseThrow(() -> new NotFoundException("Contract Id: "+idContrat+ " not found"));
    }


}
