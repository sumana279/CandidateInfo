package org.recruitment.portal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Resume_Holder")
public class ResumeHolder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long resumeId;
	private byte[] resumetoBlob;
	private Date createdDate;
	private Date modifiedDate;

	public long getResumeId() {
		return resumeId;
	}

	public void setResumeId(long resumeId) {
		this.resumeId = resumeId;
	}

	public byte[] getResumetoBlob() {
		return resumetoBlob;
	}

	public void setResumetoBlob(byte[] resumetoBlob) {
		this.resumetoBlob = resumetoBlob;
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

}
