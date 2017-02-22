package org.recruitment.portal.model;

import java.util.List;

public class ProjectResponse {
	private ExecResponse status;
	private List<ProjectMaster> projMasterList;
	private String message;

	public ExecResponse getStatus() {
		return status;
	}

	public void setStatus(ExecResponse status) {
		this.status = status;
	}

	public List<ProjectMaster> getProjMasterList() {
		return projMasterList;
	}

	public void setProjMasterList(List<ProjectMaster> projMasterList) {
		this.projMasterList = projMasterList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
