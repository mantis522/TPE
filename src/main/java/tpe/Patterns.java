package tpe;

import tpe.datastructure.PairMatched;

import java.util.ArrayList;

public class Patterns {
	
    public String patternTree = null;
    public ArrayList<String> rules = new ArrayList<String>();
    
    public MakeTree pMT = null;
    public TPETree p = null;
    public TreeNode pRoot = null;
    public TreePatternMatch match = null;

    public Patterns(String patternTree) {
//    	System.out.println("Pattern Tree: ");

        this.match = new TreePatternMatch();
        this.patternTree = patternTree;

        this.pMT = new MakeTree();
        this.p = new TPETree();
        this.pRoot = null;
       
        String str[] = pMT.getStringArray(patternTree);
        this.pRoot = pMT.getTree(str);
        this.p.setRootNode(pRoot);
        this.p.initTree();
        
        p.printPostOrderingOfNodes(pRoot);
    }
    
/*
    public void setTriplet(Triplet triplet){
        this.triplets.add(triplet);
    }
*/
    
    public String patternMatching(TPETree targetTree){
        String matchResult = null;
        try{
            matchResult = this.match.treePatternMatching(this.p, targetTree);

            if(matchResult== null || matchResult.equals(""))
                return null;
            else{
                return "::"+matchResult;
            }
        }catch(Exception e){
            System.err.println("patternMatching() Exception: "+e.getMessage());
            return null;
        }
    }
    
 
    
    public ArrayList<PairMatched> patternMatchingToList(TPETree t){
        ArrayList<PairMatched> list = new ArrayList<PairMatched>();
        try{
        	String relation = null;
        	int pNodeNo=0; int tNodeNo=0;
        	String[] rel = null;
        	
        	relation = patternMatching(t);
            
            rel = relation.split("::");
            
            if(rel == null) return null;
            
            String rel2[] = null;
            TreeNode temp = null;
            for(int i=0 ; i < rel.length; i++){
            	rel2 = rel[i].split(",");
            	if(rel2.length!=2) continue;
            	pNodeNo = Integer.parseInt(rel2[0]);
            	tNodeNo = Integer.parseInt(rel2[1]);
            	temp = t.getIthNode(tNodeNo).getGrandParentNode();
            	if(temp == null)
            		list.add(new PairMatched(pNodeNo, tNodeNo, p.getIthNode(pNodeNo).getInstantNodeType(), -1, p.getIthNode(pNodeNo).value, t.getIthNode(tNodeNo).value));
            	else
            		list.add(new PairMatched(pNodeNo, tNodeNo, p.getIthNode(pNodeNo).getInstantNodeType(), temp.getNodeNo(), p.getIthNode(pNodeNo).value, t.getIthNode(tNodeNo).value));
            	
            	rel2 = null;
            }
            
            
            if (list.size()==0) 
            	return null;
            else{
            	for(int i=0; i < list.size(); i++){
            		PairMatched pair = list.get(i);
            		// For debug
            		// System.out.println(pair.getpNode()+", "+pair.gettNode()+", " + pair.gettNodeGrandParent() + ", "+pair.getpNodeValue()+", "+pair.gettNodeValue()+", ["+pair.getpNodeType()+"]");
//            		System.out.print("["+pair.getpNodeValue()+"("+pair.getpNode()+"), "+pair.gettNodeValue()+"("+pair.gettNode()+")]  ");
            	}
            	return list;
            }
        }catch(Exception e){
            System.err.println("patternMatchingToList() Exception: "+e.getMessage());
            return null;
        }
    }

    
    
    
    public ArrayList<PairMatched> getInstantiatedElements(TPETree t){
        ArrayList<PairMatched> list = new ArrayList<PairMatched>();
        try{
        	String relation = null;
        	int pNodeNo=0; int tNodeNo=0;
        	String[] rel = null;
        	
        	relation = patternMatching(t);
            
            rel = relation.split("::");
            
            if(rel == null) return null;
            
            String rel2[] = null;
            TreeNode tempGpNode = null;
            TreeNode tempPatternNode = null;
            TreeNode tempTargetNode = null;
            int relSize = rel.length;
            
            for(int i=relSize-1 ; i >= 0 ; i--){
            	rel2 = rel[i].split(",");
            	if(rel2.length!=2) continue;
            	pNodeNo = Integer.parseInt(rel2[0]);
            	tNodeNo = Integer.parseInt(rel2[1]);
            	
            	tempPatternNode = p.getIthNode(pNodeNo);
            	
            	if(tempPatternNode.getInstantNodeType()<=0) 
            		continue;
            	
            	tempTargetNode = t.getIthNode(tNodeNo);
            	tempGpNode = t.getIthNode(tNodeNo).getGrandParentNode();
            	
            	int gpNodeNo;
            	if(tempGpNode == null) gpNodeNo = -1;
            	else gpNodeNo = tempGpNode.getNodeNo();
            	
            	list.add(new PairMatched(pNodeNo, tNodeNo, tempPatternNode.getInstantNodeType(), gpNodeNo, 
            			tempPatternNode.value, tempTargetNode.value));
            	
            	rel2 = null;
            }
            
            
            if (list.size()==0) 
            	return null;
            else{
//            	System.out.println("pNode\ttNode\ttGPNode\tpNV\ttNV\tpNType");
            	for(int i=0; i < list.size(); i++){
            		PairMatched pair = list.get(i);
//            		System.out.println(pair.getpNode()+"\t"+pair.gettNode()+"\t" + pair.gettNodeGrandParent() + "\t"+
//            				pair.getpNodeValue()+"\t"+pair.gettNodeValue()+"\t["+pair.getpNodeType()+"]");
            	}
            	return list;
            }
        }catch(Exception e){
            System.err.println("patternMatchingToList() Exception: "+e.getMessage());
            return null;
        }
    }
    /*
    
    public ArrayList<PairMatched> getInstantiatedElements(TPETree t){
    	try{
    		ArrayList<PairMatched> newList = new ArrayList<PairMatched>();
    		ArrayList<PairMatched> rawList = this.patternMatchingToList(t);
    		
    		int rawListSize = rawList.size();
    		
    		PairMatched pair = null;
    		for(int i = rawListSize - 1; i >= 0; i--){
    			pair = rawList.get(i);
    			if(pair.gettNode)
    			
    		}
    		
    	}catch(Exception e){
            System.err.println("getInstantiatedElements() Exception: "+e.getMessage());
            return null;
        }
    }
*/
}
