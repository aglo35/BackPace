package application.user;

import application.AbstractControllerTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

@Transactional
public class UserControllerMocksTest extends AbstractControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        setUp(userController);
    }

    @Test
    public void testGetPaceUsers() throws Exception {

        // Create some test data
        Iterable<PaceUser> entityListStubData = getEntityListStubData();

        // Stub the UserRepository.findAll method return value
        when(userRepository.findAll()).thenReturn(entityListStubData);

        // Perform the behavior being tested
        String uri = "/api/users";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType
                .APPLICATION_JSON)).andReturn();

        // Extract the response status and body
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        // Verify the GreetingService.findAll method was invoked once
        verify(userRepository, times(1)).findAll();

        // Perform standard JUnit assertions on the response
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue("failure - expected HTTP response body to have a value", content.trim()
                .length() > 0);
    }

    private Iterable<PaceUser> getEntityListStubData() {

        // TODO: email: profileInfo.email,
        return () -> {
            PaceUser paceUserAuthSuccess = new PaceUser();
            paceUserAuthSuccess.setName("William Wallace");
            paceUserAuthSuccess.setFacebookId("123451234512345");
            paceUserAuthSuccess.setAuthResponse("success");

            ArrayList<PaceUser> entityList = new ArrayList<>();
            entityList.add(paceUserAuthSuccess);

            return entityList.iterator();
        };
    }
//
//    @Test
//    public void testGetGreeting() throws Exception {
//
//        // Create some test data
//        Long id = new Long(1);
//        Greeting entity = getEntityStubData();
//
//        // Stub the GreetingService.findOne method return value
//        when(greetingService.findOne(id)).thenReturn(entity);
//
//        // Perform the behavior being tested
//        String uri = "/api/greetings/{id}";
//
//        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri, id)
//                .accept(MediaType.APPLICATION_JSON)).andReturn();
//
//        // Extract the response status and body
//        String content = result.getResponse().getContentAsString();
//        int status = result.getResponse().getStatus();
//
//        // Verify the GreetingService.findOne method was invoked once
//        verify(greetingService, times(1)).findOne(id);
//
//        // Perform standard JUnit assertions on the test results
//        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
//        Assert.assertTrue(
//                "failure - expected HTTP response body to have a value",
//                content.trim().length() > 0);
//    }
//
//    @Test
//    public void testGetGreetingNotFound() throws Exception {
//
//        // Create some test data
//        Long id = Long.MAX_VALUE;
//
//        // Stub the GreetingService.findOne method return value
//        when(greetingService.findOne(id)).thenReturn(null);
//
//        // Perform the behavior being tested
//        String uri = "/api/greetings/{id}";
//
//        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri, id)
//                .accept(MediaType.APPLICATION_JSON)).andReturn();
//
//        // Extract the response status and body
//        String content = result.getResponse().getContentAsString();
//        int status = result.getResponse().getStatus();
//
//        // Verify the GreetingService.findOne method was invoked once
//        verify(greetingService, times(1)).findOne(id);
//
//        // Perform standard JUnit assertions on the test results
//        Assert.assertEquals("failure - expected HTTP status 404", 404, status);
//        Assert.assertTrue("failure - expected HTTP response body to be empty",
//                content.trim().length() == 0);
//
//    }
//
//    @Test
//    public void testCreateGreeting() throws Exception {
//
//        // Create some test data
//        Greeting entity = getEntityStubData();
//
//        // Stub the GreetingService.create method return value
//        when(greetingService.create(any(Greeting.class))).thenReturn(entity);
//
//        // Perform the behavior being tested
//        String uri = "/api/greetings";
//        String inputJson = super.mapToJson(entity);
//
//        MvcResult result = mvc
//                .perform(MockMvcRequestBuilders.post(uri)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
//                .andReturn();
//
//        // Extract the response status and body
//        String content = result.getResponse().getContentAsString();
//        int status = result.getResponse().getStatus();
//
//        // Verify the GreetingService.create method was invoked once
//        verify(greetingService, times(1)).create(any(Greeting.class));
//
//        // Perform standard JUnit assertions on the test results
//        Assert.assertEquals("failure - expected HTTP status 201", 201, status);
//        Assert.assertTrue(
//                "failure - expected HTTP response body to have a value",
//                content.trim().length() > 0);
//
//        Greeting createdEntity = super.mapFromJson(content, Greeting.class);
//
//        Assert.assertNotNull("failure - expected entity not null",
//                createdEntity);
//        Assert.assertNotNull("failure - expected id attribute not null",
//                createdEntity.getId());
//        Assert.assertEquals("failure - expected text attribute match",
//                entity.getText(), createdEntity.getText());
//    }
//
//    @Test
//    public void testUpdateGreeting() throws Exception {
//
//        // Create some test data
//        Greeting entity = getEntityStubData();
//        entity.setText(entity.getText() + " test");
//        Long id = new Long(1);
//
//        // Stub the GreetingService.update method return value
//        when(greetingService.update(any(Greeting.class))).thenReturn(entity);
//
//        // Perform the behavior being tested
//        String uri = "/api/greetings/{id}";
//        String inputJson = super.mapToJson(entity);
//
//        MvcResult result = mvc
//                .perform(MockMvcRequestBuilders.put(uri, id)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON).content(inputJson))
//                .andReturn();
//
//        // Extract the response status and body
//        String content = result.getResponse().getContentAsString();
//        int status = result.getResponse().getStatus();
//
//        // Verify the GreetingService.update method was invoked once
//        verify(greetingService, times(1)).update(any(Greeting.class));
//
//        // Perform standard JUnit assertions on the test results
//        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
//        Assert.assertTrue(
//                "failure - expected HTTP response body to have a value",
//                content.trim().length() > 0);
//
//        Greeting updatedEntity = super.mapFromJson(content, Greeting.class);
//
//        Assert.assertNotNull("failure - expected entity not null",
//                updatedEntity);
//        Assert.assertEquals("failure - expected id attribute unchanged",
//                entity.getId(), updatedEntity.getId());
//        Assert.assertEquals("failure - expected text attribute match",
//                entity.getText(), updatedEntity.getText());
//
//    }
//
//    @Test
//    public void testDeleteGreeting() throws Exception {
//
//        // Create some test data
//        Long id = new Long(1);
//
//        // Perform the behavior being tested
//        String uri = "/api/greetings/{id}";
//
//        MvcResult result = mvc.perform(MockMvcRequestBuilders.delete(uri, id))
//                .andReturn();
//
//        // Extract the response status and body
//        String content = result.getResponse().getContentAsString();
//        int status = result.getResponse().getStatus();
//
//        // Verify the GreetingService.delete method was invoked once
//        verify(greetingService, times(1)).delete(id);
//
//        // Perform standard JUnit assertions on the test results
//        Assert.assertEquals("failure - expected HTTP status 204", 204, status);
//        Assert.assertTrue("failure - expected HTTP response body to be empty",
//                content.trim().length() == 0);
//
//    }
//
//    private Collection<Greeting> getEntityListStubData() {
//        Collection<Greeting> list = new ArrayList<Greeting>();
//        list.add(getEntityStubData());
//        return list;
//    }
//
//    private Greeting getEntityStubData() {
//        Greeting entity = new Greeting();
//        entity.setId(1L);
//        entity.setText("hello");
//        return entity;
//    }
}