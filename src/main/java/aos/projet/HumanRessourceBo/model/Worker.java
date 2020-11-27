package aos.projet.HumanRessourceBo.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
@Table(name="Worker")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		resolver = EntityIdResolver.class,
		property = "idPerson",
		scope=Worker.class)
//@PrimaryKeyJoinColumn(name = "idPerson")
public class Worker {
	 private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_person",  nullable = false)
	private Long idPerson;

	@Column(name="code_person")
	private String codePerson;

	@Column(name="user_name_person")
	private String userNamePerson;

	@Column(name="last_name_person")
	private String lastname;

	@Column(name="first_name_person")
	private String firstNamePerson;

	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name="birth_date_person")
	private Date birthDatePerson;

	@Enumerated(EnumType.STRING)
	@Column(name="sex_person",length = 60)
	private SexPerson sexPerson;

	@Column(name="personal_phone_portable_person")
	private String personalPhonePortablePerson;

	@Column(name="personal_mail_person")
	private String personalMailPerson;
	
	@Column(name="department_of_birth_worker")
	private String departmentOfBirthWorker;
	
	@Column(name="native_country_worker")
	private String nativeCountryWorker;
	
	@Column(name="place_of_birth_worker")
	private String placeOfBirthWorker;
	
	@Enumerated(EnumType.STRING)
    @Column(name="nationality_worker",length = 60)
    private Nationality nationalityWorker;
	
	@Column(name="social_security_number_worker")
	private String socialSecurityNumberWorker;
	
	@Enumerated(EnumType.STRING)
    @Column(name="family_situation_worker",length = 60)
    private FamilySituation familySituationWorker;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_person_address")
	@ToString.Exclude
	private Address address;

	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "id_person_bank_details")
	@ToString.Exclude
	private  BankDetails bankDetails;

	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "id_employement_contract")
	@ToString.Exclude
	private EmploymentContract employmentContract;


    public BankDetails getBankDetails() {
        return bankDetails;
    }
    //@JsonSetter
    public void setBankDetails(BankDetails bankDetails) {
        this.bankDetails = bankDetails;
    }

    public void setNationalityWorker() {
        if("Française".equals(this.nationalityWorker))

            this.nationalityWorker = Nationality.Française;
        if("Autre".equals(this.nationalityWorker))
            this.nationalityWorker = Nationality.Autre;

    }

}
