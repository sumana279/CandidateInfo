package org.recruitment.portal.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.recruitment.portal.model.CandidateMaster;
import org.recruitment.portal.model.CandidateResponse;
import org.recruitment.portal.model.EvaluationMaster;
import org.recruitment.portal.model.ExecResponse;
import org.recruitment.portal.model.ProjectMaster;
import org.recruitment.portal.model.ProjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CandidateDAO {
	private final String CAND_ID = "FROM CandidateMaster where candidateId = :candidateId";
	private final String CAND_ALL = "FROM CandidateMaster";
	private final String PROJ_ID = "FROM ProjectMaster where projId = :projId";
	private final String PROJ_ALL = "FROM ProjectMaster order by createdDate desc";

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public CandidateResponse addCandidate(CandidateMaster candidateInfo) {
		CandidateResponse cmr = new CandidateResponse();
		try {
			final Session session = this.sessionFactory.getCurrentSession();
			candidateInfo.setCreateDate(new Date());
			candidateInfo.setModifiedDate(new Date());
			session.saveOrUpdate(candidateInfo);
			List<CandidateMaster> cmList = new ArrayList<CandidateMaster>();
			cmList = session.createQuery("FROM CandidateMaster").list();
			cmr.setStatus(ExecResponse.OK);
			cmr.setCandMasterList(cmList);
			cmr.setMessage("SuccessFul");
		} catch (Exception ex) {
			cmr.setStatus(ExecResponse.FAILURE);
			cmr.setMessage(ex.toString());
		}
		return cmr;
	}

	public CandidateResponse getAllCandidates() {
		final Session session = this.sessionFactory.getCurrentSession();
		return getCandidatesPriv(session, 0);
	}

	public CandidateResponse getByCandidateId(long withId) {
		final Session session = this.sessionFactory.getCurrentSession();
		return getCandidatesPriv(session, withId);
	}

	private CandidateResponse getCandidatesPriv(Session sessions, long withId) {
		CandidateResponse candResponse = new CandidateResponse();
		final Session session = this.sessionFactory.getCurrentSession();

		try {
			if (withId != 0) {
				candResponse.setCandMasterList(session.createQuery(CAND_ID).setParameter("candidateId", withId).list());
			} else {
				candResponse.setCandMasterList(session.createQuery(CAND_ALL).list());
			}
			candResponse.setStatus(ExecResponse.OK);
			candResponse.setMessage("SuccessFul");
		} catch (Exception ex) {
			candResponse.setStatus(ExecResponse.FAILURE);
			candResponse.setMessage(ex.toString());
		}
		return candResponse;
	}

	public ProjectResponse getProjects() {
		final Session session = this.sessionFactory.getCurrentSession();
		return getProjectsPriv(session, 0);
	}

	public ProjectResponse getProjectById(long withId) {
		final Session session = this.sessionFactory.getCurrentSession();
		return getProjectsPriv(session, withId);
	}

	private ProjectResponse getProjectsPriv(Session session, long withId) {
		ProjectResponse projResponse = new ProjectResponse();
		try {
			if (withId != 0) {
				projResponse.setProjMasterList(session.createQuery(PROJ_ID).setParameter("projId", withId).list());
			} else {
				projResponse.setProjMasterList(session.createQuery(PROJ_ALL).list());
			}

			projResponse.setMessage("Successfull");
			projResponse.setStatus(ExecResponse.OK);
		} catch (Exception ex) {
			projResponse.setStatus(ExecResponse.FAILURE);
			projResponse.setMessage(ex.toString());
		}

		return projResponse;
	}

	public ProjectResponse addProjects(ProjectMaster projectMaster) {
		ProjectResponse projResponse = new ProjectResponse();
		try {
			final Session session = this.sessionFactory.getCurrentSession();
			projectMaster.setCreatedDate(new Date());
			projectMaster.setModifiedBy("addProjects");
			projectMaster.setModifiedDate(new Date());
			session.saveOrUpdate(projectMaster);
			return getProjectsPriv(session, 0);
		} catch (Exception ex) {
			projResponse.setStatus(ExecResponse.FAILURE);
			projResponse.setMessage(ex.toString());
			return projResponse;
		}
	}

	public CandidateResponse updateCandidateProject(long candId, long projId) {
		final Session session = this.sessionFactory.getCurrentSession();
		ProjectMaster projectMaster = null;
		List<CandidateMaster> cmList = getByCandidateId(candId).getCandMasterList();
		if (cmList.size() > 0 && !cmList.isEmpty()) {
			CandidateMaster candidateMaster = cmList.get(0);
			List<ProjectMaster> pmList = getProjectById(projId).getProjMasterList();
			if (pmList.size() > 0 && !pmList.isEmpty()) {
				projectMaster = pmList.get(0);
			}
			List<ProjectMaster> pmOldList = candidateMaster.getCandidateLinks().get(0).getProjectId();
			List<ProjectMaster> pmNewList = new ArrayList<ProjectMaster>();
			if (pmOldList.size() > 0) {
				for (ProjectMaster pms : pmOldList) {
					ProjectMaster pmIncr = pms;
					pmNewList.add(pmIncr);
				}
			}
			pmNewList.add(projectMaster);
			candidateMaster.getCandidateLinks().get(0).setProjectId(pmNewList);
			session.update(candidateMaster);
		}

		return getByCandidateId(candId);
	}

	public CandidateResponse addEvalResponse(long candId, int linkId, List<EvaluationMaster> evalMasterList) {
		final Session session = this.sessionFactory.getCurrentSession();
		List<CandidateMaster> cmList = getByCandidateId(candId).getCandMasterList();
		if (cmList.size() > 0 && !cmList.isEmpty()) {
			CandidateMaster candidateMaster = cmList.get(0);
			List<EvaluationMaster> evalMasterOld = candidateMaster.getCandidateLinks().get(linkId - 1)
					.getEvaluationMaster();
			if (evalMasterOld.size() > 0) {
				for (EvaluationMaster eval : evalMasterList) {
					evalMasterOld.add(eval);
				}
				candidateMaster.getCandidateLinks().get(linkId - 1).setEvaluationMaster(evalMasterOld);
				session.update(candidateMaster);

			} else {
				candidateMaster.getCandidateLinks().get(linkId - 1).setEvaluationMaster(evalMasterList);
				session.update(candidateMaster);

			}
			return getByCandidateId(candId);
		}
		CandidateResponse cr = new CandidateResponse();
		cr.setMessage("Please Check candidate id/link id as unable to find candidate");
		cr.setStatus(ExecResponse.FAILURE);

		return cr;
	}

}
