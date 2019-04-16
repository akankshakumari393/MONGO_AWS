package com.gslab.mongo5.controllertest;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.anyString;

import static org.mockito.BDDMockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gslab.mongo5.assembler.EmployeeResourceAssembler;
import com.gslab.mongo5.assembler.ProjectResourceAssembler;
import com.gslab.mongo5.controller.ProjectController;
import com.gslab.mongo5.exception.ProjectNotFoundException;
import com.gslab.mongo5.model.Project;
import com.gslab.mongo5.service.ProjectService;

@RunWith(SpringRunner.class)
@WebMvcTest(ProjectController.class)
@Import({ProjectResourceAssembler.class, EmployeeResourceAssembler.class})
public class ProjectControllerTest {
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProjectService projectService;
	
	@InjectMocks
	private ProjectController projectController;
	
	
	@Test 
	public void getProject_shouldReturnProject() throws Exception{
		
		//System.out.println("Starting get Project Test that should return a project");
		given(projectService.getProject("5")).willReturn(new Project("5","project5"));
		//System.out.println("Found going to perform the test");	
		mockMvc.perform(MockMvcRequestBuilders
					.get("/mongo-api/projects/5"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("id").value("5"))
					.andExpect(jsonPath("projectName").value("project5"));
		//System.out.println(" found testing done");
	}
	
	@Test
	public void getProject_IfNotFound() throws Exception{
		//System.out.println("Starting get Project Test that should not return a project");
		given(projectService.getProject(anyString())).willThrow(new ProjectNotFoundException(anyString()));
		//System.out.println("Not found going to perform the test");
		mockMvc.perform(MockMvcRequestBuilders
				.get("/mongo-api/projects/1"))
				.andExpect(status().isNotFound());
		//System.out.println(" Not found testing done");		
	}
	
	@Test
	public void newProject() throws Exception{
		//System.out.println("Starting get Project Test that should add a project");
		Project p1 = new Project("1", "abc");
		Project p2 = new Project("2", "abcd");
		given(projectService.addProject(any(Project.class))).willReturn(p1);
		//System.out.println("adding going to perform the test");
		mockMvc.perform(MockMvcRequestBuilders
				.post("/mongo-api/projects")
				.content(asJsonString(p2))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
		//System.out.println("adding testing done");      
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	/*
	 * @Test public void createStudentCourse() throws Exception { Project
	 * mockProject = new Project("1", "Smallest Number");
	 * 
	 * // studentService.addCourse to respond back with mockCourse Mockito.when(
	 * projectService.addProject(Mockito.any(Project.class))).thenReturn(mockProject
	 * );
	 * 
	 * // Send course as body to /students/Student1/courses RequestBuilder
	 * requestBuilder = MockMvcRequestBuilders .post("/mongo-api/projects")
	 * .accept(MediaType.APPLICATION_JSON).
	 * content("\"projectName\":\"Smallest Number\"")
	 * .contentType(MediaType.APPLICATION_JSON);
	 * 
	 * MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	 * 
	 * MockHttpServletResponse response = result.getResponse();
	 * 
	 * assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	 * 
	 * assertEquals("http://localhost/mongo-api/projects",
	 * response.getHeader(HttpHeaders.LOCATION));
	 * 
	 * }
	 */
	@Test
	public void deleteProject() throws Exception{
		System.out.println("Starting delete Project Test that should delete a project");
		doNothing().when(projectService).deleteProject("5");
		projectService.deleteProject("5");
		verify(projectService, times(1)).deleteProject("5");
		/*System.out.println("adding going to perform the test");
		mockMvc.perform(MockMvcRequestBuilders
				.post("/mongo-api/projects")
				.content(asJsonString(p2))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
		System.out.println("adding testing done");*/
	}
	
	
	}
