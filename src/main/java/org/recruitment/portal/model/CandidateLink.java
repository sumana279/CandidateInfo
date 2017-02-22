package org.recruitment.portal.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CandAttr_Link")
public class CandidateLink {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "candLinkId")
	private long candLinkId;
	private String referrerDeptName;
	private String referrerName;
	private String referrerContact;
	private String referrerEmail;
	private String hiringStatus;
	private long candidateId;
	private Boolean isOnSite;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(joinColumns = @JoinColumn(name = "CanAttrEval_Link"), name = "CandEval_Link")
	private List<EvaluationMaster> evaluationMaster = new ArrayList<EvaluationMaster>();
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(joinColumns = @JoinColumn(name = "CanAttrResume_Link"), name = "CandResume_Link")
	private List<ResumeHolder> resumes = new ArrayList<ResumeHolder>();
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(joinColumns = @JoinColumn(name = "CanAttrProj_Link"), name = "CandProj_Link")
	private List<ProjectMaster> projectId = new ArrayList<ProjectMaster>();

	public long getCandLinkId() {
		return candLinkId;
	}

	public void setCandLinkId(long candLinkId) {
		this.candLinkId = candLinkId;
	}

	public String getReferrerDeptName() {
		return referrerDeptName;
	}

	public void setReferrerDeptName(String referrerDeptName) {
		this.referrerDeptName = referrerDeptName;
	}

	public String getReferrerName() {
		return referrerName;
	}

	public void setReferrerName(String referrerName) {
		this.referrerName = referrerName;
	}

	public String getReferrerContact() {
		return referrerContact;
	}

	public void setReferrerContact(String referrerContact) {
		this.referrerContact = referrerContact;
	}

	public String getReferrerEmail() {
		return referrerEmail;
	}

	public void setReferrerEmail(String referrerEmail) {
		this.referrerEmail = referrerEmail;
	}

	public String getHiringStatus() {
		return hiringStatus;
	}

	public void setHiringStatus(String hiringStatus) {
		this.hiringStatus = hiringStatus;
	}

	public long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(long candidateId) {
		this.candidateId = candidateId;
	}

	public Boolean getIsOnSite() {
		return isOnSite;
	}

	public void setIsOnSite(Boolean isOnSite) {
		this.isOnSite = isOnSite;
	}

	public List<EvaluationMaster> getEvaluationMaster() {
		return evaluationMaster;
	}

	public void setEvaluationMaster(List<EvaluationMaster> evaluationMaster) {
		this.evaluationMaster = evaluationMaster;
	}

	public List<ResumeHolder> getResumes() {
		return resumes;
	}

	public void setResumes(List<ResumeHolder> resumes) {
		this.resumes = resumes;
	}

	public List<ProjectMaster> getProjectId() {
		return projectId;
	}

	public void setProjectId(List<ProjectMaster> projectId) {
		this.projectId = projectId;
	}

}
