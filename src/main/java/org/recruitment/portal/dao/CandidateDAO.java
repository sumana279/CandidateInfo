package org.recruitment.portal.dao;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.recruitment.portal.model.CandidateLink;
import org.recruitment.portal.model.CandidateMaster;
import org.recruitment.portal.model.CandidateResponse;
import org.recruitment.portal.model.EvaluationMaster;
import org.recruitment.portal.model.ExecResponse;
import org.recruitment.portal.model.ProjectMaster;
import org.recruitment.portal.model.ProjectResponse;
import org.recruitment.portal.model.ResumeHolder;
import org.recruitment.portal.model.ResumeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CandidateDAO {
	private final String CAND_ID = "select cm.candidateId,cm.firstName,cm.lastName,cm.middleName,cm.phoneNo,cm.email,cm.shortDesc,cm.visa,cm.candidateCurrentStatus,clks,cm.ctsId, cm.createDate,cm.modifiedDate from CandidateMaster cm LEFT JOIN cm.candidateLinks clks where cm.candidateId= :candidateId";
	private final String RESUME_ID = "select cm.candidateId,cm.email,rm from CandidateMaster cm LEFT JOIN cm.resumes rm where cm.candidateId= :candidateId";
	private final String UPD_RESUME_ID = "select cm,rm from CandidateMaster cm LEFT JOIN cm.resumes rm where cm.candidateId= :candidateId";
	private final String CAND_ALL = "select cm.candidateId, cm.candidateCurrentStatus, cm.createDate, cm.ctsId, cm.email, cm.firstName, cm.lastName, cm.middleName, cm.modifiedBy, cm.modifiedDate, cm.phoneNo, cm.shortDesc, cm.visa ,clks from CandidateMaster cm LEFT JOIN cm.candidateLinks clks";
	private final String PROJ_ID = "FROM ProjectMaster where projId = :projId";
	private final String PROJ_ALL = "FROM ProjectMaster order by createdDate desc";
	private final String CAND_FULL_DETAILS = "from CandidateMaster cm  where cm.candidateId= :candidateId";
	private final String CAND_BY_EMAIL = "select cm.candidateId,cm.email from CandidateMaster cm where cm.email = :email";
	private static final String uploadedFileLocation = "//home//sudhan//Downloads//test.txt";

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public CandidateResponse addCandidate(CandidateMaster candidateInfo) {
		CandidateResponse cmr = new CandidateResponse();
		String email = "";
		long candId = 0;
		try {
			final Session session = this.sessionFactory.getCurrentSession();
			candidateInfo.setCreateDate(new Date());
			candidateInfo.setModifiedDate(new Date());
			session.saveOrUpdate(candidateInfo);
			List<CandidateMaster> cmList = new ArrayList<CandidateMaster>();
			List<Object[]> canObj = (List<Object[]>) session.createQuery(CAND_BY_EMAIL)
					.setParameter("email", candidateInfo.getEmail()).list();
			for (Object[] tuple : canObj) {
				candId = (long) tuple[0];
				email = (String) tuple[1];
			}
			cmr.setStatus(ExecResponse.OK);
			// cmr.setCandMasterList(cmList);
			cmr.setMessage("SuccessFul for emailId " + email + " has a new Id " + candId);
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
		List<CandidateMaster> cmOldList = new ArrayList<>();
		try {
			if (withId != 0) {
				List<Object[]> cmObj = session.createQuery(CAND_ID).setParameter("candidateId", withId).list();
				if (cmObj.size() > 0 && !cmObj.isEmpty()) {
					for (Object[] tuple : cmObj) {
						CandidateLink cl = new CandidateLink();
						List<CandidateLink> clist = new ArrayList<>();

						clist.add((CandidateLink) tuple[9]);
						CandidateMaster cM = new CandidateMaster((long) tuple[0], (String) tuple[1], (String) tuple[2],
								(String) tuple[3], (String) tuple[4], (String) tuple[5], (String) tuple[6],
								(String) tuple[7], (String) tuple[8], clist, (long) tuple[10], (Date) tuple[11],
								(Date) tuple[12]);
						// cM = (CandidateMaster) tuple[0];
						// cM.setCandidateId((long) tuple[0]);
						// cM.setCandidateCurrentStatus((String) tuple[1]);
						cmOldList.add(cM);

					}

					candResponse.setCandMasterList(cmOldList);
				}
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

	public CandidateResponse updateCandidateProject(long candId, int linkId, ProjectMaster projectMasters) {
		final Session session = this.sessionFactory.getCurrentSession();
		ProjectMaster projectMaster = null;
		List<CandidateMaster> cmList = session.createQuery(CAND_FULL_DETAILS).setParameter("candidateId", candId)
				.list();
		if (cmList.size() > 0 && !cmList.isEmpty()) {
			CandidateMaster candidateMaster = cmList.get(0);

			List<ProjectMaster> pmList = getProjectById(projectMasters.getProjId()).getProjMasterList();
			if (pmList.size() > 0 && !pmList.isEmpty()) {
				projectMaster = pmList.get(0);
			}

			List<ProjectMaster> pmOldList = candidateMaster.getCandidateLinks().get(linkId - 1).getProjectId();
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
		List<CandidateMaster> cmList = session.createQuery(CAND_FULL_DETAILS).setParameter("candidateId", candId)
				.list();
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
		return new CandidateResponse(ExecResponse.FAILURE,
				"Please Check candidate id/link id as unable to find candidate");

	}

	public ResumeResponse getCandidateResume(long candId, String path) {
		ResumeResponse resumeResponse = new ResumeResponse();
		try {
			final Session session = this.sessionFactory.getCurrentSession();

			List<Object[]> resumeObj = (List<Object[]>) session.createQuery(RESUME_ID)
					.setParameter("candidateId", candId).list();
			for (Object[] tuple : resumeObj) {
				long candIds = (long) tuple[0];
				ResumeHolder ue = (ResumeHolder) tuple[1];
				writeToFile(new ByteArrayInputStream(ue.getResumetoBlob()), path);
			}

			resumeResponse.setStatus(ExecResponse.OK);
			path = path.replace("//", "/");
			resumeResponse.setMessage("SuccessFully downloaded the file at path: " + path);
		} catch (Exception ex) {
			resumeResponse.setStatus(ExecResponse.FAILURE);
			resumeResponse.setMessage(ex.toString());
		}
		return resumeResponse;
	}

	private void writeToFile(InputStream uploadedInputStream, String FILE_LOC) {

		try {
			OutputStream out = new FileOutputStream(new File(FILE_LOC));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(FILE_LOC));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public ResumeResponse updateCandResume(List<ResumeHolder> resumeHolderList, long candId) {
		try {
			final Session session = this.sessionFactory.getCurrentSession();
			CandidateMaster candidateMaster = null;

			List<Object[]> candidateMasterObj = (List<Object[]>) session.createQuery(UPD_RESUME_ID)
					.setParameter("candidateId", candId).list();
			for (Object[] tuple : candidateMasterObj) {
				candidateMaster = (CandidateMaster) tuple[0];
				ResumeHolder ue = (ResumeHolder) tuple[1];
			}
			List<ResumeHolder> resumeHolderOld = candidateMaster.getResumes();

			if (resumeHolderOld.size() > 0) {
				for (ResumeHolder eval : resumeHolderList) {
					resumeHolderOld.add(eval);
				}
				candidateMaster.setResumes(resumeHolderOld);
				session.update(candidateMaster);

			} else {
				candidateMaster.setResumes(resumeHolderList);
				session.update(candidateMaster);

			}
			return new ResumeResponse(ExecResponse.OK, "Uploaded Resume for candidateId : " + candId);

		} catch (Exception ex) {
			return new ResumeResponse(ExecResponse.FAILURE,
					"Failed to upload Resume for candidateId with reason : " + ex);
		}
	}
}
