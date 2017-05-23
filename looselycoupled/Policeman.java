package zehrakent.looselycoupled;

import zehrakent.looselycoupled.EvidenceBox;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 * @author Zehra Punjwani and Kent Millamena
 *
 */
public class Policeman {
	private String name;
	private Collection<EvidenceBox>evidenceBoxesInPossession;
	
	/**
	 * 
	 * @param name
	 */
	public Policeman(String name){ //Creating a constructor with the paramater String
		this.name = name; //Initiling the Varible and storing them
		evidenceBoxesInPossession = new ArrayList<EvidenceBox>();
	}
	
	/**
	 * 
	 * add evidenceBox to the evidenceBoxesInPossession
	 * 
	 * @param evidenceBox
	 */
	public void takes(EvidenceBox evidenceBox){ //Takes() method that takes in the parameter evidencebox (type EvidenceBox)
		evidenceBoxesInPossession.add(evidenceBox); //and adds evidencebox to the evidenceBoxesInPossion ArrayList<EvidenceBox>
	}
	
	/**
	 * 
	 * give a Policeman the ability to get all pieces of evidence of a certain type from a box in their 
	 * possession with a certain case number. - delegating EvidenceBox class to access the Evidence Class to obtain type (Decoupling Classes)
	 * 
	 * @param type
	 * @param caseNumber
	 * @return
	 */
	public String getEvidence(String type, int caseNumber){ //getEvidence() method with parameter type (data type String) and caseNumber (Data type Integer) - method return type String
		ArrayList<Evidence> desiredEvidenceType = new ArrayList<Evidence>(); //create a new instance of ArrayList<Evidence> and store it in a variable called desiredEvidenceType with the same sata type
		for (EvidenceBox evidenceBox : evidenceBoxesInPossession){ //then for every evidenceBox (Data type Evidencebox) in eveidenceBoxesInPossession
			if (evidenceBox.getCaseNumber() == caseNumber){ //if caseNumber (this) is equal to the evidencebox CaseNumber 
				System.out.println("Pieces of " + type + " Evidence Box Number " + caseNumber + " found by " + name + ": " + EvidenceBox.getEvidenceByType(type)); //print the type and caseNumber along with the ArrayList desiredEvidencetype
			}
			return null; //Else return null
		}
		return null; //Else return null
	}
}
