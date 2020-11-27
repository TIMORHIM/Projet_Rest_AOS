package aos.projet.HumanRessourceBo.repository;

import aos.projet.HumanRessourceBo.model.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BankDetailsRepository extends JpaRepository<BankDetails,Long>{

}
