package com.example.demo;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MainController {
	
  public static ArrayList<Person> listOfPeople;
  public static String newLine = "<br/>";

  @RequestMapping("/HelloWorld")
  @ResponseBody
  public String index() {
    return "Hello World!!!";
  }
  
  @RequestMapping("/menu")
  @ResponseBody
  public String menu() {
	  if(listOfPeople == null) {
		  listOfPeople = new ArrayList<Person>();
	  }
	  
	  StringBuilder sb = new StringBuilder("In order to use this application, simply enter one of the following URLs into your browser to perform the corresponding action:" + newLine + newLine +
			    "To visit this menu, enter: http://localhost:8080/menu" + newLine + 
			    "To add a new person, enter: http://localhost:8080/addPersonNamed/Marc2" + newLine  + 
			    "To update a persons name, enter their index and updated name: http://localhost:8080/updatePersonIndex/0/MarcoPolo" + newLine + 
			    "To remove a person from the list, enter their index with this link: http://localhost:8080/removePersonIndex/0/" + newLine);
	  
	  sb.append(newLine + "List of people: " + newLine);
	  
	  int indexOf = 0;
	  for (Person person : listOfPeople) {
		  sb.append("Index: " + indexOf + ", Details: " +person.toString() + "" + newLine);
		  indexOf++;
	  }
	  

    
	  return sb.toString();
  }
  
  
  @RequestMapping("/addPersonNamed/{name}")
  @ResponseBody
  public void addPerson(@PathVariable String name) {
	  if(listOfPeople == null) {
		  listOfPeople = new ArrayList<Person>();
	  }
	  
	Person newPerson = new Person();
	newPerson.setName(name);
	listOfPeople.add(newPerson);
  }
  
  @RequestMapping("/removePersonIndex/{index}")
  @ResponseBody
  public void removePerson(@PathVariable int index) {
	  if(listOfPeople != null && listOfPeople.get(index) != null) {
		  listOfPeople.remove(index);
	  }
	  else {
		  return;
	  }
  }
  
  
  @RequestMapping("/updatePersonIndex/{index}/{name}")
  @ResponseBody
  public void updatePerson(@PathVariable int index, @PathVariable String name) {
	  if(listOfPeople != null && listOfPeople.get(index) != null) {
		  listOfPeople.get(index).setName(name);
	  }
	  else {
		  return;
	  }
  }

}
