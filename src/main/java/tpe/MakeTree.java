package tpe;

import java.util.ArrayList;
import java.util.Stack;

public class MakeTree {
	// 입력받은 Pattern Expression을 내부 알고리즘에서 사용할 수 있는 형태로 변환하여 String[] 타입으로 반환함.
	// 이 메소드는 내부에서만 사용된다.
	public String[] getStringArray(String str){
		try{
			ArrayList<String> list = new ArrayList<String>();

			int i=0;
			while(i<str.length()){
				if(str.charAt(i)==' ') { // 공백 문자인 경우
					list.add(" "); // 공백 한 개만 리스트에 추가한다.
					while(i<str.length() && str.charAt(i)==' ') i=i+1; //  나머지 반복되는 공백은 모두 제거
				}else if(str.charAt(i)=='<' || str.charAt(i)=='{' ||str.charAt(i)=='>' || str.charAt(i)=='}' ||
						str.charAt(i)=='*' || str.charAt(i)=='!'){

					if(i+1<str.length() && str.charAt(i+1)==' '){ //특수 문자 뒤의 띄어쓰기는 모두 인정한다. 단, 맨 마지막일 경우는 제외
						list.add(String.valueOf(str.charAt(i)));
						i = i+1;
					}else if(i<str.length() && (str.charAt(i)=='*' || str.charAt(i)=='!') ){
						// 특수 문자 뒤에가 공백이 아니면서 동시에 특수문자가 * 또는 !인 경우 뒤의 문자를 인정해야 한다. (공백 추가 안함)
						list.add(String.valueOf(str.charAt(i)));
						i = i+1;
						if(i<str.length() && (str.charAt(i)=='>' || str.charAt(i)=='}')) // 단 뒤의 문자로 >나 }가 오면 공백을 추가한다.
							list.add(" ");
					}else{// 나머지 특수문자 (괄호)의 뒤에가 공백이 아닌 경우, 공백을 삽입함. (단 맨 마지막 글자는 제외)
						list.add(String.valueOf(str.charAt(i)));
						if(i+1<str.length())
							list.add(" ");
						i = i+1;
					}
				}else{// 특수문자가 아닌 일반 문자의 경우 이어붙임.
					String temp = "";
					while(i<str.length()  && (str.charAt(i)!='<' && str.charAt(i)!='{' && str.charAt(i)!='>' && str.charAt(i)!='}'
							&& str.charAt(i)!='!' && str.charAt(i)!=' ')){
						temp = temp + str.charAt(i);
						i = i+1;
					}

					list.add(temp);

					// 이어 붙인 뒤부분의 글자가 공백이 아닌 경우 공백을 삽입함. (맨 마지막 글자는 제외)
					if(i<str.length() && str.charAt(i)!=' ')
						list.add(" ");
				}
			}

			// 맨 앞의 공백은 효율성을 위해서 모두 지운다.
			String t = "";
			while((t=list.get(0)).equals(" "))
				list.remove(0);


			// 최종 반환은 String Array 타입으로 한다.
			String[] a = new String[list.size()];

			for(int j=0; j < list.size(); j++)
				a[j] = list.get(j);

			return a;
		}catch(Exception e){
			System.err.println("String Array Exception: "+e.getMessage());
			return null;
		}
	}



	public TreeNode getTree(String[] str){
		try{
			Stack<TreeNode> prevStack = new Stack<TreeNode>();
			//Stack<String> closeStack = new Stack<String>();

			TPETree newTree = new TPETree();
			TreeNode root = null;
			TreeNode parent = null;

			int i=0;
			boolean isStart = true;
			while(i<str.length){
				if(str[i].equals(" ")) {
					i = i+1;
					//	System.out.println("step 0");
				}else if(str[i].equals("<") || str[i].equals("{")){
					if(isStart){
						root = new TreeNode(str[i+2]);
						if(str[i].equals("<")) root.setNodeType(1);
						else if(str[i].equals("{")) root.setNodeType(2);

						parent = root;
						prevStack.add(parent);
						//	closeStack.add(str[i]);
						i = i+3;
						isStart = false;
						//		System.out.println("step 1");
					}else{
						TreeNode newNode2 = new TreeNode(str[i+2]);
						if(str[i].equals("<")) newNode2.setNodeType(1);
						else if(str[i].equals("{")) newNode2.setNodeType(2);

						parent.addChild(newNode2);   newNode2.addParent(parent);
						prevStack.add(parent);
						parent = newNode2;
						//	closeStack.add(str[i]);
						i = i+3;
						//		System.out.println("step 2");
					}
				}else if(str[i].equals(">") || str[i].equals("}")){
					if(!prevStack.isEmpty())
						parent = prevStack.pop();

					while(!prevStack.isEmpty() && (parent.getValue().equals("*") || parent.getValue().equals("!")))
						parent = prevStack.pop();

					//	System.out.println("step 3");
					/*
					if(!closeStack.isEmpty())
					{
						String temp = closeStack.pop();
						if(str[i].equals(">") && temp.equals("<")) ;
						else if(str[i].equals("}") && temp.equals("{")) ;
						else{
							System.err.println("괄호의 짝이 맞지 않습니다. 에러");
							//return null;
						}
					}
					*/
					i = i+1;

				}else if(str[i].equals("*")){
					if(i+1<str.length && str[i+1].equals(" ")){
						TreeNode newNode2 = new TreeNode(str[i]);  newNode2.setNodeType(3);
						TreeNode anyNode = new TreeNode("<>");  anyNode.setNodeType(5);
						newNode2.addChild(anyNode);   anyNode.addParent(newNode2);
						parent.addChild(newNode2);    newNode2.addParent(parent);
						i = i+1;

						//		System.out.println("step 4");
					}else{
						TreeNode newNode2 = new TreeNode(str[i]);  newNode2.setNodeType(3);

						parent.addChild(newNode2);   newNode2.addParent(parent);

						if(i+1<str.length && (str[i+1].equals("<") || str[i+1].equals("{")))
						{
							prevStack.add(parent);
							parent = newNode2;
							i = i+1;
							//			System.out.println("step 5");
						}else if(i+1<str.length && str[i+1].equals("!")){
							prevStack.add(parent);
							parent = newNode2;
							i = i+1;
							//			System.out.println("step 6");
						}else{
							TreeNode newNode3 = new TreeNode(str[i+1]);
							newNode3.setNodeType(1);

							newNode2.addChild(newNode3);    newNode3.addParent(newNode2);

							i = i+2;
							//			System.out.println("step 7");
						}
					}
				}else if(str[i].equals("!")){
					TreeNode newNode2 = new TreeNode(str[i]);
					newNode2.setNodeType(4);
					parent.addChild(newNode2);   newNode2.addParent(parent);

					if(i+1<str.length && (str[i+1].equals("<") || str[i+1].equals("{"))){
						prevStack.add(parent);
						parent = newNode2;
						i = i+1;
						//		System.out.println("step 8");
					}else{
						TreeNode newNode3 = new TreeNode(str[i+1]);
						newNode2.addChild(newNode3);   newNode3.addParent(newNode2);
						i = i+2;
						//			System.out.println("step 9");
					}
				}
				else{ // 일반 노드의 경우
					TreeNode newNode2 = new TreeNode(str[i]);
					newNode2.setNodeType(1);
					parent.addChild(newNode2);    newNode2.addParent(parent);
					i = i+1;
					//		System.out.println("step 10");
				}
			}
			//	System.out.println("Complete make tree");
			return root;
		}catch(Exception e){
			//System.err.println("makeTree Exception: "+e.getMessage());
			return null;
		}
	}


}
