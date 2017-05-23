package zehrakent;

import zehrakent.tightlycoupled.Evidence;
import zehrakent.tightlycoupled.EvidenceBox;
import zehrakent.tightlycoupled.Policeman;

/**
 * 
 * @author Zehra Punjwani and Kent Millamena
 *
 */

public class Main {

	/**
	 * 
	 * The Main Class serve to help run the program, hoever the other Classes are not depended on the Main.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//creating instances of the evidence materials, i.e. in this case Clothing Fibre and Tire Marks.
		//Giving the instances the paremeters it needs i.e. the string name. 
		Evidence clothingFibre = new Evidence("Clothing Fibre");
		Evidence tireMarks = new Evidence("Tire Marks");
		
		//System.out.println prints the previous created instances to the screen.
		System.out.println(clothingFibre + "\n" + tireMarks);
		
		//Creating the instance of the evidencebox and giving it the paremeters that it needs. 
		//The caseNumber and CaseName.		
		EvidenceBox evidenceBox = new EvidenceBox(2005000381, "s.aVERY");
		
		//Adding both all evidences to the evidencebox		
		evidenceBox.addEvidence(clothingFibre);
		evidenceBox.addEvidence(tireMarks);
		
		//Creating the instances of two Policemen with the names J. Lenk and A. Colborn
		Policeman jlenkPM = new Policeman("J.Lenk");
		Policeman acolbornPM = new Policeman("A.Colborn");

		//Making the Policeman object that represents J. Lenk take evidenceBox	
		jlenkPM.takes(evidenceBox);

		//Print evidenceBox to the console
		System.out.println(evidenceBox);

		//giving a Policeman the ability to get all pieces of evidence of a certain type from a box in their possession with a certain case number.
		//jlenkPM.get("dna", 2005000381);		
		jlenkPM.getEvidence("DNA", 2005000381);
	}
}
