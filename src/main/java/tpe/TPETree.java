package tpe;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

class NodeNumber{
    private int number = 1;
    void addNumber(){
        ++number;
    }

    public int getNumber() {
        return number;
    }
}

public class TPETree {
	TreeNode[] nodePointer = null;
	TreeNode rootNode = null;
	int n  = -1;

	public void setRootNode(TreeNode t){
		this.rootNode = t;
	}

	public TreeNode getIthNode(int i){
		return this.nodePointer[i];
	}

	public TreeNode getRootNode(){
		return this.rootNode;
	}

	public int calculateHeightOfNode(TreeNode root){
        try{
            if(root != null){
                int childrenHeights[] = new int[root.getChildren().size()];
                for(int i=0; i< root.getChildren().size(); i++)
                    childrenHeights[i] = calculateHeightOfNode(root.getChildren().get(i));

                // 자식 노드 높이 중에서 최대 높이 찾기
                int maxValue = 0;
                for(int j=0; j<childrenHeights.length; j++)
                    if(maxValue < childrenHeights[j])  maxValue = childrenHeights[j];

                // Iterative node와 Negative node는 높이에서 제외함.
               if(root.getValue().equals("*") || root.getValue().equals("!"))
                   maxValue = maxValue;
               else
                   maxValue = maxValue+1;

              // System.out.println("current node is "+root.getValue().getData()+" and its Height is "+maxValue);

               root.setHeight(maxValue);
               return maxValue;
            }else
                return 0;
        }catch(Exception e){
            System.err.println("calculateHeightOfNode error: "+e.getMessage()+" and return -1 to you\n");
            return -1;
        }
    }


	public void calculatePostOrderingNumberOfNode(TreeNode root, NodeNumber nodeNum){
	      //  System.out.println("calculatePostOrderingNumberOfNode 시작 ");
	        try{
	            if(root != null){
	                for(int i=0; i < root.getChildren().size(); i++)
	                    calculatePostOrderingNumberOfNode(root.getChildren().get(i),nodeNum);
	                root.setNodeNo(nodeNum.getNumber());
	                nodeNum.addNumber();

	                nodePointer[root.getNodeNo()] = root; // 직접 접근이 가능한 포인터 배열과 연결함.

	                if(root.getChildren().size()<1){ // 노드가 Leaf 노드인 경우
	                	if(root.getNodeType()==1){ // 일반 노드인 경우
	                		root.setIsLeafNode(81); // 노드가 Leaf이고, 일반노드인 경우 81 표시
	                		root.setNodeType(81);
	                		
	                		// 변수 노드 여부를 확인하여 해당 노드에 표시 (주어, 동사, 목적어 등의 문법 요소를 추출하여 Triplet에 저장하기 위하여 새롭게 추가된 코드)
	                		String rootValue = root.getValue();
	                		if(rootValue.matches("%S.+%")){ // Leaf Node --> Subject (1. 주어)
	                			root.setInstantNode(true);
	                			root.setInstantNodeType(1);
	                		}else if(rootValue.matches("%V.+%")){ // Leaf Node --> Verb (2. 주동사) 
	                			root.setInstantNode(true);
	                			root.setInstantNodeType(2);
	                		}else if(rootValue.matches("%O.+%")){ // Leaf Node --> Object (3. 목적어)
	                			root.setInstantNode(true);
	                			root.setInstantNodeType(3);
	                		}else if(rootValue.matches("%PP.+%")){ // Leaf Node --> Preposition (4. 전치사)
	                			root.setInstantNode(true);
	                			root.setInstantNodeType(4);
	                		}else if(rootValue.matches("%PO.+%")){ // Leaf Node --> Prepositional Object (5. 전치사의 목적어)
	                			root.setInstantNode(true);
	                			root.setInstantNodeType(5);
	                		}else if(rootValue.matches("%AJ.+%")){ // Leaf Node --> Adjective (6. 형용사)
	                			root.setInstantNode(true);
	                			root.setInstantNodeType(6);
	                		}else if(rootValue.matches("%NT.+%")){ // Leaf Node --> Negative (not) (8. 부정어)
	                			root.setInstantNode(true);
	                			root.setInstantNodeType(8);
	                		}else if(rootValue.matches("%W.+%")){ // Leaf Node --> Head word for question (10. 의문사)
	                			root.setInstantNode(true);
	                			root.setInstantNodeType(10);
	                		}else if(rootValue.matches("%HW.+%")){ // Leaf Node --> Head word for question (9. HeadWord)
	                			root.setInstantNode(true);
	                			root.setInstantNodeType(9);
	                		}else if(rootValue.matches("%ET.+%")){ // Leaf Node --> Etc. (7. 기타 문법 요소)
	                			root.setInstantNode(true);
	                			root.setInstantNodeType(7);
	                		}else ;
	                		
	                		rootValue = "";
	                		
	                		
	                	}// end of inner if
	                }// end of outer if
	            }
	        }catch(Exception e){
	            System.err.println("calculatePostOrderingNumberOfNode error: "+e.getMessage());
	        }
	}
	
	

	public void getPostOrderingNumber(TreeNode root){
		NodeNumber n = new NodeNumber();
		this.calculatePostOrderingNumberOfNode(root, n);
	}

	public int calculateTheNumberOfTreeNode(TreeNode root){
        int s = 0;
        if(root!=null){
            for(int i=0; i<root.getChildren().size(); i++)
                s += calculateTheNumberOfTreeNode(root.getChildren().get(i));
            s += 1;
            return s;
        }else
            return 0;
    }

	public void makeNodePointerList(int n){
		this.nodePointer = new TreeNode[n+1];
		this.nodePointer[0] = null;
	}

	public void printPostOrderingOfNodes(TreeNode root){
        try{
            if(root != null){
                for(int i=0; i < root.getChildren().size(); i++)
                    printPostOrderingOfNodes(root.getChildren().get(i));
                System.out.print("["+root.getValue()+"]"+root.getNodeNo()+"->");
            }
        }catch(Exception e){
            System.err.println("printPostOrderingOfNodes error: "+e.getMessage());
        }
    }

	public void printPoinerArray(){
        System.out.println("\n포인터 변수 출력 예제");
        for(int i=1; i<this.nodePointer.length; i++)
            System.out.print(this.nodePointer[i].getValue()+"->");
    }

	public void printHeightOfNodes(TreeNode root){
		try{
            if(root != null){
                for(int i=0; i < root.getChildren().size(); i++)
                	printHeightOfNodes(root.getChildren().get(i));
                System.out.print("["+root.getValue()+"](H="+root.getHeight()+")"+root.getNodeNo()+"->");
            }
        }catch(Exception e){
            System.err.println("printPostOrderingOfNodes error: "+e.getMessage());
        }
    }

	public void printNodeTypeOfNodes(TreeNode root){
		try{
            if(root != null){
                for(int i=0; i < root.getChildren().size(); i++)
                	printNodeTypeOfNodes(root.getChildren().get(i));
                System.out.print("["+root.getValue()+"](T="+root.getNodeType()+")"+root.getNodeNo()+"->");
            }
        }catch(Exception e){
            System.err.println("printPostOrderingOfNodes error: "+e.getMessage());
        }
    }


	public void aaa(){
        for(int i=1; i<this.nodePointer.length; i++)
            this.nodePointer[i].computeChildrenList();
    }

	public void initTree(){
		this.n = calculateTheNumberOfTreeNode(rootNode);
		makeNodePointerList(n);
		getPostOrderingNumber(rootNode);
		calculateHeightOfNode(rootNode);
		aaa();
	}


}
