package org.recruitment.portal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Project_Master")
public class ProjectMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long projId;
	private String projName;
	private long postionNeededOnsite;
	private long postionNeededOffsite;
	private long postionFilledOnsite;
	private long postionFilledOffsite;
	private String jobDesc;
	private String jobCategory;
	private String location;
	private String techSkills;
	private Date createdDate;
	private Date modifiedDate;
	private String modifiedBy;

	public long getProjId() {
		return projId;
	}

	public void setProjId(long projId) {
		this.projId = projId;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public long getPostionNeededOnsite() {
		return postionNeededOnsite;
	}

	public void setPostionNeededOnsite(long postionNeededOnsite) {
		this.postionNeededOnsite = postionNeededOnsite;
	}

	public long getPostionNeededOffsite() {
		return postionNeededOffsite;
	}

	public void setPostionNeededOffsite(long postionNeededOffsite) {
		this.postionNeededOffsite = postionNeededOffsite;
	}

	public long getPostionFilledOnsite() {
		return postionFilledOnsite;
	}

	public void setPostionFilledOnsite(long postionFilledOnsite) {
		this.postionFilledOnsite = postionFilledOnsite;
	}

	public long getPostionFilledOffsite() {
		return postionFilledOffsite;
	}

	public void setPostionFilledOffsite(long postionFilledOffsite) {
		this.postionFilledOffsite = postionFilledOffsite;
	}

	public String getJobDesc() {
		return jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	public String getJobCategory() {
		return jobCategory;
	}

	public void setJobCategory(String jobCategory) {
		this.jobCategory = jobCategory;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTechSkills() {
		return techSkills;
	}

	public void setTechSkills(String techSkills) {
		this.techSkills = techSkills;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}
