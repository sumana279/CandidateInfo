package org.recruitment.portal.model;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Evaluation_Master")
public class EvaluationMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long evalId;
	private String interviewer;
	private Date assignedDate;
	private Date submittedDate;
	private boolean isExternalInterviewer;
	private String feedBack;
	private String remarks;

	public long getEvalId() {
		return evalId;
	}

	public void setEvalId(long evalId) {
		this.evalId = evalId;
	}

	public String getInterviewer() {
		return interviewer;
	}

	public void setInterviewer(String interviewer) {
		this.interviewer = interviewer;
	}

	public Date getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}

	public Date getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	public boolean isExternalInterviewer() {
		return isExternalInterviewer;
	}

	public void setExternalInterviewer(boolean isExternalInterviewer) {
		this.isExternalInterviewer = isExternalInterviewer;
	}

	public String getFeedBack() {
		return feedBack;
	}

	public void setFeedBack(String feedBack) {
		this.feedBack = feedBack;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
