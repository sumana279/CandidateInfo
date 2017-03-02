package org.recruitment.portal.model;

import java.util.List;

public class ResumePayload {
	private long candId;
	private List<ResumeHolder> resumeList;
	
	public long getCandId() {
		return candId;
	}
	public void setCandId(long candId) {
		this.candId = candId;
	}
	public List<ResumeHolder> getResumeList() {
		return resumeList;
	}
	public void setResumeList(List<ResumeHolder> resumeList) {
		this.resumeList = resumeList;
	}
	
}
