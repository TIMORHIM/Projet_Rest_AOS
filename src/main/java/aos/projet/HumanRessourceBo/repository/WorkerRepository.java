package aos.projet.HumanRessourceBo.repository;

import aos.projet.HumanRessourceBo.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
	Collection<Worker> findAllByFirstNamePerson(String firstName);

}
