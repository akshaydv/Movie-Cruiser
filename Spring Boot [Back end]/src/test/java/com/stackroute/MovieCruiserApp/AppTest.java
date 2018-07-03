package com.stackroute.MovieCruiserApp;
 
import junit.framework.TestCase;
import junit.framework.TestSuite; 
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner; 
import com.stackroute.moviecruiser.App;
import com.stackroute.moviecruiser.domain.Movie;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppTest  extends TestCase {
   
	@LocalServerPort
	private int port;
	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();
	Movie movie;    
    
	@Before
    public void setUp() throws Exception {
//         movie = new Movie(16,"Dark Knight","Dark knight is saved", "https://image.tmdb.org/t/p/w500//9E2y5Q7WlCVNEhP5GiVTjhEhx1o.jpg", );
    }
	
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
    
    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void testSaveUser() throws Exception { 

    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Movie> entity = new HttpEntity<Movie>(movie, headers); 
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/movie/save"),
                HttpMethod.POST, entity, String.class); 
        assertNotNull(response);
        String actual = response.getBody();
        System.out.println(actual);
        assertEquals("Movie saved successfully",actual); 
    }
    
    @Test
    public void testList() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/movie/all"),
                HttpMethod.GET, entity, String.class);
        assertNotNull(response);
    } 
    
    @Test
    public void testDeleteMovie() throws Exception {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Movie> entity = new HttpEntity<Movie>(movie, headers); 
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/movie/delete"),
                HttpMethod.DELETE, entity, String.class); 
        assertNotNull(response);
        String actual = response.getBody();
        System.out.println(actual);
        assertEquals("Movie deleted successfully",actual);
    }
    
}
