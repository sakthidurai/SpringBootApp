package com.app.main;

import java.util.ArrayList;
import java.util.List;

import com.app.vo.Student;

public class ComparatorMain {

	public static void main(String[] args) {
		Student student1 = new Student("1","ryan","Plano");
		Student student2 = new Student("2","Anna","Irving");
		Student student3 = new Student("3","Sam","Dallas");
		
		List<Student> studentList = new ArrayList<Student>();
		studentList.add(student1);
		studentList.add(student2);
		studentList.add(student3);
		//we are using lambda inside arraylist to sort a complex object.
		//Don't get confused with the compareToIgnoreCase, it is a string method.
		studentList.sort((a,b)->a.getName().compareToIgnoreCase(b.getName()));
		for(Student student : studentList) {
			System.out.println("Values are "+student.toString());
			
		}
		

	}

}
