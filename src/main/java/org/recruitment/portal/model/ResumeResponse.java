package org.recruitment.portal.model;

import java.util.List;

public class ResumeResponse {
	private ExecResponse status;
	//private List<ResumeHolder> resumeMasterList;
	private String message;

	public ResumeResponse(ExecResponse status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public ResumeResponse(ExecResponse status, List<ResumeHolder> candMasterList, String message) {
		super();
		this.status = status;
		//this.resumeMasterList = resumeMasterList;
		this.message = message;
	}

	public ResumeResponse() {
	}

	public ExecResponse getStatus() {
		return status;
	}

	public void setStatus(ExecResponse ok) {
		this.status = ok;
	}

//	public List<ResumeHolder> getResumeMasterList() {
//		return resumeMasterList;
//	}
//
//	public void setResumeMasterList(List<ResumeHolder> resumeMasterList) {
//		this.resumeMasterList = resumeMasterList;
//	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
