package com.cts.sbcrud.test;



import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.time.LocalDate;
import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cts.sbcrud.api.EmployeeAPI;
import com.cts.sbcrud.model.Department;
import com.cts.sbcrud.model.Employee;
import com.cts.sbcrud.service.EmployeeService;

import static org.mockito.BDDMockito.*;


@SuppressWarnings("unused")
@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeAPI.class)
public class EmployeeAPIUnitTest {
	@Autowired
	private MockMvc mvc;
	@MockBean
	private EmployeeService service;
	@Test
	public void whendindallthenreturnjsonarray() throws Exception{
		Employee emp=new Employee("gorle","sunny",40000,Department.DEVELOPMENT,"7739322210","gorleshanmukh@gmail.com",LocalDate.now());
		List<Employee> allEmployees = Arrays.asList(emp);
		
		given (service.findAll()).willReturn(allEmployees);
		
		mvc.perform(get("/emps").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$",hasSize(1)))
		.andExpect(jsonPath("$[0].mobileNumber",is(emp.getMobileNumber())));
		
	}
}
