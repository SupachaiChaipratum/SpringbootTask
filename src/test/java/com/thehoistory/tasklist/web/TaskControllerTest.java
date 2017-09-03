package com.thehoistory.tasklist.web;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.thehoistory.tasklist.TaskApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TaskApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TaskControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;



	@Before
	public  void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}



	@Test
	public void addTaskList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/task/")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{  \"description\" : \"Learn Java\", \"pending\" : \"false\" }")
				.accept(MediaType.APPLICATION_JSON)).andDo(print());

		mockMvc.perform(MockMvcRequestBuilders.post("/task/")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{  \"description\" : \"Learn spring\", \"pending\" : \"false\" }")
				.accept(MediaType.APPLICATION_JSON));

		mockMvc.perform(MockMvcRequestBuilders.post("/task/")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{  \"description\" : \"Learn paly\", \"pending\" : \"false\" }")
				.accept(MediaType.APPLICATION_JSON));

		mockMvc.perform(MockMvcRequestBuilders.post("/task/")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{  \"description\" : \"Learn scala\", \"pending\" : \"false\" }")
				.accept(MediaType.APPLICATION_JSON));

		mockMvc.perform(MockMvcRequestBuilders.get("/tasks").accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(jsonPath("$", hasSize(4)));

	}


	
	@Test
	public void verifyTaskById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/task/3").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.description").exists())
		.andExpect(jsonPath("$.pending").exists())
		.andExpect(jsonPath("$.id").value(3))
		.andExpect(jsonPath("$.description").value("Learn paly"))
		.andExpect(jsonPath("$.pending").value(false));

	}
	
	@Test
	public void verifyInvalidTaskArgument() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/task/f").accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.errorCode").value(400))
			.andExpect(jsonPath("$.message").value("The request could not be understood by the server due to malformed syntax."));

	}
	
	@Test
	public void verifyInvalidTaskId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/task/0").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("Task doesn´t exist"));

	}
	
	@Test
	public void verifyNullTask() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/task/6").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("Task doesn´t exist"));

	}
	
	@Test
	public void verifyDeleteTask() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/task").accept(MediaType.APPLICATION_JSON))
				.andDo(print());

		mockMvc.perform(MockMvcRequestBuilders.delete("/task/4").accept(MediaType.APPLICATION_JSON))
				.andDo(print())
		.andExpect(jsonPath("$.status").value(200))
		.andExpect(jsonPath("$.message").value("Task has been deleted"));

	}
	
	@Test
	public void verifyInvalidTaskIdToDelete() throws Exception {


		mockMvc.perform(MockMvcRequestBuilders.delete("/task/9").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("Task to delete doesn´t exist"))
		.andDo(print());
	}
	
	
	@Test
	public void verifySaveTask() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/task/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"description\" : \"New Task Sample\", \"pending\" : \"false\" }")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.description").exists())
		.andExpect(jsonPath("$.pending").exists())
		.andExpect(jsonPath("$.description").value("New Task Sample"))
		.andExpect(jsonPath("$.pending").value(false));

	}
	
	@Test
	public void verifyMalformedSaveTask() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/task/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{ \"id\": \"8\", \"description\" : \"New Task Sample\", \"pending\" : \"false\" }")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("can not create new task"));

	}
	
	@Test
	public void verifyUpdateTask() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.patch("/task/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{ \"id\": \"1\", \"description\" : \"New Task Text\", \"pending\" : \"false\" }")
        .accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.description").exists())
		.andExpect(jsonPath("$.pending").exists())
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.description").value("New Task Text"))
		.andExpect(jsonPath("$.pending").value(false));
		//.andDo(print());
	}
	
	@Test
	public void verifyInvalidTaskUpdate() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.patch("/task/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{ \"idd\": \"8\", \"text\" : \"New Task Sample\", \"pending\" : \"false\" }")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("Task update doesn´t exist"));

	}




}
