package aos.projet.HumanRessourceBo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
@Table(name="Bank_Details")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		resolver = EntityIdResolver.class,
		property = "idBankDetails",
		scope=BankDetails.class)


public class BankDetails {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_bank_details")
	private Long idBankDetails;
	
	@Column(name="code_bank_details")
	private String codeBankDetails;

	@Column(name="IBAN_bank_details")
	private String IBANBankDetails;
	
	@Column(name="BIC_bank_details")
	private	String BICBankDetails;
	

	@Column(name="title_of_account_bank_details")
	private String titleOfAccountBankDetails;

	@OneToOne(cascade = CascadeType.ALL,mappedBy="bankDetails", fetch=FetchType.LAZY)
	@JsonIdentityReference(alwaysAsId = true)
	@ToString.Exclude
	private Worker worker;

}
