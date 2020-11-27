package aos.projet.HumanRessourceBo.controller;

import java.util.List;
import java.util.Optional;

import aos.projet.HumanRessourceBo.exception.NotFoundException;
import aos.projet.HumanRessourceBo.model.Address;
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

import aos.projet.HumanRessourceBo.model.BankDetails;
import aos.projet.HumanRessourceBo.repository.BankDetailsRepository;


@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
@RestController
public class BankDetailsRestAPIs {
	@Autowired
    private BankDetailsRepository bankDetailsRepository;
	@PostMapping("/bankDetails/add")
	@PreAuthorize("hasRole('ADMIN')")
	public BankDetails saveBankDetails(@RequestBody BankDetails bankDetails) {
		return bankDetailsRepository.save(bankDetails);
	}
	
	@DeleteMapping("/bankDetails/{idBankDetails}")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteBankDetails(@PathVariable Long idbankDetails) {
		bankDetailsRepository.deleteById(idbankDetails);
	}
	
	@PutMapping("/bankDetails/{idBankDetails}")
	@PreAuthorize("hasRole('ADMIN')")
	public BankDetails updateBankDetails(@PathVariable Long idBankDetails,@RequestBody BankDetails bankDetails) {
		return bankDetailsRepository.findById(idBankDetails).map(
		        bank -> {
		        	if(bankDetails.getBICBankDetails()!=null)
		        		bank.setBICBankDetails(bankDetails.getBICBankDetails());
		            if(bankDetails.getCodeBankDetails()!=null)
		            	bank.setCodeBankDetails(bankDetails.getCodeBankDetails());
		            if(bankDetails.getIBANBankDetails() != null)
		            	bank.setIBANBankDetails(bankDetails.getIBANBankDetails());
		            if(bankDetails.getTitleOfAccountBankDetails() != null)
		            	bank.setTitleOfAccountBankDetails(bankDetails.getTitleOfAccountBankDetails());
		            return bankDetailsRepository.save(bank);
                }
        ).orElseThrow(() -> new NotFoundException("BankDetails Id: "+idBankDetails+ " not found"));
	}

	@GetMapping("/bankDetails")
	@PreAuthorize("hasRole('ADMIN')")
	public List<BankDetails> getAddressList(){
		return bankDetailsRepository.findAll();
	}
	

	@GetMapping("/bankDetails/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public BankDetails getBankDetailsById(@PathVariable Long id) throws Exception{
		Optional<BankDetails> bankDetails = bankDetailsRepository.findById(id);
		if (!bankDetails.isPresent())
			throw new Exception("id-" + id);
		return bankDetails.get();
	}
	
	
	
}
