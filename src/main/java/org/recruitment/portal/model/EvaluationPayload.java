package org.recruitment.portal.model;

import java.util.List;

public class EvaluationPayload {

	private long candId;
	private int linkId;
	private List<EvaluationMaster> evaluations;

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

	public List<EvaluationMaster> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<EvaluationMaster> evaluations) {
		this.evaluations = evaluations;
	}

}
