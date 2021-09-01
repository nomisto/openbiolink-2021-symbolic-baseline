package de.unima.ki.anyburl.util;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

import de.unima.ki.anyburl.data.Triple;
import de.unima.ki.anyburl.data.TripleSet;
import de.unima.ki.anyburl.io.RuleReader;
import de.unima.ki.anyburl.structure.Rule;

public class XX {

	/**
	 * Can be deleted after problem has been fixed.
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		
		Random rand = new Random();
		
		TripleSet ts1 = new TripleSet("data/WN18RR/test.txt");
		TripleSet ts2 = new TripleSet("data/WN18RR/valid.txt");
		TripleSet ts3 = new TripleSet("data/WN18RR/train.txt");
	
		//System.out.println(ts.size());
		
		TripleSet tsAll = new TripleSet();
		
		tsAll.addTripleSet(ts1);
		tsAll.addTripleSet(ts2);
		tsAll.addTripleSet(ts3);
		
		TripleSet tsTrain07 = new TripleSet();
		TripleSet tsTest03 = new TripleSet();
		
		for (Triple t : tsAll.getTriples()) {
			if (rand.nextDouble() < 0.3) {
				tsTest03.addTriple(t);
			}
			else {
				tsTrain07.addTriple(t);
			}
		}

		tsTest03.write("data/WN18RR/test03.txt");
		tsTrain07.write("data/WN18RR/train07.txt");	

	}

}
