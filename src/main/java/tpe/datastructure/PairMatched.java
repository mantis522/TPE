package tpe.datastructure;

public class PairMatched {
	private int pNode;
	private int tNode;
	
	private String pNodeValue;
	private String tNodeValue;
	
	private int pNodeType = -1;
	private int tNodeGrandParent;
	
	
	public PairMatched(int pNode, int tNode, int pNodeType, int tNodeGP, String pNodeValue, String tNodeValue) {
		this.pNode = pNode;
		this.tNode = tNode;
		this.pNodeType = pNodeType;
		this.pNodeValue = pNodeValue;
		this.tNodeValue = tNodeValue;
		this.tNodeGrandParent = tNodeGP;
	}


	
	public int gettNodeGrandParent() {
		return tNodeGrandParent;
	}


	public void settNodeGrandParent(int tNodeGrandParent) {
		this.tNodeGrandParent = tNodeGrandParent;
	}


	public int getpNode() {
		return pNode;
	}


	public void setpNode(int pNode) {
		this.pNode = pNode;
	}


	public int gettNode() {
		return tNode;
	}


	public void settNode(int tNode) {
		this.tNode = tNode;
	}


	public String getpNodeValue() {
		return pNodeValue;
	}


	public void setpNodeValue(String pNodeValue) {
		this.pNodeValue = pNodeValue;
	}


	public String gettNodeValue() {
		return tNodeValue;
	}


	public void settNodeValue(String tNodeValue) {
		this.tNodeValue = tNodeValue;
	}



	public int getpNodeType() {
		return pNodeType;
	}



	public void setpNodeType(int pNodeType) {
		this.pNodeType = pNodeType;
	}
	
	
}
