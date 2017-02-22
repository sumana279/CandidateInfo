import java.util.ArrayList;
import java.util.List;

import org.recruitment.portal.model.ProjMasterPayload;
import org.recruitment.portal.model.ProjectMaster;

import com.google.gson.Gson;

public class CandidateTest {

	public static void main(String[] args) {
		ProjMasterPayload pmp = new ProjMasterPayload();
		List<ProjectMaster> projectMasterList = new ArrayList<ProjectMaster>();

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

		projectMasterList.add(projectMaster);
		pmp.setProjects(projectMaster);
		Gson gson = new Gson();
		String json = gson.toJson(pmp);
		// session.saveOrUpdate(cm);
		// tx.commit();
		System.out.println(json);

	}

}
