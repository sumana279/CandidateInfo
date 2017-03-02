package org.recruitment.portal.controller;

import java.util.ArrayList;
import java.util.List;

import org.recruitment.portal.model.EvaluationMaster;
import org.recruitment.portal.model.EvaluationPayload;
import org.recruitment.portal.model.ProjMasterPayload;
import org.recruitment.portal.model.ProjectMaster;
import org.recruitment.portal.model.ResumeHolder;
import org.recruitment.portal.model.ResumePayload;

import com.google.gson.Gson;

public class CandidateTest {

	public static void main(String[] args) {
		ProjMasterPayload pp = new ProjMasterPayload();
		pp.setCandId(1);
		pp.setLinkId(1);
		ProjectMaster projectMaster = new ProjectMaster();
		projectMaster.setJobCategory("SB Devops");
		projectMaster.setJobDesc("DevOps");
		projectMaster.setLocation("SB");
		projectMaster.setPostionFilledOffsite(0);
		projectMaster.setPostionFilledOnsite(0);
		projectMaster.setPostionNeededOffsite(25);
		projectMaster.setPostionNeededOnsite(20);
		projectMaster.setProjName("CES");
		projectMaster.setTechSkills("Java/DB");
		pp.setProjects(projectMaster);
		Gson gson = new Gson();
		String json = gson.toJson(pp);
		// session.saveOrUpdate(cm);
		// tx.commit();
		System.out.println(json);

	}

}
