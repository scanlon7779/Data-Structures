package edu.ncsu.csc316.dsa.data;

/**
 * A student is comparable and identifiable.
 * Students have a first name, last name, id number, 
 * number of credit hours, gpa, and unityID.
 * 
 * @author Dr. King
 *
 */
public class Student implements Comparable<Student>, Identifiable {

	/** first name of the student */
	private String first;
	
	/** last name of the student */
	private String last;
	
	/** id of the student */
	private int id;
	
	/** credit hours of the student */
	private int creditHours;
	
	/** gpa of the student */
	private double gpa;
	
	/** unity id of the student */
	private String unityID;
	
	
	
	/**
	 * Constructor for the student class
	 * @param first students first name
	 * @param last students last name
	 * @param id students id
	 * @param creditHours students credit hours
	 * @param gpa students gpa
	 * @param unityID students unity id
	 */
	public Student(String first, String last, int id, int creditHours, double gpa, String unityID) {
		this.first = first;
		this.last = last;
		this.id = id;
		this.creditHours = creditHours;
		this.gpa = gpa;
		this.unityID = unityID;
	}

	


	/**
	 * gets the first name
	 * @return the first
	 */
	public String getFirst() {
		return first;
	}




	/**
	 * sets the first name
	 * @param first the first to set
	 */
	public void setFirst(String first) {
		this.first = first;
	}




	/**
	 * gets the last name
	 * @return the last
	 */
	public String getLast() {
		return last;
	}




	/**
	 * sets the last name
	 * @param last the last to set
	 */
	public void setLast(String last) {
		this.last = last;
	}




	/**
	 * gets the id
	 * @return the id
	 */
	public int getId() {
		return id;
	}




	/**
	 * sets the id
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}




	/**
	 * gets the credit hours
	 * @return the creditHours
	 */
	public int getCreditHours() {
		return creditHours;
	}




	/**
	 * sets the credit hours
	 * @param creditHours the creditHours to set
	 */
	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}




	/**
	 * gets the gpa
	 * @return the gpa
	 */
	public double getGpa() {
		return gpa;
	}




	/**
	 * sets the gpa
	 * @param gpa the gpa to set
	 */
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}




	/**
	 * gets the unity id
	 * @return the unityID
	 */
	public String getUnityID() {
		return unityID;
	}




	/**
	 * sets the unity id
	 * @param unityID the unityID to set
	 */
	public void setUnityID(String unityID) {
		this.unityID = unityID;
	}



	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + creditHours;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		long temp;
		temp = Double.doubleToLongBits(gpa);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		result = prime * result + ((unityID == null) ? 0 : unityID.hashCode());
		return result;
	}




	@Override
	public boolean equals(Object obj) {
		return obj instanceof Student && first.equals(((Student) obj).first) && last.equals(((Student) obj).last) 
				&& unityID.equals(((Student) obj).unityID);
			
	}


	


	@Override
	public String toString() {
		return first + "," + last + "," + id + "," + creditHours + "," + gpa + "," + unityID;
	}




	@Override
	public int compareTo(Student o) {
		if (last.compareTo(o.last) == 0) {
			if(first.compareTo(o.first) == 0) {
				return unityID.compareTo(o.unityID);
			} else {
				return first.compareTo(o.first);
			}
		} else {
			return last.compareTo(o.last);
		}
	}

}