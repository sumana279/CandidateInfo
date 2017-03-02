package org.recruitment.portal.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Candidate_Master")
public class CandidateMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long candidateId;
	private String firstName;
	private String lastName;
	private String middleName;
	private String phoneNo;
	private String email;
	private String shortDesc;
	private String visa;
	private String candidateCurrentStatus;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	// @JoinTable(joinColumns = @JoinColumn(name = "CanProj_LinkId"), name =
	// "Candidate_Proj_Link")
	private List<CandidateLink> candidateLinks = new ArrayList<CandidateLink>();
	private long ctsId;
	private Date createDate;
	private Date modifiedDate;
	private final String modifiedBy = "frontEndUser";
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(joinColumns = @JoinColumn(name = "CanAttrResume_Link"), name = "CandResume_Link")
	private List<ResumeHolder> resumes = new ArrayList<ResumeHolder>();

	public CandidateMaster(long candidateId, String firstName, String lastName, String middleName, String phoneNo,
			String email, String shortDesc, String visa, String candidateCurrentStatus,
			List<CandidateLink> candidateLinks, long ctsId, Date createDate, Date modifiedDate) {
		super();
		this.candidateId = candidateId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.phoneNo = phoneNo;
		this.email = email;
		this.shortDesc = shortDesc;
		this.visa = visa;
		this.candidateCurrentStatus = candidateCurrentStatus;
		this.candidateLinks = candidateLinks;
		this.ctsId = ctsId;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
	}

	public CandidateMaster() {

	}

	public List<ResumeHolder> getResumes() {
		return resumes;
	}

	public void setResumes(List<ResumeHolder> resumes) {
		this.resumes = resumes;
	}

	public long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(long candidateId) {
		this.candidateId = candidateId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getVisa() {
		return visa;
	}

	public void setVisa(String visa) {
		this.visa = visa;
	}

	public String getCandidateCurrentStatus() {
		return candidateCurrentStatus;
	}

	public void setCandidateCurrentStatus(String candidateCurrentStatus) {
		this.candidateCurrentStatus = candidateCurrentStatus;
	}

	public List<CandidateLink> getCandidateLinks() {
		return candidateLinks;
	}

	public void setCandidateLinks(List<CandidateLink> candidateLinks) {
		this.candidateLinks = candidateLinks;
	}

	public long getCtsId() {
		return ctsId;
	}

	public void setCtsId(long ctsId) {
		this.ctsId = ctsId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

}
