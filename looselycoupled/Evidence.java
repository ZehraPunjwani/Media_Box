package zehrakent.looselycoupled;
/**
 * 
 * @author Zehra Punjwani and Kent Millamena
 *
 */
public class Evidence {
	private String type;
	/**
	 * 
	 * Constructor
	 * 
	 * @param type
	 */
	public Evidence(String type){ // Constructor for Evidence with the parameter 'type' which has a data type String
		this.type = type;    //Initialising the Variable
	}
	
	/**
	 * 
	 * Gets the evidence 
	 * 
	 * @return 
	 */
	public String getEvidenceType(){ //getEvidenceType method that returns a String type
		return type;
	}
	

	public String toString(){ //toString() method that Overrides. At every instance of type the name will be printed
		return type;        // instead of the memory Address.
	}
}