package aos.projet.HumanRessourceBo.model;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
@Table(name="Address")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		resolver = EntityIdResolver.class,
		property = "idAddress",
		scope=Address.class)
public class Address {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_address")
	private Long idAddress;
	@Column(name="code_address")
	private String codeAddress;
	@Column(name="complement_1_address")
	private String complement1Address;
	@Column(name="complement_2_address")
	private String complement2Address;
	@Column(name="country_address")
	private String countryAddress;
	@Column(name="postal_code_address")
	private Integer postalCodeAddress;
	@Column(name="city_address")
	private String cityAddress;


	@OneToOne(cascade = CascadeType.ALL,mappedBy="address", fetch=FetchType.LAZY)
	@JsonIdentityReference(alwaysAsId = true)
	@ToString.Exclude
	private Worker worker;

	
	

}
