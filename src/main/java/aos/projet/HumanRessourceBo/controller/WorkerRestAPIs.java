package aos.projet.HumanRessourceBo.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import aos.projet.HumanRessourceBo.model.Worker;
import aos.projet.HumanRessourceBo.repository.WorkerRepository;
import aos.projet.HumanRessourceBo.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*",allowedHeaders = "*", maxAge = 3600)
@RequestMapping("/api")
@RestController
public class WorkerRestAPIs {
	
	@Autowired
	private WorkerRepository workerRepository;
	
	@GetMapping("/workers")
	@PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
	public List<Worker> getAllWorkers(){
		return workerRepository.findAll();
	}

	@PostMapping("/workers")
	@PreAuthorize("hasRole('ADMIN')")
	public Worker saveWorker(@RequestBody Worker worker) {
		worker.setNationalityWorker();
		return workerRepository.save(worker);
	}
	
	@GetMapping("/workers/{idPerson}")
	@PreAuthorize("hasRole('ADMIN')")
	public Worker getWorker(@PathVariable Long idPerson) throws Exception{
		Optional<Worker> workers = workerRepository.findById(idPerson);
		if (!workers.isPresent())
			throw new Exception("id-" + idPerson);
		return workers.get();
	}

	@DeleteMapping("/workers/{idPerson}")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteWorker(@PathVariable Long idPerson) {
		workerRepository.deleteById(idPerson);
	}
	
	@PutMapping("/workers/{idPerson}")
	@PreAuthorize("hasRole('ADMIN')")
	public Worker updateWorker(@PathVariable Long idPerson,@RequestBody Worker worker) {
		return workerRepository.findById(idPerson).map(
		        bud -> {
		        	if(worker.getCodePerson()!=null)
		            	bud.setCodePerson(worker.getCodePerson());
		        	if(worker.getUserNamePerson()!=null)
		            	bud.setUserNamePerson(worker.getUserNamePerson());
		        	if(worker.getLastname()!=null)
		            	bud.setLastname(worker.getLastname());
		        	if(worker.getFirstNamePerson()!=null)
		            	bud.setFirstNamePerson(worker.getFirstNamePerson());
		        	if(worker.getBirthDatePerson()!=null)
		            	bud.setBirthDatePerson(worker.getBirthDatePerson());
		        	if(worker.getSexPerson()!=null)
		            	bud.setSexPerson(worker.getSexPerson());
		        	if(worker.getPersonalPhonePortablePerson()!=null)
		            	bud.setPersonalPhonePortablePerson(worker.getPersonalPhonePortablePerson());
		        	if(worker.getPersonalMailPerson()!=null)
		            	bud.setPersonalMailPerson(worker.getPersonalMailPerson());
		        	if(worker.getDepartmentOfBirthWorker()!=null)
		            	bud.setDepartmentOfBirthWorker(worker.getDepartmentOfBirthWorker());
		        	if(worker.getNativeCountryWorker()!=null)
		            	bud.setNativeCountryWorker(worker.getNativeCountryWorker());
		        	if(worker.getPlaceOfBirthWorker()!=null)
		            	bud.setPlaceOfBirthWorker(worker.getPlaceOfBirthWorker());
		        	if(worker.getNationalityWorker()!=null)
		            	bud.setNationalityWorker(worker.getNationalityWorker());
		        	if(worker.getNationalityWorker()!=null)
		            	bud.setNationalityWorker(worker.getNationalityWorker());
		        	if(worker.getSocialSecurityNumberWorker()!=null)
		            	bud.setSocialSecurityNumberWorker(worker.getSocialSecurityNumberWorker());
		        	if(worker.getFamilySituationWorker()!=null)
		            	bud.setFamilySituationWorker(worker.getFamilySituationWorker());
		        	if(worker.getBankDetails()!=null)
		            	bud.setBankDetails(worker.getBankDetails());
		            return workerRepository.save(bud);
                }
        ).orElseThrow(() -> new NotFoundException("Person Id: "+idPerson+ " n'existe pas"));
	}


	@GetMapping("/workers/firstName/{firstName}")
	@PreAuthorize("hasRole('ADMIN')")
	public Collection<Worker> getWorker(@PathVariable String firstName) throws Exception{
		Collection<Worker> workers = workerRepository.findAllByFirstNamePerson(firstName);
		return workers;
	}
}
