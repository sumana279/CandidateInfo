package org.recruitment.portal.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class ProjMasterPayload {
	@JsonIgnoreProperties
	private long candId;
	@JsonIgnoreProperties
	private int linkId;
	@JsonIgnoreProperties
	private long projId;

	private ProjectMaster projects;

	public ProjectMaster getProjects() {
		return projects;
	}

	public void setProjects(ProjectMaster projects) {
		this.projects = projects;
	}

	public long getCandId() {
		return candId;
	}

	public void setCandId(long candId) {
		this.candId = candId;
	}

	public int getLinkId() {
		return linkId;
	}

	public void setLinkId(int linkId) {
		this.linkId = linkId;
	}

	public long getProjId() {
		return projId;
	}

	public void setProjId(long projId) {
		this.projId = projId;
	}

}
