package aos.projet.HumanRessourceBo.repository;

import aos.projet.HumanRessourceBo.model.EmploymentContract;
import aos.projet.HumanRessourceBo.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmploymentContractRepository extends JpaRepository<EmploymentContract, Long> {

     EmploymentContract findEmploymentContractByWorker(Worker worker);
     List<EmploymentContract> findByWorkerIdPerson(Long idWorker);



}
