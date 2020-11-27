package aos.projet.HumanRessourceBo.controller;

import java.util.List;
import java.util.Optional;

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
import aos.projet.HumanRessourceBo.model.Address;
import aos.projet.HumanRessourceBo.repository.AdressRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
@RestController
public class AddressRestAPIs {
	@Autowired
    private AdressRepository addressRepository;
	
	@PostMapping("/address")
	@PreAuthorize("hasRole('ADMIN')")
	public Address saveAddress(@RequestBody Address address) {
		return addressRepository.save(address);
	}
	
	@DeleteMapping("/address/{idAddress}")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteAddress(@PathVariable Long idAddress) {
		addressRepository.deleteById(idAddress);
	}
	
	@PutMapping("/address/{idAddress}")
	@PreAuthorize("hasRole('ADMIN')")
	public Address updateAddress(@PathVariable Long idAddress,@RequestBody Address address) {
		return addressRepository.findById(idAddress).map(
		        adr -> {
		        	if(address.getCityAddress()!=null)
		            	adr.setCityAddress(address.getCityAddress());
		            if(address.getCodeAddress()!=null)
		            	adr.setCodeAddress(address.getCodeAddress());
		            if(address.getComplement1Address() != null)
						adr.setComplement1Address(address.getComplement1Address());
		            if(address.getComplement2Address() != null)
		            	adr.setComplement2Address(address.getComplement2Address());
		            if(address.getCountryAddress()!=null)
		            	adr.setCountryAddress(address.getCountryAddress());
		            if(address.getPostalCodeAddress()!=null)
		            	adr.setPostalCodeAddress(address.getPostalCodeAddress());
		            return addressRepository.save(adr);
                }
        ).orElseThrow(() -> new NotFoundException("Address Id: "+idAddress+ " not found"));
	}

	@GetMapping("/address")
	@PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
	public List<Address> getAddressList(){
		return addressRepository.findAll();
	}

	@GetMapping("/address/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public Address getAddress(@PathVariable Long id) throws Exception{
		Optional<Address> addresses = addressRepository.findById(id);
		if (!addresses.isPresent())
			throw new Exception("id-" + id);
		return addresses.get();
	}
}
