package org.recruitment.portal.controller;

import java.io.IOException;

import org.recruitment.portal.model.CandPayload;
import org.recruitment.portal.model.CandidateMaster;
import org.recruitment.portal.model.CandidateResponse;
import org.recruitment.portal.model.EvaluationPayload;
import org.recruitment.portal.model.ProjMasterPayload;
import org.recruitment.portal.model.ProjectMaster;
import org.recruitment.portal.model.ProjectResponse;
import org.recruitment.portal.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
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
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return candidateService.addCandidate(candPayload.getCandidate());
	}

	@RequestMapping(value = "/addProjects", method = RequestMethod.POST, headers = "Accept=application/json")
	public ProjectResponse addProjects(@RequestBody String payload) {
		try {
			projMasterPayload = new ProjMasterPayload();
			projMasterPayload = mapper.readValue(payload, ProjMasterPayload.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return candidateService.addProjects(projMasterPayload.getProjects());
	}

	@RequestMapping(value = "/updateCandidateProject/{projId}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public CandidateResponse updateCandidateProject(@RequestParam("candId") String candId,
			@PathVariable("projId") String projId) {
		return candidateService.updateCandidateProject(Long.parseLong(candId), Long.parseLong(projId));
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
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return candidateService.addEvalResponse(evalPayload.getCandId(), evalPayload.getLinkId(),
				evalPayload.getEvaluations());
	}

}
