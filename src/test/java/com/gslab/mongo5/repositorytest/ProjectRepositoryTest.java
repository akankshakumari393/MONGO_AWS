package com.gslab.mongo5.repositorytest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.gslab.mongo5.model.Project;
import com.gslab.mongo5.repository.ProjectRepository;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ProjectRepositoryTest {
	
	@Autowired
	private ProjectRepository projectRepository; 
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
    @Before
    public void setup() throws Exception {

        mongoTemplate.dropCollection(Project.class);
    }

    @After
    public void tearDown() throws Exception {
        mongoTemplate.dropCollection(Project.class);
    }
	
	
	@Test
	public void findByid_returnsProject() {
         
        Project savedProject = projectRepository.save(new Project("1", "blabla"));
        Optional<Project> project = projectRepository.findByid("1");
        
        assertThat(project.get().getId()).isEqualTo(savedProject.getId());
		assertThat(project.get().getProjectName()).isEqualTo(savedProject.getProjectName());
	
	}
	
	@Test
	public void findByid_returnsProjectNotFound() {
		Optional<Project> project = projectRepository.findByid("2");
		
			assertThat(project.isPresent()).isEqualTo(false);
			
	}
	
	@Test
	public void save_returnsProject() {
		Project p1 = new Project();
		Project project = projectRepository.save(p1);
		
			assertThat(project.getId()).isNotEqualTo(null);
			assertThat(project.getProjectName()).isEqualTo(null);
			
	}
	
	@Test
	public void deleteProject() {
		projectRepository.deleteByid("5"); 
			
			
	}
}
