package zehrakent.tightlycoupled;

import zehrakent.tightlycoupled.Evidence;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/**
 * 
 * @author Zehra Punjwani and Kent Millamena
 *
 */
public class EvidenceBox {
	//Declaring fields for caseNumber and caseName and ArrayList<Evidence> evidence
	private int caseNumber;
	private String caseName;
	private ArrayList<Evidence> evidence;
	
	/**
	 * 
	 * @param caseNumber - param one
	 * @param caseName  - param two
	 */
	public EvidenceBox(Integer caseNumber, String caseName){ //Constructor for EvidenceBox with parameters caseNumber (type type integer) and caseName (data type String)
		this.caseNumber = caseNumber; //Initialising the Varible caseNumber
		this.caseName = caseName;	//Initialising the Varible caseName
		evidence = new ArrayList<Evidence>(); //Creating an instance of a new ArrayList<Evidence> and storing it in the varible evidence
	}
	
	/**
	 * 
	 * Obtain the case Number
	 * 
	 * @return int
	 */
	public int getCaseNumber(){ //getCaseNumber() method that returns caseNumber a int Data type
		return caseNumber;
	}
	
	/**
	 * 
	 * Obtain the case Name
	 * 
	 * @return String
	 */
	public String getCaseName(){ //getCaseName() method that returns caseName a string Data type
		return caseName;
	}
	
	/**
	 * 
	 * add type to evidence ArrayList
	 * 
	 * @param type
	 */
	public void addEvidence(Evidence type) { //addEvidence Method with parameter type which is a type of evidence
		evidence.add(type);   // takes type and adds it to the evidence ArrayList
	}
	
	/**
	 * 
	 * extract a certain evidence from an ArrayList of Evidence
	 * 
	 * @return ArrayList
	 */
	public ArrayList<Evidence> extract(){ //Extract() method that returns evidence(return value ArrayList<Evidence> data type)
		return evidence;
	}
	
	/**
	 * 
	 * Display each type of Evidence currently in the box on a new line in the console of the IDE. 
	 * If there are multiple instances of the same type of evidence in a box, then the output line for that type is 
	 * Nx type, where N is the number of occurrences of that type.
	 * 
	 * @return String
	 */
	public String display(){ //Display method with the return type String 
		ArrayList<String> list = new ArrayList<String>(); //Creating an instance of new ArrayList<String> and storing it in a variable 'list' of the same Data type
		for (Evidence i : evidence) { //for each element i while iterating through the ArrayList evidence
			list.add(i.getEvidenceType()); //add i.getEvidenceType() to the 'list' 
		}
		Set<String> uniqueSet = new HashSet<String>(list); //takes list has a parameter in a new instance of HashSet<String> and storing it in the variable uniqueSet with the same data type
		for (String i : uniqueSet) { //for each element i while iterating through the uniquesSet
			System.out.println(Collections.frequency(list, i) + "x " + i); //print the number of occurances of i in the list. along with the name of i
		}
		return ""; //return empty string
	}
	
	public String toString(){ //toString() method that Overrides. At every instance of type the name will be printed
		return display();   // instead of the memory Address.
	}
}
