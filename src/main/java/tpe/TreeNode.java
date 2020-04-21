package tpe;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * @author 1
 *
 */

public class TreeNode {
	// For Triplet Extraction........
	public String tripletType = "NULL";

	public void setTripletNodeType(String type){
		this.tripletType = type;
	}

	public String getTripletNodeType(){
		return this.tripletType;
	}

	public String value=""; // String value.
	int nodeNo=-1; // Post-order node no.
	int height=-1; // Node height
	int nodeType=-1; // 1:TreeNode(81 is Leaf), 2:CutNode, 3:IterativeNode, 4:NegativeNode, 5:AnyNode
	int isLeafNode=-1; // 81: Leaf+TreeNode


	boolean isInstantNode = false; // 변수노드인지 여부 확인

	// Leaf Node가 InstantNode인 경우 1: 주어  2: 서울어  3: 목적어  4: 전치사  5: 전치사목적어  6: 형용사  7: 기타  8: 부정어(not)  9: HeadWord for Question
	// 10: Interrogative word (의문사)
	int instantNodeType = -1;

	ArrayList<TreeNode> children;
	TreeNode parent;
	String childrenList;
	Hashtable<Integer, Integer> childrenIDList = null;

	public TreeNode(String value){
		this.value = value;
		this.children = new ArrayList<TreeNode>();
		this.childrenIDList = new Hashtable<Integer, Integer>();
		this.parent = null;
	}



	public TreeNode getParent() {
		return parent;
	}

	public void addParent(TreeNode parent) {
		this.parent = parent;
	}

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getNodeNo() {
		return nodeNo;
	}
	public void setNodeNo(int nodeNo) {
		this.nodeNo = nodeNo;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getNodeType() {
		return nodeType;
	}
	public void setNodeType(int nodeType) {
		this.nodeType = nodeType;
	}
	public int getIsLeafNode() {
		return isLeafNode;
	}
	public void setIsLeafNode(int isLeafNode) {
		this.isLeafNode = isLeafNode;
	}

	public ArrayList<TreeNode> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<TreeNode> children) {
		this.children = children;
	}

	public String getChildrenList() {
		return childrenList;
	}
	public void setChildrenList(String childrenList) {
		this.childrenList = childrenList;
	}


	public void addChild(TreeNode child){
		this.children.add(child);
		//this.childrenList = this.childrenList+child.getNodeNo()+",";
	}

	public void computeChildrenList(){ ////////////////////////// Modified method....... 20130502 //////////////////
		String a = "";

		for(int i=0; i < this.children.size(); i++){
			int nodeNo = this.children.get(i).getNodeNo();
			a = a+nodeNo+",";
			this.childrenIDList.put(new Integer(nodeNo), new Integer(nodeNo));
		}

		this.childrenList = a;
	}

	public TreeNode getChild(){
		return this.children.get(0);
	}


	public boolean isLeafNode(){
		if(this.isLeafNode==81 ) return true;
		else return false;
	}


	public boolean isInstantNode() {
		return isInstantNode;
	}

	public void setInstantNode(boolean isInstantNode) {
		this.isInstantNode = isInstantNode;
	}


	public int getInstantNodeType() {
		return instantNodeType;
	}

	public void setInstantNodeType(int instantNodeType) {
		this.instantNodeType = instantNodeType;
	}

	public TreeNode getGrandParentNode(){
		if(this.parent == null) return null;

		return this.parent.getParent();
	}

	//
        /*
        public int getspecificChild(int childNo){
            if(!this.childrenIDList.containsKey(new Integer(childNo)))
                return -1;

            return
               this.childrenIDList.get(new Integer(childNo)).intValue();
        }
*/
	//// 이 메소드들은 추후에 matching algorithm으로 옮긴다.




}
