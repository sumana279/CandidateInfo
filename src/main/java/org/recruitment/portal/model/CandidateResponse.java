package org.recruitment.portal.model;

import java.util.List;

public class CandidateResponse {

	private ExecResponse status;
	private List<CandidateMaster> candMasterList;
	private String message;

	public CandidateResponse(ExecResponse status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public CandidateResponse(ExecResponse status, List<CandidateMaster> candMasterList, String message) {
		super();
		this.status = status;
		this.candMasterList = candMasterList;
		this.message = message;
	}

	public CandidateResponse() {
	}

	public ExecResponse getStatus() {
		return status;
	}

	public void setStatus(ExecResponse ok) {
		this.status = ok;
	}

	public List<CandidateMaster> getCandMasterList() {
		return candMasterList;
	}

	public void setCandMasterList(List<CandidateMaster> candMasterList) {
		this.candMasterList = candMasterList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
