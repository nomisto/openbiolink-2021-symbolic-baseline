package de.unima.ki.anyburl.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import de.unima.ki.anyburl.io.RuleReader;
import de.unima.ki.anyburl.structure.Rule;
import de.unima.ki.anyburl.structure.RuleAcyclic1;
import de.unima.ki.anyburl.structure.RuleAcyclic2;

public class Splitter {

	public static void main(String[] args) throws IOException {
		String filepath = "";
		if (args.length == 1) filepath = args[0];
		else {
			filepath = "exp/april/fb237-rules-313-run2-100";
			
		}
		RuleReader rr = new RuleReader();
		LinkedList<Rule> rules = rr.read(filepath);
		
		
		String[] types = new String[]{"wo-ac1L1", "wo-ac1L2", "wo-ac2L1", "wo-ac2L2", "wo-acX"};
		
		for (String type : types) {

			PrintWriter pw = new PrintWriter(filepath + "-" + type);
			for (Rule r : rules) {
				if (type.equals("wo-ac1L1")) {
					if (r.bodysize() == 1 && r instanceof RuleAcyclic1) continue;
				}
				if (type.equals("wo-ac1L2") || type.equals("wo-acX")) {
					if (r.bodysize() == 2 && r instanceof RuleAcyclic1) continue;
				}
				if (type.equals("wo-ac2L1") || type.equals("wo-acX")) {
					if (r.bodysize() == 1 && r instanceof RuleAcyclic2) continue;
				}
				if (type.equals("wo-ac2L2") || type.equals("wo-acX")) {
					if (r.bodysize() >= 2 && r instanceof RuleAcyclic2) continue;
				}
				pw.println(r);
			}
			pw.flush();
			pw.close();
			System.out.println("created rule file of type: " + type);
		}
		System.out.println("done.");


	}

}
