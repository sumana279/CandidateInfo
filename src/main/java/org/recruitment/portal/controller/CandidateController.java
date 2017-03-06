package org.recruitment.portal.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.recruitment.portal.model.CandPayload;
import org.recruitment.portal.model.CandidateMaster;
import org.recruitment.portal.model.CandidateResponse;
import org.recruitment.portal.model.EvaluationPayload;
import org.recruitment.portal.model.ExecResponse;
import org.recruitment.portal.model.ProjMasterPayload;
import org.recruitment.portal.model.ProjectInfoDetails;
import org.recruitment.portal.model.ProjectInfoDetailsResponse;
import org.recruitment.portal.model.ProjectInfoRequest;
import org.recruitment.portal.model.ProjectMaster;
import org.recruitment.portal.model.ProjectResponse;
import org.recruitment.portal.model.ResumeHolder;
import org.recruitment.portal.model.ResumePayload;
import org.recruitment.portal.model.ResumeResponse;
import org.recruitment.portal.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/candInfo")
public class CandidateController {
	@Autowired
	CandidateService candidateService;

	ObjectMapper mapper;

	private CandPayload candPayload;

	private ProjMasterPayload projMasterPayload;

	private EvaluationPayload evalPayload;

	public ObjectMapper getMapper() {
		return mapper;
	}

	public void setMapper(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	@RequestMapping(value = "/addCandidate", method = RequestMethod.POST, headers = "Accept=application/json")
	public CandidateResponse addCandidate(@RequestBody String payload) {
		try {
			candPayload = new CandPayload();
			candPayload = mapper.readValue(payload, CandPayload.class);
			return candidateService.addCandidate(candPayload.getCandidate());

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new CandidateResponse(ExecResponse.FAILURE, "Please Check candidate id/link id as add candidate");

	}

	@RequestMapping(value = "/addProjects", method = RequestMethod.POST, headers = "Accept=application/json")
	public ProjectResponse addProjects(@RequestBody String payload) {
		try {
			projMasterPayload = new ProjMasterPayload();
			projMasterPayload = mapper.readValue(payload, ProjMasterPayload.class);
			return candidateService.addProjects(projMasterPayload.getProjects());

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ProjectResponse pr = new ProjectResponse();
		pr.setMessage("Sorry Failed to Add/Insert");
		pr.setStatus(ExecResponse.FAILURE);
		return pr;
	}

	@RequestMapping(value = "/updateCandidateProject", method = RequestMethod.PUT, headers = "Accept=application/json")
	public CandidateResponse updateCandidateProject(@RequestBody String payload) {
		try {
			projMasterPayload = new ProjMasterPayload();
			projMasterPayload = mapper.readValue(payload, ProjMasterPayload.class);
			if (projMasterPayload.getCandId() > 0 && projMasterPayload.getProjects().getProjId() > 0) {
				return candidateService.updateCandidateProject(projMasterPayload.getCandId(),
						projMasterPayload.getLinkId(), projMasterPayload.getProjects());
			}
			return new CandidateResponse(ExecResponse.FAILURE, "Please pass both candidateId/ProjectId");
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new CandidateResponse(ExecResponse.FAILURE,
				"Please Check candidate id/link id as unable to add project for a candidate");
	}

	@RequestMapping(value = "/getProjects", method = RequestMethod.GET, headers = "Accept=application/json")
	public ProjectResponse getProjects() {
		return candidateService.getProjects();
	}

	@RequestMapping(value = "/getProjectById", method = RequestMethod.GET, headers = "Accept=application/json")
	public ProjectResponse getProjectById(@RequestParam("projId") String id) {
		return candidateService.getProjectById(Long.parseLong(id));
	}

	@RequestMapping(value = "/getAllCandidates", method = RequestMethod.GET, headers = "Accept=application/json")
	public CandidateResponse getAllCandidates() {
		return candidateService.getAllCandidates();
	}

	@RequestMapping(value = "/getByCandidateId/{candId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public CandidateResponse getByCandidateId(@PathVariable("candId") String candId) {
		return candidateService.getByCandidateId(Long.parseLong(candId));
	}

	@RequestMapping(value = "/addEvalResponse", method = RequestMethod.PUT, headers = "Accept=application/json")
	public CandidateResponse addEvalResponse(@RequestBody String payload) {
		try {
			evalPayload = new EvaluationPayload();
			evalPayload = mapper.readValue(payload, EvaluationPayload.class);
			return candidateService.addEvalResponse(evalPayload.getCandId(), evalPayload.getLinkId(),
					evalPayload.getEvaluations());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new CandidateResponse(ExecResponse.FAILURE,
				"Please Check candidate id/link id as unable to add evaluation for a candidate");

	}

	@RequestMapping(value = "/getCandidateResume/{candId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResumeResponse getCandidateResume(@PathVariable("candId") long candId, @RequestParam("path") String path) {
		return candidateService.getCandidateResume(candId, path);

	}

	@RequestMapping(value = "/updateCandResume", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResumeResponse updateCandResume(@RequestBody String payload) {
		ResumePayload resumePayload = null;

		try {
			resumePayload = new ResumePayload();
			resumePayload = mapper.readValue(payload, ResumePayload.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return candidateService.updateCandResume(resumePayload.getResumeList(), resumePayload.getCandId());
	}

	// If possible move the executive summary to new controller not to club both
	@RequestMapping(value = "/getPrjDtls4Exec", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody ProjectInfoDetailsResponse getPrjDtls4Exec(@RequestBody String payload) {
		ProjectInfoRequest projectInfoRequest = null;

		try {
			projectInfoRequest = new ProjectInfoRequest();
			projectInfoRequest = mapper.readValue(payload, ProjectInfoRequest.class);
			return candidateService.getPrjDtls4Exec(projectInfoRequest);

		} catch (Exception e) {
			return new ProjectInfoDetailsResponse(ExecResponse.FAILURE, "Failed to get info for projectId"+ e);
		}

	}
}
