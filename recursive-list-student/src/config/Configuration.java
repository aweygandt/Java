package config;

import structure.ListInterface;
import List.List;

/**
 * This class acts as a configuration file which tells the testing framework
 * which implementation you want us to use when we grade your assignment.
 * 
 * @author jcollard
 * 
 */
public class Configuration {

	
	/**
	 * Your 8 digit University of Massachusetts Identification Number. This
	 * is the value on your UMass ID Card. We need this to associate your submission
	 * with your moodle account when we submit grades
	 */
	public static final String STUDENT_ID_NUMBER = "27938948";
	
	/**
	 * Returns a new instance of the {@link ListInterface} that you want to be
	 * graded.
	 */
	public static <T> ListInterface<T> getListInterface(){
		return new List<T>();
	}

}
