package org.recruitment.portal.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.recruitment.portal.model.ProjectInfoRequest;

import com.google.gson.Gson;

public class CandidateTest {

	public static void main(String[] args) {
		ProjectInfoRequest pp = new ProjectInfoRequest();
		pp.setProjId(1);
		pp.setCreatedDate(new Date());
		pp.setModifiedDate(new Date());
		List<String> statusReq = new ArrayList<String>();
		statusReq.add("Pending");
		statusReq.add("hired");
		pp.setStatusReq(statusReq);
		StringBuffer status = new StringBuffer();
		for (String ms : statusReq) {
			status = status.append(ms).append(",");

		}
		System.out.println(status.deleteCharAt(status.length() - 1));
		Gson gson = new Gson();
		String json = gson.toJson(pp);
		// session.saveOrUpdate(cm);
		// tx.commit();
		System.out.println(json);

	}
	
	
	/*{
  "projId": 1,
  "createdDate": "Mar 5, 2017 4:22:29 PM",
  "modifiedDate": "Mar 5, 2017 4:22:29 PM",
  "statusReq": [
    "Pending",
    "hired"
  ]
}*/

}
