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

                // �ڽ� ��� ���� �߿��� �ִ� ���� ã��
                int maxValue = 0;
                for(int j=0; j<childrenHeights.length; j++)
                    if(maxValue < childrenHeights[j])  maxValue = childrenHeights[j];

                // Iterative node�� Negative node�� ���̿��� ������.
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
	      //  System.out.println("calculatePostOrderingNumberOfNode ���� ");
	        try{
	            if(root != null){
	                for(int i=0; i < root.getChildren().size(); i++)
	                    calculatePostOrderingNumberOfNode(root.getChildren().get(i),nodeNum);
	                root.setNodeNo(nodeNum.getNumber());
	                nodeNum.addNumber();

	                nodePointer[root.getNodeNo()] = root; // ���� ������ ������ ������ �迭�� ������.

	                if(root.getChildren().size()<1){ // ��尡 Leaf ����� ���
	                	if(root.getNodeType()==1){ // �Ϲ� ����� ���
	                		root.setIsLeafNode(81); // ��尡 Leaf�̰�, �Ϲݳ���� ��� 81 ǥ��
	                		root.setNodeType(81);
	                		
	                		// ���� ��� ���θ� Ȯ���Ͽ� �ش� ��忡 ǥ�� (�־�, ����, ������ ���� ���� ��Ҹ� �����Ͽ� Triplet�� �����ϱ� ���Ͽ� ���Ӱ� �߰��� �ڵ�)
	                		String rootValue = root.getValue();
	                		if(rootValue.matches("%S.+%")){ // Leaf Node --> Subject (1. �־�)
	                			root.setInstantNode(true);
	                			root.setInstantNodeType(1);
	                		}else if(rootValue.matches("%V.+%")){ // Leaf Node --> Verb (2. �ֵ���) 
	                			root.setInstantNode(true);
	                			root.setInstantNodeType(2);
	                		}else if(rootValue.matches("%O.+%")){ // Leaf Node --> Object (3. ������)
	                			root.setInstantNode(true);
	                			root.setInstantNodeType(3);
	                		}else if(rootValue.matches("%PP.+%")){ // Leaf Node --> Preposition (4. ��ġ��)
	                			root.setInstantNode(true);
	                			root.setInstantNodeType(4);
	                		}else if(rootValue.matches("%PO.+%")){ // Leaf Node --> Prepositional Object (5. ��ġ���� ������)
	                			root.setInstantNode(true);
	                			root.setInstantNodeType(5);
	                		}else if(rootValue.matches("%AJ.+%")){ // Leaf Node --> Adjective (6. �����)
	                			root.setInstantNode(true);
	                			root.setInstantNodeType(6);
	                		}else if(rootValue.matches("%NT.+%")){ // Leaf Node --> Negative (not) (8. ������)
	                			root.setInstantNode(true);
	                			root.setInstantNodeType(8);
	                		}else if(rootValue.matches("%W.+%")){ // Leaf Node --> Head word for question (10. �ǹ���)
	                			root.setInstantNode(true);
	                			root.setInstantNodeType(10);
	                		}else if(rootValue.matches("%HW.+%")){ // Leaf Node --> Head word for question (9. HeadWord)
	                			root.setInstantNode(true);
	                			root.setInstantNodeType(9);
	                		}else if(rootValue.matches("%ET.+%")){ // Leaf Node --> Etc. (7. ��Ÿ ���� ���)
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
        System.out.println("\n������ ���� ��� ����");
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
