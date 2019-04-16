package com.gslab.mongo5.servicetest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.gslab.mongo5.exception.ProjectNotFoundException;
import com.gslab.mongo5.model.Project;
import com.gslab.mongo5.repository.ProjectRepository;
import com.gslab.mongo5.service.ProjectService;

@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceTest {

	@Mock
	private ProjectRepository projectRepository;
	
	private ProjectService projectService;
	
	@Before
	public void setUp() throws Exception {
		projectService = new ProjectService(projectRepository);
	}
	
	@Test
	public void getProject_returnProject() {
		Optional<Project> p = Optional.of(new Project("2","hello"));
		given(projectRepository.findByid("2")).willReturn(p);
		
		Project p1 = projectService.getProject("2");
		assertThat(p1.getId()).isEqualTo("2");
		assertThat(p1.getProjectName()).isEqualTo("hello");
	}
	
	@Test( expected = ProjectNotFoundException.class)
	public void getProject_whenCarNotFound() {
		//Optional<Project> p = Optional.of(new Project("2","hello"));
		given(projectRepository.findByid(anyString())).willReturn(Optional.empty());
		
		projectService.getProject("3");
				
	}
	
	@Test
	public void addProject() {
		Project savedProject = new Project("2","project2");
		given(projectRepository.save(any(Project.class))).willReturn(savedProject);
		Project recievedProject = projectService.addProject(savedProject);
		assertThat(recievedProject.getId()).isEqualTo("2");
		assertThat(recievedProject.getProjectName()).isEqualTo("project2");
	}
	
	@Test 
	public void deleteProject() {
		doNothing().when(projectRepository).deleteByid(anyString());
		projectRepository.deleteByid("5");
		verify(projectRepository, times(1)).deleteByid("5");
	}
}
