package com.codegym.todo;

import static javafx.beans.binding.Bindings.when;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.codegym.todo.model.Status;
import com.codegym.todo.model.Work;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
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

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TodoApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TodoApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }

    @Test
    public void verifyAllToDoList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/todo?page=0&size=3&sort=workName").accept(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    public void verifyToDoById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/todo/12").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.workName").exists())
                .andDo(print());
    }

    @Test
    public void verifyDeleteToDo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/todo/15").accept(MediaType.APPLICATION_JSON))
                .andDo(print());
    }
    @Test
    public void verifyInvalidToDoIdToDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/todo/13").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value(404))
                .andDo(print());
    }

    @Test
    public void verifyUpdateToDo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/todo/14")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{'workName' : 'Java', 'startDate': '2019-11-15','endDate': '2019-11-15'}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
    }






}
