package zehrakent.tightlycoupled;

import zehrakent.tightlycoupled.Evidence;
import zehrakent.tightlycoupled.EvidenceBox;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 
 * @author Zehra Punjwani and Kent Millamena
 *
 */
public class Policeman { //Policeman Class
	private String name; //declaring the fields which are private
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
	 * Give a Policeman the ability to get all pieces of evidence of a certain type from a box in their 
	 * possession with a certain case number. - Directly accessing the Evidence Class to (Coupling Classes)
	 * 
	 * @param type
	 * @param caseNumber
	 * @return boolean
	 */
	public boolean getEvidence(String type, int caseNumber){ //getEvidence() method with parameter type (data type String) and caseNumber (Data type Integer) - method return type boolean
		List<Evidence> desiredEvidenceType = new ArrayList<Evidence>(); //create a new instance of ArrayList<Evidence> and store it in a variable called desiredEvidenceType with the same sata type
		for (EvidenceBox evidenceBox : evidenceBoxesInPossession){ //then for every evidenceBox (Data type Evidencebox) in eveidenceBoxesInPossession
			if (evidenceBox.getCaseNumber() == caseNumber){ //if caseNumber (this) is equal to the evidencebox CaseNumber 
				for (Evidence item : evidenceBox.extract()){ // extract the item (data type Evidence - cross refernce Evidence Class) in evidenceBox 
					if (item.getEvidenceType() == type){ //if type (this) is equal to the item.getEvidenceType()...
						desiredEvidenceType.add(item); // then add the item to the desiredEvidenceType
					}
				}
				System.out.println("Pieces of " + type + " Evidence Box Number " + caseNumber + " found by " + name + ": " + desiredEvidenceType); //print the type and caseNumber along with the ArrayList desiredEvidencetype
			}
			return false; //else return false
		}
		return false; //else return false
	}
}
