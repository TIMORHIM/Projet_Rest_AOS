package aos.projet.HumanRessourceBo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
@Table(name="Employment_Contract")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		resolver = EntityIdResolver.class,
		property = "idEmploymentContract",
		scope=EmploymentContract.class)
public class EmploymentContract {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_employment_contract")
	private Long idEmploymentContract;
	
	@Column(name="code_employment_contract")
	private String code_employment_contract;
    
    @Column(name="state_employment_contract", length = 60)
    private StateEmploymentContract stateEmploymentContract;
    
    @Column(name="status_employment_contract", length = 60)
    private StatusEmploymentContract statusEmploymentContract;
	
	@Column(name="note_employment_contract")
	private String noteEmploymentContract;

	@Enumerated(EnumType.STRING)
	@Column(name="form_employment_contract", length = 60)
	private FormEmploymentContract formContract;

	@OneToOne
	@JsonIdentityReference(alwaysAsId = true)
	@JoinColumn(name="id_contract_annexation")
	private EmploymentContract contractAnnexation;
	
	@Enumerated(EnumType.STRING)
    @Column(name="type_employment_contract", length = 60)
    private TypeEmploymentContract typeEmploymentContract;
	
	@Enumerated(EnumType.STRING)
    @Column(name="work_time_employment_contract", length = 60)
    private WorkTimeEmploymentContract workTimeEmploymentContract;
	
	@Temporal(TemporalType.DATE)
	@Column(name="start_date_employment_contract")
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date startDateEmploymentContract;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name="end_date_employment_contract")
	private Date endDateEmploymentContract;
	
	@Column(name="points_seniority_employment_contract")
	private Integer pointsSeniorityEmploymentContract;
	
	@Column(name="complementary_points_employment_contract")
	private Integer complementaryPointsEmploymentContract;
	@Column(name="base_point")
	private Integer pointBase;

	@OneToOne(cascade = CascadeType.ALL,mappedBy="employmentContract", fetch=FetchType.LAZY)
	@JsonIdentityReference(alwaysAsId = true)
	@ToString.Exclude
	private Worker worker;

}
