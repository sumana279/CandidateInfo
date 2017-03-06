package org.recruitment.portal.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.recruitment.portal.dao.CandidateDAO;
import org.recruitment.portal.model.CandidateMaster;
import org.recruitment.portal.model.CandidateResponse;
import org.recruitment.portal.model.EvaluationMaster;
import org.recruitment.portal.model.ProjectInfoDetailsResponse;
import org.recruitment.portal.model.ProjectInfoRequest;
import org.recruitment.portal.model.ProjectMaster;
import org.recruitment.portal.model.ProjectResponse;
import org.recruitment.portal.model.ResumeHolder;
import org.recruitment.portal.model.ResumeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CandidateService")
public class CandidateService {
	@Autowired
	CandidateDAO candidateDAO;

	@Transactional
	public CandidateResponse addCandidate(CandidateMaster candidateInfo) {
		return candidateDAO.addCandidate(candidateInfo);
	}

	@Transactional
	public CandidateResponse getAllCandidates() {
		return candidateDAO.getAllCandidates();
	}

	@Transactional
	public CandidateResponse getByCandidateId(long withId) {
		return candidateDAO.getByCandidateId(withId);
	}

	@Transactional
	public ProjectResponse addProjects(ProjectMaster projectMaster) {
		return candidateDAO.addProjects(projectMaster);
	}

	@Transactional
	public ProjectResponse getProjects() {
		return candidateDAO.getProjects();
	}

	@Transactional
	public ProjectResponse getProjectById(long withId) {
		return candidateDAO.getProjectById(withId);
	}

	@Transactional
	public CandidateResponse updateCandidateProject(long candId, int linkId, ProjectMaster projectMaster) {
		return candidateDAO.updateCandidateProject(candId, linkId, projectMaster);
	}

	@Transactional
	public CandidateResponse addEvalResponse(long candId, int linkId, List<EvaluationMaster> evalMasterList) {
		return candidateDAO.addEvalResponse(candId, linkId, evalMasterList);
	}

	@Transactional
	public ResumeResponse getCandidateResume(long candId, String path) {
		return candidateDAO.getCandidateResume(candId, path);
	}

	@Transactional
	public ResumeResponse updateCandResume(List<ResumeHolder> resumeHolderList, long candId) {
		return candidateDAO.updateCandResume(resumeHolderList, candId);
	}

	@Transactional
	public ProjectInfoDetailsResponse getPrjDtls4Exec(ProjectInfoRequest projectInfoRequest) {
		return candidateDAO.getPrjDtls4Exec(projectInfoRequest);
	}

}
