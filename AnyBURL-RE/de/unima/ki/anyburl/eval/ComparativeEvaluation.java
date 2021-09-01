package de.unima.ki.anyburl.eval;

import java.io.IOException;
import java.util.ArrayList;

import de.unima.ki.anyburl.data.Triple;
import de.unima.ki.anyburl.data.TripleSet;

public class ComparativeEvaluation {
	
	public static String[] categories = new String[]{"Symmetry", "Equivalence", "Subsumption", "Path", "Not covered"};
	
	// WN18 WN18RR FB15 FB15-237 YAGO NELL995
	public static String target = "OLPBENCH";
	
	// 0 = ALL, 1 = HEAD ONLY, 2 = TAIL ONLY
	public static int ALL_HEAD_TAIL = 0;
	
	public static void main(String[] args) throws IOException {
		
		TripleSet trainingSet = null, validationSet = null, testSet = null;
		AlternativeMentions am = null;
		GoldStandard gold = null;
		
		if (target.equals("WN18")) {
			trainingSet = new TripleSet("data/WN18/train.txt");
			validationSet = new TripleSet("data/WN18/valid.txt");
			testSet = new TripleSet("data/WN18/test.txt");
			gold = new GoldStandard("data/WN18/gold.txt");
		}
		if (target.equals("FB15")) {
			trainingSet = new TripleSet("data/FB15k/train.txt");
			validationSet = new TripleSet("data/FB15k/valid.txt");
			testSet = new TripleSet("data/FB15k/test.txt");
			gold = new GoldStandard("data/FB15k/gold.txt");
		}
		
		if (target.equals("FB15-237")) {
			trainingSet =   new TripleSet("data/FB15-237/train.txt");
			validationSet = new TripleSet("data/FB15-237/valid.txt");
			testSet =       new TripleSet("data/FB15-237/test.txt");
			gold =          new GoldStandard("data/FB15-237/gold.txt");
		}
		
		if (target.equals("WN18RR")) {
			trainingSet = new TripleSet("data/WN18RR/train.txt");
			validationSet = new TripleSet("data/WN18RR/valid.txt");
			testSet = new TripleSet("data/WN18RR/test.txt");
			// gold = new GoldStandard("../RuleN18/data/WN18RR/gold.txt");
		}
		if (target.equals("YAGO")) {
			trainingSet = new TripleSet("data/YAGO03-10/train.txt");
			validationSet = new TripleSet("data/YAGO03-10/valid.txt");
			testSet = new TripleSet("data/YAGO03-10/test.txt");
			gold = null;
		}
		if (target.equals("NELL995")) {
			trainingSet   = new TripleSet("data/NELL995/train.txt");
			validationSet = new TripleSet("data/NELL995/valid.txt");
			testSet       = new TripleSet("data/NELL995/test.txt");
			gold = null;
		}
		if (target.equals("DB500")) {
			trainingSet = new TripleSet("data/DB500/train.txt");
			validationSet = new TripleSet("data/DB500/valid.txt");
			testSet = new TripleSet("data/DB500/test.txt");
			gold = null;
		}
		if (target.equals("ASS")) {
			trainingSet   = new TripleSet("experiments/SemAssocs/data/empty.txt");
			validationSet = new TripleSet("experiments/SemAssocs/data/empty.txt");
			testSet       = new TripleSet("experiments/SemAssocs/data/assoc_test.nt");
			gold = null;
		}
		
		if (target.equals("MOB")) {
			trainingSet   = new TripleSet("data/mob/train.txt");
			validationSet = new TripleSet("data/mob/valid.txt");
			testSet       = new TripleSet("data/mob/test.txt");
			gold = null;
		}
		
		if (target.equals("WD")) {
			trainingSet = new TripleSet("data/WIKIDATA/empty.txt");
			validationSet = new TripleSet("data/WIKIDATA/empty.txt");
			testSet = new TripleSet("data/WIKIDATA/test.txt");
			gold = null;
		}
		
		if (target.equals("OLPBENCH")) {
			trainingSet = new TripleSet("data/OLPBENCH/empty.txt");
			validationSet = new TripleSet("data/OLPBENCH/empty.txt");
			testSet = new TripleSet("data/OLPBENCH/test_data.txt");
			am = new AlternativeMentions("data/OLPBENCH/test_data.txt");
			
			gold = null;
		}
		if (target.equals("OLPBENCH2")) {
			trainingSet = new TripleSet("data/OLPBENCH/empty.txt");
			validationSet = new TripleSet("data/OLPBENCH/empty.txt");
			testSet = new TripleSet("data/OLPBENCH/validation_data_linked_mention.txt");
			gold = null;
		}		
		
		
		ResultSet[] results = null;
		
		boolean html = false;
		if (target.equals("ASS")) {

			
			results = new ResultSet[]{
					
					new ResultSet("RuleN", "experiments/SemAssocs/predictions/p12-s200.txt", true, 100),
					new ResultSet("RuleN", "experiments/SemAssocs/predictions/p12-s200-mul.txt", true, 100),
					new ResultSet("RuleN", "experiments/SemAssocs/predictions/p123-s200.txt", true, 100),
					new ResultSet("RuleN", "experiments/SemAssocs/predictions/p123-s500.txt", true, 100),

					
					
			};			
		}
		
		if (target.equals("WD")) {

			
			results = new ResultSet[]{
					
					new ResultSet("500      ","exp/february/final/rg/wikidata-rt-c2a1g1-p2s3-predictionsZ-AVG-500", true, 10),
					new ResultSet("1000     ","exp/february/final/rg/wikidata-rt-c2a1g1-p2s3-predictionsZ-AVG-1000", true, 10),
					new ResultSet("5000     ","exp/february/final/rg/wikidata-rt-c2a1g1-p2s3-predictionsZ-AVG-5000", true, 10),
					new ResultSet("10000    ","exp/february/final/rg/wikidata-rt-c2a1g1-p2s3-predictionsZ-AVG-10000", true, 10),
					
			};			
		}
		
		if (target.equals("MOB")) {

			
			results = new ResultSet[]{
					
					new ResultSet("RuleN", "exp/summer/mob/mob-rules-pred-100", true, 100),
				
			};	
		}

		
		
		if (target.equals("WN18")) {

			
			results = new ResultSet[]{

					new ResultSet("100    5    ", "exp/february/final/free/wn18-c5a1g1-p2s3-preds-100", true, 10),
					new ResultSet("500    5    ", "exp/february/final/free/wn18-c5a1g1-p2s3-preds-500", true, 10),
					new ResultSet("1000   5    ", "exp/february/final/free/wn18-c5a1g1-p2s3-preds-1000", true, 10),
					new ResultSet("5000   5    ", "exp/february/final/free/wn18-c5a1g1-p2s3-preds-5000", true, 10),
					new ResultSet("10000  5    ", "exp/february/final/free/wn18-c5a1g1-p2s3-preds-10000", true, 10),

					
			};	
		}
		
		if (target.equals("WN18RR")) {

			
			results = new ResultSet[]{
					new ResultSet("311 with 100", "exp/june/preds/wn18rr-311-100", true, 10),
					new ResultSet("311 out  100", "exp/june/preds/wn18rr-311-xac2-100", true, 10),
					null,
					new ResultSet("511 with 100", "exp/june/preds/wn18rr-511-100", true, 10),
					new ResultSet("511 out  100", "exp/june/preds/wn18rr-511-xac2-100", true, 10),					



					

			};	
		}
		
		if (target.equals("YAGO")) {

	
			
			results = new ResultSet[]{
					
					
					new ResultSet("311      ", "exp/april/server/yago-311-predictions-rand-5000", true, 10),
					new ResultSet("311-wo   ", "exp/april/server/yago-311-predictions-rand-5000-wo-ac2L1", true, 10),
					null,
					new ResultSet("all      ", "exp/april/server/yago-312-predictions-rand-5000", true, 10),
					new ResultSet("wo-acX   ", "exp/april/server/yago-312-predictions-rand-5000-wo-acX", true, 10),
					new ResultSet("wo-ac1L1 ", "exp/april/server/yago-312-predictions-rand-5000-wo-ac1L1", true, 10),
					new ResultSet("wo-ac1L2 ", "exp/april/server/yago-312-predictions-rand-5000-wo-ac1L2", true, 10),
					new ResultSet("wo-ac2L1 ", "exp/april/server/yago-312-predictions-rand-5000-wo-ac2L1", true, 10),
					new ResultSet("wo-ac2L2 ", "exp/april/server/yago-312-predictions-rand-5000-wo-ac2L2", true, 10)
										


			};
			
			
			
		}
		
	if (target.equals("NELL995")) {

			results = new ResultSet[] {

		
					new ResultSet("200 edisOn1   ", "exp/february/temp/nell-predictions-311-edisOn1-200", true, 10),
					new ResultSet("200 edisOn2   ", "exp/february/temp/nell-predictions-311-edisOn2-200", true, 10),
					null,
					new ResultSet("200 edisOff1  ", "exp/february/temp/nell-predictions-311-edisOff1-200", true, 10),
					new ResultSet("200 edisOff2  ", "exp/february/temp/nell-predictions-311-edisOff2-200", true, 10),
					
			};
			
		}
		
	
		
		if (target.equals("DB500")) {

			results = new ResultSet[]{
					new ResultSet("100     ", "exp/january/reinforced/db500-predictions-100", true, 10),

				
			};
			
			
			
		}
		
		
		if (target.equals("FB15-237")) {
			
			results = new ResultSet[]{

					new ResultSet("311 with  100", "exp/june/preds/fb237-311-100", true, 10),
					new ResultSet("311 with  500", "exp/june/preds/fb237-311-500", true, 10),
					new ResultSet("311 with 1000", "exp/june/preds/fb237-311-1000", true, 10),
					null,
					new ResultSet("311 out   100", "exp/june/preds/fb237-311-xac2-100", true, 10),
					new ResultSet("311 out   500", "exp/june/preds/fb237-311-xac2-500", true, 10),
					new ResultSet("311 out  1000", "exp/june/preds/fb237-311-xac2-1000", true, 10),
					

			};
			
		}
		
		
		
		if (target.equals("FB15")) {

			
			
			results = new ResultSet[]{
					
					new ResultSet("311 with  100", "exp/june/preds/fb15-311-100", true, 10),
					new ResultSet("311 with  500", "exp/june/preds/fb15-311-500", true, 10),
					new ResultSet("311 with 1000", "exp/june/preds/fb15-311-1000", true, 10),
					null,
					new ResultSet("311 out   100", "exp/june/preds/fb15-311-xac2-100", true, 10),
					new ResultSet("311 out   500", "exp/june/preds/fb15-311-xac2-500", true, 10),
					new ResultSet("311 out  1000", "exp/june/preds/fb15-311-xac2-1000", true, 10),
					
					


			};
			
			
		}
		
		if (target.equals("OLPBENCH")) {

			results = new ResultSet[]{
					
					// new ResultSet("   100", "exp/july/olpbench/predictions-thorough-100", true, 10),
					// new ResultSet("   500", "exp/july/olpbench/predictions-thorough-500", true, 10),
					new ResultSet("  1000", "exp/july/olpbench/predictions-thorough-1000", true, 10),
					new ResultSet("  5000", "exp/july/olpbench/predictions-thorough-5000", true, 10),
					new ResultSet(" 10000", "exp/july/olpbench/predictions-thorough-10000", true, 10),
					null,
					new ResultSet("  1000", "exp/july/olpbench/predictions-c5-thorough-1000", true, 10),
					new ResultSet("  5000", "exp/july/olpbench/predictions-c5-thorough-5000", true, 10),
					new ResultSet(" 10000", "exp/july/olpbench/predictions-c5-thorough-10000", true, 10),
					new ResultSet(" 20000", "exp/july/olpbench/predictions-c5-thorough-20000", true, 10),
					new ResultSet(" 40000", "exp/july/olpbench/predictions-c5-thorough-40000", true, 10),

			};
			
		}
		
		
		if (target.equals("OLPBENCH2")) {

			results = new ResultSet[]{
					
					// new ResultSet("  5000", "exp/july/olpbench/predictions-vdlm-thorough-5000", true, 10),
					new ResultSet(" 10000", "exp/july/olpbench/predictions-vdlm-thorough-10000", true, 10),
			};
			
		}		
		
		
		
		
		HitsAtK hitsAtK = new HitsAtK();
		
		hitsAtK.addFilterTripleSet(trainingSet);
		hitsAtK.addFilterTripleSet(validationSet);
		hitsAtK.addFilterTripleSet(testSet); 
		
		if (am != null) hitsAtK.addAlternativeMentions(am);
		
		GoldStandard goldSymmetry    = null;	
		GoldStandard goldEquivalence = null;
		GoldStandard goldSubsumption = null;
		GoldStandard goldPath        = null;
		GoldStandard goldUncovered   = null;
		
		if (gold != null) {
			goldSymmetry    = gold.getSubset("Symmetry");	
			goldEquivalence = gold.getSubset("Equivalence");	
			goldSubsumption = gold.getSubset("Subsumption");	
			goldPath        = gold.getSubset("Path");	
			goldUncovered   = gold.getSubset("Not covered");	
		}
	

		
		for (ResultSet rs : results) {
			
			// symmetry
			
			if (rs == null) {
				System.out.println("\n------------------");
				continue;
			}
			if (gold == null) {
				computeScores(rs, testSet, hitsAtK);
				// System.out.println(hitsAtK);
				
				// computeScores(rs, testSet, hitsAtK);
				System.out.print(rs.getName() + "   " + hitsAtK.getHitsAtK(0) + "   " + hitsAtK.getHitsAtK(2) + "   " + hitsAtK.getHitsAtK(9) + "   " + hitsAtK.getApproxMRR());
				// hitsAtK.reset();
				hitsAtK.reset();
			}
			else if (html == true) {
				computeScores(rs, testSet, hitsAtK);
				System.out.print("<tr><td><span class=\"important\">" + rs.getName() + "</span></td><td>" + hitsAtK.getHitsAtK(0) + "</td> <td>" + hitsAtK.getHitsAtK(9) + "</td> <td></td> <td></td> <td></td></tr>");
				hitsAtK.reset();
			}
			
			else {
				computeScores(rs, gold, hitsAtK);
				// System.out.println(hitsAtK);
				System.out.print(rs.getName() + "   " + hitsAtK.getHitsAtK(0) + "   " + hitsAtK.getHitsAtK(9) + "   " + hitsAtK.getApproxMRR() + "   ");
				
				hitsAtK.reset();
			}
			/*
			
				computeScores(rs, gold, hitsAtK);
				System.out.print(rs.getName() + "   " + hitsAtK.getHitsAtK(0) + "   " + hitsAtK.getHitsAtK(9) + "   " + hitsAtK.getApproxMRR() + "   ");
				
				System.out.println(hitsAtK);
				hitsAtK.reset();

				
				// symmetry
				computeScores(rs, goldSymmetry, hitsAtK);
				System.out.print( hitsAtK.getHitsAtK(0) + "   " + hitsAtK.getHitsAtK(9) + "   ");
				hitsAtK.reset();
				
				
				// equivalence
				computeScores(rs, goldEquivalence, hitsAtK);
				System.out.print( hitsAtK.getHitsAtK(0) + "   " + hitsAtK.getHitsAtK(9) + "   ");
				hitsAtK.reset();
				// subsumption
				computeScores(rs, goldSubsumption, hitsAtK);
				System.out.print( hitsAtK.getHitsAtK(0) + "   " + hitsAtK.getHitsAtK(9) + "   ");
				hitsAtK.reset();
				// Path
				computeScores(rs, goldPath, hitsAtK);
				System.out.print( hitsAtK.getHitsAtK(0) + "   " + hitsAtK.getHitsAtK(9) + "   ");
				hitsAtK.reset();
				// not covered
				computeScores(rs, goldUncovered, hitsAtK);
				System.out.print( hitsAtK.getHitsAtK(0) + "   " + hitsAtK.getHitsAtK(9) + "");
				hitsAtK.reset();
				
			}
			*/
			
			
			System.out.println();
		}
		
		
		
		
		
		
	}


	private static void computeScores(ResultSet rs, GoldStandard gold, HitsAtK hitsAtK) {
		int counter = 21;
		for (String triple : gold.triples) {
			String[] tt = triple.split(" ");
			Triple t = new Triple(tt[0], tt[1], tt[2]);
			// System.out.print(t+ "\t");
			if (gold.getCategory(triple, true) != null) {
				counter++;
				ArrayList<String> cand = rs.getHeadCandidates(triple);
				// System.out.print(cand.size() + "\t");
				// String c = cand.size() > 0 ? cand.get(0) : "-";
				hitsAtK.evaluateHead(cand, t);
			}
			if (gold.getCategory(triple, false) != null) {
				counter++;
				ArrayList<String> cand = rs.getTailCandidates(triple);
				// System.out.print(cand.size() + "\t");
				// String c = cand.size() > 0 ? cand.get(0) : "-";
				hitsAtK.evaluateTail(cand, t);
				// if (rank == -1) System.out.println("NOT FOUND: " + triple);
			}
			// System.out.println();
		}
	}
	
	
	private static void computeScores(ResultSet rs, TripleSet gold, HitsAtK hitsAtK) {
		for (Triple t : gold.getTriples()) {
			// System.out.print(t+ "\t");
			
				if (!target.equals("ASS")) {
					ArrayList<String> cand1 = rs.getHeadCandidates(t.toString());
					// System.out.print(cand.size() + "\t");
					String c1 = cand1.size() > 0 ? cand1.get(0) : "-";
					// if (ALL_HEAD_TAIL == 0 || ALL_HEAD_TAIL == 1)
					hitsAtK.evaluateHead(cand1, t);
				}
			
				ArrayList<String> cand2 = rs.getTailCandidates(t.toString());
				// System.out.print(cand.size() + "\t");
				// String c2 = cand2.size() > 0 ? cand2.get(0) : "-";
				//if (ALL_HEAD_TAIL == 0 || ALL_HEAD_TAIL == 2)
				hitsAtK.evaluateTail(cand2, t);
			// System.out.println();
		}
	}
	
	
	private static void printAndMarkUnfoundTriples(ResultSet rs, GoldStandard gold, HitsAtK hitsAtK) {
		for (String triple : gold.triples) {
			String[] tt = triple.split(" ");
			Triple t = new Triple(tt[0], tt[1], tt[2]);
			if (gold.getCategory(triple, true) != null) {
				ArrayList<String> cand = rs.getHeadCandidates(triple);
				String c = cand.size() > 0 ? cand.get(0) : "-";
				int foundAt = hitsAtK.evaluateHead(cand, t);
				if (foundAt < 0) System.out.println(t.getHead() + " headX" + t.getRelation() + " " + t.getTail());
			}
			if (gold.getCategory(triple, false) != null) {
				ArrayList<String> cand = rs.getTailCandidates(triple);
				String c = cand.size() > 0 ? cand.get(0) : "-";
				int foundAt = hitsAtK.evaluateTail(cand, t);
				if (foundAt < 0) System.out.println(t.getHead() + " tailX" + t.getRelation() + " " + t.getTail());
			}
		}
	}
	
	
	private static void compareResultSets(ResultSet rs1, ResultSet rs2, GoldStandard gold, HitsAtK hitsAtK) {
		for (String triple : gold.triples) {
			String[] tt = triple.split(" ");
			Triple t = new Triple(tt[0], tt[1], tt[2]);
			if (gold.getCategory(triple, true) != null) {
				ArrayList<String> cand1 = rs1.getHeadCandidates(triple);
				ArrayList<String> cand2 = rs2.getHeadCandidates(triple);
				boolean foundBy1 = false;
				for (String c1 : cand1) {
					if (t.getHead().equals(c1)) foundBy1 = true;
				}
				boolean foundBy2 = false;
				for (String c2 : cand2) {
					if (t.getHead().equals(c2)) foundBy2 = true;
				}
				if (foundBy1 != foundBy2) {
					System.out.println("H " + rs1.getName() + "=" + foundBy1 + " " + rs2.getName() + "=" + foundBy2 + " " + triple);
				}
				
			}
			if (gold.getCategory(triple, false) != null) {
				ArrayList<String> cand1 = rs1.getTailCandidates(triple);
				ArrayList<String> cand2 = rs2.getTailCandidates(triple);;
				boolean foundBy1 = false;
				for (String c1 : cand1) {
					if (t.getTail().equals(c1)) foundBy1 = true;
				}
				boolean foundBy2 = false;
				for (String c2 : cand2) {
					if (t.getTail().equals(c2)) foundBy2 = true;
				}
				if (foundBy1 != foundBy2) {
					System.out.println("T " + rs1.getName() + "=" + foundBy1 + " " + rs2.getName() + "=" + foundBy2 + " " + triple);
				}
			}
		}
	}

}
