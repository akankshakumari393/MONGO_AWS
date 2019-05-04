/*
 * package com.gslab.mongo5.controllertest;
 * 
 * import static org.assertj.core.api.Assertions.assertThat; import static
 * org.junit.Assert.assertTrue;
 * 
 * import java.net.URI; import java.net.URISyntaxException; import
 * java.util.Arrays;
 * 
 * import org.junit.Before; import org.junit.Test; import
 * org.junit.runner.RunWith; import org.skyscreamer.jsonassert.JSONAssert;
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.context.SpringBootTest; import
 * org.springframework.boot.test.web.client.TestRestTemplate; import
 * org.springframework.boot.web.server.LocalServerPort; import
 * org.springframework.core.ParameterizedTypeReference; import
 * org.springframework.hateoas.Resource; import
 * org.springframework.http.HttpEntity; import
 * org.springframework.http.HttpHeaders; import
 * org.springframework.http.HttpMethod; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.MediaType; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.test.context.ContextConfiguration; import
 * org.springframework.test.context.junit4.SpringRunner; import
 * org.springframework.web.client.RestClientException;
 * 
 * import com.gslab.mongo5.Mongo5Application; import
 * com.gslab.mongo5.assembler.ProjectResourceAssembler; import
 * com.gslab.mongo5.model.Project;
 * 
 * @RunWith(SpringRunner.class)
 * 
 * @SpringBootTest(classes = Mongo5Application.class, webEnvironment =
 * SpringBootTest.WebEnvironment.RANDOM_PORT)
 * 
 * @ContextConfiguration public class ProjectIntegrationTest {
 * 
 * @LocalServerPort private int port;
 * 
 * @Autowired ProjectResourceAssembler projectResourceAssembler;
 * 
 * @Autowired TestRestTemplate restTemplate;
 * 
 * HttpHeaders headers = new HttpHeaders();
 * 
 * @Before public void before() {
 * headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
 * System.out.
 * println("Running the before method  ----------------------------------------------------------- "
 * ); }
 * 
 * 
 * @Test public void getProject() throws RestClientException, URISyntaxException
 * {
 * 
 * //Project project = new Project("1", "Project1"); HttpEntity<Project> entity=
 * new HttpEntity<Project>(null, headers); System.out.
 * println(" --------------------------------------------------------Before Fetching Response in getProject"
 * );
 * 
 * 
 * ResponseEntity<Resource<Project>> response = restTemplate.exchange( new
 * URI("http://localhost:" + port + "/mongo-api/projects/1"), HttpMethod.GET,
 * entity, new ParameterizedTypeReference<Resource<Project>>() { });
 * 
 * 
 * 
 * ResponseEntity<String> response = restTemplate.exchange(new
 * URI("http://localhost:" + port + "/mongo-api/projects/1"), HttpMethod.GET,
 * entity, String.class);
 * 
 * System.out.
 * println(" --------------------------------------------------------Printing response value in getProject"
 * );
 * 
 * assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
 * assertThat(response.getBody()).isEqualTo("Could not find project 1");
 * 
 * }
 * 
 * 
 * 
 * @Test public void testName() throws RestClientException, URISyntaxException {
 * //ResponseEntity<Project> response1 =
 * restTemplate.getForEntity("/mongo-api/projects/1", null, headers), );
 * //ResponseEntity<Project> response = restTemplate.getForEntity(new
 * URI("http://localhost:" + port + "/mongo-api/projects/1"), Project.class);
 * System.out.
 * println("Started th test method  ----------------------------------------------------------- "
 * );
 * 
 * 
 * HttpEntity<String> entity = new HttpEntity<String>(null, headers);
 * System.out.
 * println("Set HTTPEntity   ----------------------------------------------------------- "
 * ); System.out.println("Set HTTPEntity "+ entity.getHeaders()); System.out.
 * println("Going to set the response value   ----------------------------------------------------------- "
 * ); ResponseEntity<Project> response = restTemplate.exchange(new
 * URI("http://localhost:" + port + "/mongo-api/projects/1"), HttpMethod.GET,
 * entity, Project.class ); System.out.
 * println("the response value  is set ----------------------------------------------------------- "
 * );
 * 
 * System.out.println(" Printing response value "+response.getBody());
 * System.out.
 * println("Started Assertion Test ----------------------------------------------------------- "
 * );
 * 
 * assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
 * assertThat(response.getBody().getId().equals("1")); }
 * 
 * 
 * @Test public void addProject() throws RestClientException, URISyntaxException
 * {
 * 
 * Project project = new Project("1", "Project1");
 * 
 * HttpEntity<Project> entity = new HttpEntity<Project>(project, headers);
 * 
 * ResponseEntity<Project> response = restTemplate.exchange( new
 * URI("http://localhost:" + port + "/mongo-api/projects"), HttpMethod.POST,
 * entity, Project.class); System.out.
 * println(" --------------------------------------------------------Printing response value "
 * ); String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
 * assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
 * assertThat(response.getBody().getId()).isEqualTo("1");
 * assertThat(response.getBody().getProjectName()).isEqualTo("Project1");
 * assertTrue(actual.contains("/mongo-api/projects/1")); }
 * 
 * 
 * @Test public void deleteProject() throws RestClientException,
 * URISyntaxException {
 * 
 * Project project = new Project("1", "Project1");
 * 
 * HttpEntity<Project> entity = new HttpEntity<Project>(project, headers);
 * 
 * ResponseEntity<Project> response = restTemplate.exchange( new
 * URI("http://localhost:" + port + "/mongo-api/projects/1"), HttpMethod.DELETE,
 * entity, Project.class); System.out.
 * println(" --------------------------------------------------------Printing response value "
 * ); String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
 * assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
 * assertThat(response.getBody().getId()).isEqualTo("1");
 * assertThat(response.getBody().getProjectName()).isEqualTo("Project1");
 * assertTrue(actual.contains("/mongo-api/projects/1")); }
 * 
 * 
 * }
 */