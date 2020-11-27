package aos.projet.HumanRessourceBo.repository;

import aos.projet.HumanRessourceBo.model.Address;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface AdressRepository extends JpaRepository<Address,Long>{

}
