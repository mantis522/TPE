package tpe.datastructure;

public class ExtractedElement {
	public int pNodeNo;
	public int tNodeNo;
	
	public String pNodeString;
	public String tNodeString;
	
	public int type; // 1. Noun, 2. Verb, 3. Preposition, 4. Adjective, 5. Adverb

	
	
	
	public ExtractedElement(int pNodeNo, int tNodeNo, String pNodeString, String tNodeString) {
		super();
		this.pNodeNo = pNodeNo;
		this.tNodeNo = tNodeNo;
		this.pNodeString = pNodeString;
		this.tNodeString = tNodeString;
		this.type = type;
	}

	
	public int getpNodeNo() {
		return pNodeNo;
	}

	public void setpNodeNo(int pNodeNo) {
		this.pNodeNo = pNodeNo;
	}

	public int gettNodeNo() {
		return tNodeNo;
	}

	public void settNodeNo(int tNodeNo) {
		this.tNodeNo = tNodeNo;
	}

	public String getpNodeString() {
		return pNodeString;
	}

	public void setpNodeString(String pNodeString) {
		this.pNodeString = pNodeString;
	}

	public String gettNodeString() {
		return tNodeString;
	}

	public void settNodeString(String tNodeString) {
		this.tNodeString = tNodeString;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
}
