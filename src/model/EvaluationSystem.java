package model;

import java.util.ArrayList;

public class EvaluationSystem {
	
	//ATTRIBUTES
	
	Teacher firstTeacher;
	ArrayList<Student> students;
	/**
	 * 
	 */
	public EvaluationSystem() {
		firstTeacher = null;
		students = new ArrayList<Student>();
	}
	/**
	 * @return the firstTeacher
	 */
	public Teacher getFirstTeacher() {
		return firstTeacher;
	}
	
	/**
	 * @param firstTeacher the firstTeacher to set
	 */
	public void setFirstTeacher(Teacher firstTeacher) {
		this.firstTeacher = firstTeacher;
	}
	/**
	 * @return the students
	 */
	public ArrayList<Student> getStudents() {
		return students;
	}
	
	/**
	 * @param students the students to set
	 */
	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}
	
	//MANAGEMENT TEACHERS
	
	//Add Teacher Recursively
	
	/**
	* Add a new teacher to the linked list of teachers if the list is empty and the teacher to add is the first.<br>
	* <b>post:</b> Has been added a new teacher to the system if and only if the new teacher did not exist previously. 
	* @param name The name of the new teacher.
	* @param lastName The lastName of the new teacher.
	* @param email The e-mail of the new teacher.
	* @param code The institutional code assigned to the new teacher.
	* @param fullTime The type of the new teacher (boolean value, full time or half-time).
	*/
	public void addTeacher(String name, String lastName, String email, String code, boolean fullTime) {
		Teacher newTeacher = new Teacher(name, lastName, email, code, fullTime);
		if(firstTeacher == null) {
			firstTeacher = newTeacher;
		}else {
			addTeacher(firstTeacher, newTeacher);
		}
	}
	
	/**
	* Add a new teacher to the linked list of teachers recursively.<br>
	* <b>post:</b> Has been added a new teacher to the system if and only if the new teacher did not exist previously. 
	* @param newTeacher The new teacher to be added. current != null.
	* @param current The linked list teacher which will be traverse.
	*/
	private void addTeacher(Teacher current, Teacher newTeacher) {
		if(current.getNext() == null) {
			current.setNext(newTeacher);
		}else {
			addTeacher(current.getNext(), newTeacher);
		}
	}
	
	
}