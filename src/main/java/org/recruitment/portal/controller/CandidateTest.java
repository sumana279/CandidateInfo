package org.recruitment.portal.controller;

import java.util.ArrayList;
import java.util.List;

import org.recruitment.portal.model.EvaluationMaster;
import org.recruitment.portal.model.EvaluationPayload;
import org.recruitment.portal.model.ProjMasterPayload;
import org.recruitment.portal.model.ProjectMaster;

import com.google.gson.Gson;

public class CandidateTest {

	public static void main(String[] args) {
		EvaluationPayload evalPay = new EvaluationPayload();
		EvaluationMaster em = new EvaluationMaster();
		EvaluationMaster em2 = new EvaluationMaster();
		List<EvaluationMaster> ems = new ArrayList<EvaluationMaster>();
		em.setExternalInterviewer(false);
		em.setFeedBack("Avg");
		em.setInterviewer("sts@cts.com");
		em2.setExternalInterviewer(true);
		em2.setFeedBack("good");
		em2.setInterviewer("sts1@cts.com");
		ems.add(em);
		ems.add(em2);
		evalPay.setCandId(1);
		evalPay.setEvaluations(ems);
		evalPay.setLinkId(1);
		Gson gson = new Gson();
		String json = gson.toJson(evalPay);
		// session.saveOrUpdate(cm);
		// tx.commit();
		System.out.println(json);

	}

}
