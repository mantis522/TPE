package tpe;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import tpe.datastructure.ExtractedElement;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TreePatternMatch {
	ArrayList<ExtractedElement> matchedLeafList = new ArrayList<ExtractedElement>();


	TPETree p = null;
	TPETree t = null;

	int pN = -1;
	int tN = -1;
	String B[][] = null;
	Hashtable<String, String> R = null;

	public void initialize(TPETree p, TPETree t){
		this.p = p;
		this.t = t;
		this.pN = p.n;
		this.tN = t.n;
		B = new String[pN+1][tN+1];
		R = new Hashtable<String,String>();
		initializeB(pN, tN);
		initializeR();
	}


	///////////////////// For array B////////////////////////////////////////////

	// Initialize B
	public void initializeB(int pN, int tN){
		for(int i=0; i < pN+1; i++)
			for(int j=0; j < tN+1; j++)
				B[i][j] = "Fails";
	}

	// Set Fails
	public void setBFails(int i, int j){
		B[i][j] = "Fails";
	}

	// Get Fails
	public boolean isBFails(int i, int j){
		if(B[i][j].equals("Fails")) return true;
		else return false;
	}

	// Set Phi
	public void setBPhi(int i, int j){
		B[i][j] = "Phi";
	}

	// Get Phi
	public boolean isBPhi(int i, int j){
		if(B[i][j].equals("Phi")) return true;
		else return false;
	}


	// Set relation
	public void setB(int i, int j, String relation){
		B[i][j] = relation;
	}

	// Get relation
	public String getB(int i, int j){
		return B[i][j];
	}

	///////////////////////////////////////////////////////////////////////////////



	/////////////////// For Hashtable R ///////////////////////////////////////////
	// Initialize Hashtable
	public void initializeR(){
		if(!R.isEmpty())
			R.clear();
	}

	// Get Nil  (Nil is initial state)
	public boolean isRNil(String s1, String s2){
		String key = s1+"::"+s2;
		if(!R.containsKey(key)) return true;
		else return false;
	}

	// Set Nil (Delete element from the hashtable)
	public void setNil(String s1, String s2){
		String key = s1+"::"+s2;
		if(!R.containsKey(key)) return;
		else
			R.remove(key);
	}

	// Get Phi
	public boolean isRPhi(String s1, String s2){
		String key = s1+"::"+s2;
		if(!R.containsKey(key)) return false; // Nil
		else if(R.get(key).equals("Phi")) return true;
		else return false;
	}

	// Set Phi
	public void setRPhi(String key1, String key2){
		String key = key1+"::"+key2;
		if(!R.containsKey(key))
			R.put(key, "Phi");
		else
		{
			R.remove(key);
			R.put(key, "Phi");
		}
	}

	// Get Fails
	public boolean isRFails(String s1, String s2){
		String key = s1+"::"+s2;
		if(!R.containsKey(key)) return false; // Nil
		else if(R.get(key).equals("Fails")) return true;
		else return false;
	}

	// Set Fails
	public void setRFails(String key1, String key2){
		String key = key1+"::"+key2;
		if(!R.containsKey(key))
			R.put(key, "Fails");
		else
		{
			R.remove(key);
			R.put(key, "Fails");
		}
	}

	// Get value is Relation: <a,b>,<c,d>,<e,f> ---> a,b::c,d::e,f::
	public String getR(String key1, String key2){
		String key = key1+"::"+key2;
		if(!R.containsKey(key)) // Nil
			return "Nil";
		else
			return R.get(key);
	}

	// Set value (value is a relation a,b::c,d::e,f:: )
	public void setR(String key1, String key2, String relation){

		String key = key1+"::"+key2;

		//	System.out.println("  Key is "+key);
		if(R.containsKey(key))
			R.remove(key);

		R.put(key, relation);
	}

	///////////////////////////////////////////////////////////////////////////////



	/////////////// For a term <a,b> ---> a,b /////////////////////////////////////
	public int getParentNode(String term){
		// A term(Pair) is <a,b> ---> a,b
		String splitTerm[] = term.split(",");

		return Integer.parseInt(splitTerm[0]); // return a
	}

	public int getChildNode(String term){
		// A term(Pair) is <a,b> ---> a,b
		String splitTerm[] = term.split(",");

		return Integer.parseInt(splitTerm[1]); // return b
	}
	///////////////////////////////////////////////////////////////////////////////

	public String cons(String s1, String s2){
		if(s1.equals("")) return s2;
		else if(s2.equals("")) return s1;
		else {
			String s = s1+s2;

			return s;
		}
	}

	public String head(String s1){
		// 1,2,3,4,5,6,7,
		if(s1.length()<1)
			return "";

		String[] s = s1.split(",");
		String value = s[0];
		return value;
	}
	public String tail(String s1){
		if(s1.length()<1)
			return "";

		String[] s = s1.split(",");
		String value = "";

		for(int i=1; i<s.length; i++)
			value = value + s[i]+",";

		return value;
	}
	public boolean patternMatch(String pattern, String target){
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(target);
		boolean isMatch = false;
		isMatch = m.matches();

		return isMatch;
	}

	int xx = 1;
	int yy = 1;

	public String treePatternMatching(TPETree p, TPETree t){
		String matchResult = null;
		String pNodeV = null;
		String tNodeV = null;

		pNodeV = ""; tNodeV = "";

		try{
			this.initialize(p, t);

			for(int i=1; i <=pN ; i++){ xx = i;
				//	System.out.print("i="+i);
				if(p.getIthNode(i).getNodeType() == 3){
					//	System.out.println();
					continue;
				}

				for(int j=1; j <=tN ;j++){ yy = j;
					//	System.out.println(" j="+j);
					if( (p.getIthNode(i).getHeight()>t.getIthNode(j).getHeight())||
							(p.getIthNode(i).isLeafNode() && !t.getIthNode(j).isLeafNode()  )) continue;
					if(p.getIthNode(i).getNodeType()==5){ // anyTree
						setBPhi(i,j);
					}else if(p.getIthNode(i).getNodeType()==4){ // Negative node
						if( isBFails( p.getIthNode(i).getChild().getNodeNo(), j))
							setBPhi(i,j);
					}else if(p.getIthNode(i).getNodeType()==1){ // Tree Node
						//System.out.println("----->"+t.getIthNode(j).getValue());
						if( patternMatch(p.getIthNode(i).getValue(), t.getIthNode(j).getValue()))
							setB(i,j, match(p.getIthNode(i).getChildrenList(), t.getIthNode(j).getChildrenList(), false) );
					}else if(p.getIthNode(i).getNodeType()==2){ // Cut node
						if( patternMatch(p.getIthNode(i).getValue(), t.getIthNode(j).getValue()))
							setB(i,j, match(p.getIthNode(i).getChildrenList(), t.getIthNode(j).getChildrenList(), true) );
					}else if(p.getIthNode(i).getNodeType()==81){ // Leaf node

						pNodeV = p.getIthNode(i).getValue();
						tNodeV = t.getIthNode(j).getValue();

						// 주어, 동사, 목적어 등의 문법 요소를 추출하여 Triplet에 저장하기 위하여 새롭게 추가된 코드
						if( pNodeV.matches("%S.+%") || pNodeV.matches("%V.+%") || pNodeV.matches("%O.+%") || pNodeV.matches("%PP.+%") ||
								pNodeV.matches("%PO.+%") || pNodeV.matches("%AJ.+%") || pNodeV.matches("%ET.+%") || pNodeV.matches("%NT.+%") ||
								pNodeV.matches("%HW.+%") || pNodeV.matches("%W.+%")){
							if(patternMatch(".*", tNodeV))
								setBPhi(i,j);
						}else{
							if(patternMatch(pNodeV, tNodeV))
								setBPhi(i,j);
						}
						pNodeV = "";
						tNodeV = "";

						//	if(patternMatch(p.getIthNode(i).getValue(), t.getIthNode(j).getValue()))
						//		setBPhi(i,j);
					}
				}
			} // end of For

			String L = "";
			for(int j=1; j <= tN; j++){
				if(B[p.getRootNode().getNodeNo()][j].equals("Fails")) continue;
				else{
					L = treeBinding(p.getRootNode().getNodeNo(), j)+L+"::";
				}
			}
/*
			for(int i=1; i <= pN; i++)
				for(int k=1; k <= tN; k++)
					if(!B[i][k].equals("Fails"))	System.out.println(i+","+k+"] "+B[i][k]);
*/			/*
			 *  For experiment...... using TPE V1.
			 */
			String[] L2 = L.split("::");
			for(int i=L2.length-1; i >= 0; i--){

				int pa = getParentNode(L2[i]);
				int ch = getChildNode(L2[i]);

				//	System.out.println(p.getIthNode(pa).getValue()+" ["+p.getIthNode(pa).getNodeNo()+"] --> "+t.getIthNode(ch).getValue()+" ["+t.getIthNode(ch).getNodeNo()+"]");
			}

			matchResult = L;

			//	System.out.println("Complete!!");
			if(matchResult==null) return null;
			if(matchResult.length() <= 0) return null;

			//   System.out.println("매칭 완료: "+L);
			return matchResult;

		}catch(Exception e){
			//       System.err.println("treePatternMatching Exception: "+e.getMessage());
			pNodeV = "";
			tNodeV = "";

			return null;
		}
	}


	public String match(String x, String y, boolean cut){
		try{
			String L2 = "";String L5 = "";
			//		System.out.println("   ===>x="+x+"   and y="+y);
			//	System.out.println(p.getIthNode(Integer.parseInt(head(x))).getValue());
			if(!isRNil(x,y)) return getR(x,y);
			setRFails(x,y);
			if(x.equals("")){ if(y.equals("")){ setRPhi(x,y);} }
			else if( p.getIthNode(Integer.parseInt(head(x))).nodeType == 3 && !(L5=match(tail(x), y, cut)).equals("Fails") ){
				//	System.out.println("Point7");
				setR(x,y,L5);
			}else if(p.getIthNode(Integer.parseInt(head(x))).nodeType == 3 && !y.equals("") && !isBFails( p.getIthNode(Integer.parseInt(head(x))).getChild().getNodeNo(), Integer.parseInt(head(y)) ) && !(( L2 = match(x, tail(y),cut)).equals("Fails"))){
				setR(x,y, p.getIthNode( Integer.parseInt(head(x))).getChild().getNodeNo()+","+head(y)+"::"+L2  );
			}else if( cut == false && p.getIthNode(Integer.parseInt(head(x))).nodeType == 3) ;
			else if(!y.equals("") && !isBFails(Integer.parseInt(head(x)),Integer.parseInt(head(y)) )      ){
				//	System.out.println("Point2");
				String L = "";
				if( !(L=match(tail(x),tail(y),cut)).equals("Fails")  ){
					//		System.out.println("Point3");
					setR(x,y,  Integer.parseInt(head(x))+","+Integer.parseInt(head(y))+"::"+L);
				}
			}else if(cut==true && !x.equals("") && !y.equals("") && !(t.getIthNode(Integer.parseInt(head(y))).isLeafNode())  ){
				//	System.out.println("Point4: "+x+",,"+y);
				String L = "";
				if( !(L=match(x, cons( t.getIthNode(Integer.parseInt(head(y))).getChildrenList(), tail(y) ) , cut)  ).equals("Fails")   )
				{
					//		System.out.println("Point5");
					setR(x,y, L);
				}
			}
			return getR(x,y);
		}catch(Exception e){
			System.err.println("match exception: "+e.getMessage());
			System.err.println("Parameter is "+x+"  and"+y+"  || node no is "+xx+" , "+yy);
			return null;
		}
	}


	public String treeBinding(int p, int t){
		try{
			String L = p+","+t+"::";
			String[] relation = B[p][t].split("::");

			for(int i=0; i < relation.length; i++){
				if(!relation[i].equals("Phi")){
					int pa = getParentNode(relation[i]);
					int ch = getChildNode(relation[i]);
					L = treeBinding(pa, ch)+L;
				}
			}

			return L;
		}catch(Exception e){
			System.err.println("Tree binding error: "+e.getMessage());
			return "";
		}
	}

}
