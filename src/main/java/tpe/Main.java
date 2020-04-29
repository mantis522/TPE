package tpe;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CoreNLP_Parser Cparser = new CoreNLP_Parser();
        String sentence = Cparser.Core_parser("On average, How many miles are there to the moon?");
        Split_Sentence split_s = new Split_Sentence();
        ArrayList<String> splited_sentence = split_s.split_sentence("How many miles are there to the moon? How many times do I have to tell you?");


        /* 0. Input a TPE pattern sentence and a parsed sentence.
    	 *   - pattern: TPE pattern sentence
    	 *   - sentence: A parsed sentence (It can be obtained from the Stanford NLP full parser.)
    	 */
    	
    	// �򼭹� ���� 1
//    	String pattern = "<S <NP.* * <NN.*|PR.* .+> *> * <VP.* * <VB.* .+> <NP * <NN.*|RP.* .+> * > * > * >";
//    	String sentence = "<S <NP <PRP I>> <VP <VBP like> <NP <DT the> <NN movie>>> <. .>>";
    	// �򼭹� ���� 2    	
//    	String pattern = "<S <NP * <NN.* .+> * > * <VP * <VB.* .+> * <NP * <NP * <NN.* .+> *> <PP <IN .+> <NP * <NN.* .+> * > * > * > * > * >";
//    	String sentence = "<S <NP <NNP Andong>> <VP <VBZ is> <NP <NP <DT a> <JJ historical> <NN city>> <PP <IN in> <NP <NN Korea>>>>> <. !>>";
    	// �ǹ��� ����
    	String pattern = "{SBARQ * {WH.* <WRB .+> <RB|JJ .+> * <NN.* .+> * } {SQ * } * }"; // How + many/much + Noun (�ǹ������� ����� �� �� �κ��� ������ �պκ� (= �ǹ��� �κ�)�� ��Ī�ϴ� ����)
////        String pattern = "{{WH.* <WRB .+> <RB|JJ .+> * <NN.* .+> * } {SQ * } * }";
////    	String sentence = "<SBARQ <PP <IN On> <NP <NN average>>> <, ,> <WHNP <WHADJP <WRB How> <JJ many>> <NNS miles>> <SQ <VBP are> <NP <EX there>> <PP <TO to> <NP <DT the> <NN moon>>>> <. ?>>";
//        String sentence = "<SBARQ <WHNP <WHADJP <WRB How> <JJ many>> <NNS people>> <SQ <VP <VBP work> <PP <IN in> <NP <PRP$ your> <NN company>>>>> <. ?>>";
    	// On average, How many miles are there to the moon?
//        String sentence = "<SBARQ <WHNP <WHADJP <WRB How> <JJ many>> <NNS times>> <SQ <VP <VBP have> <NP <NP <DT any>> <PP <IN of> <NP <NP <PRP us>> <VP <VBN been> <VP <VBN involved> <PP <IN in> <NP <NP <DT an> <NN event>> <ADJP <RB remotely> <JJ exciting>>>>>>>>>>> <. ?>>";

//        String pattern = "<S <NP.* <DT .+> <NN.* .+> *> <VP <VB.* .+> <NP <NN.* .+> * >"; // �̰� the physicists�� �������� (���⼭�� the physicists playing badmitton)
//        // <S <NP.* * <NN.* .+> *> *> �� the�� ����. ���� ���� �� ������ �׳� * ó�� �ع����� ��
//        String sentence = "<NP <DT the> <JJ wonderful> <NN scene>> <PP <IN with> <S <NP <DT the> <NNS physicists>> <VP <VBG playing> <NP <NN badmitton>>>>>";

//        String pattern = "<S <NP <NP * <NN.* .+> *> *> <VP <VB.* .+> <NP <NP <DT.* .+> <JJ.* .+> <NN.* .+> *> *> *> *>";
//        String pattern = "<S <NP <(PR.*|NN.*) .+> <VP <VB.* .+> <NP <NP <NN.* .+> *> *> *> *>";
//        String pattern = "<S <NP <NP * <(NN.*|PRP) .+> *> *> <VP <VB.* .+> <NP <NP <DT.* .+> <JJ.* .+> <NN.* .+> *> *> *> *>";
//        String pattern = "<S <NP <NP <PR.* .+> <JJ.* .+> <NN.* .+> *> *> <VP <VB.* .+> <NP <NP <DT.* .+> <JJ.* .+> <NN.* .+> *> *> *> *>"; // my favorite part was the wonderful scene
//        String pattern = "<S <NP <NP * <(NN.*|PRP) .+> *> *> <VP <VB.* .+> <NP <NP * <NN.* .+> *> *> *> *>";    // part was scene -> �̰� �ϴ� ����
//        String pattern = "<S <NP <(NN.*|PRP) .+> *>  <VP <VB.* .+> <PP <IN .+> <NP <NN.* .+> * > *> * > *>"; // they waited for Robbins
//        String pattern = "<S * <NP <PRP .+>> <VP <VB.* .+> <NP <NP *  <NN.* .+> *> *> *> *>"; // I loved sweaters
//        String pattern = "<NP <NP * <JJ.* .+> <NN.* .+> *> <PP <IN .+> <NP * <VB.* .+> <NN.* .+> * > *> *>";    // wonderful scene with playing badmitton
//        String pattern = "<VP * <VP <VB.* .+> <NP <PR.* .+> * >*> * <VP <VB.* .+> <PRT <RP.* .+> *> <PP <IN .+> <NP <PR.* .+> <NN.* .+> *> *> *>"; // make me go out of my way
//        String pattern = "<S <NP <PR.* .+> *> <VP <VB.* .+> *> *>"; // I think SBAR
//        String pattern = "<S <NP <PR.* .+> *> <VP <VB.* .+> <S.* <S  <NP <NP * <(NN.*|PRP) .+> *> *> *> *> *> *>";
//        String pattern = "<S.* <S  <NP <NP * <(NN.*|PRP) .+> *> *> *> *>";
//        String pattern = "<S.* <S <NP <PRP .+ > *> *> *>";
//        String pattern = "<NP * <VB.* .+> <NN.* .+> * > ";
//        String pattern = "<NP <DT.* .+> <JJ.* .+> <NN.* .+> *> ";
//        String pattern = "";

    	// 1.Define a pattern tree        
        Patterns p = new Patterns(pattern);  // �ʼ�

        // 2. Define a target tree
//        System.out.println("\n\nTarget Tree: ");

        MakeTree tMT = new MakeTree();  // �ʼ�
        TPETree t = new TPETree();  // �ʼ�
        TreeNode tRoot = null;  // �ʼ�

        String[] str2 = tMT.getStringArray(sentence);   // �ʼ�
        tRoot = tMT.getTree(str2);  // �ʼ�
        t.setRootNode(tRoot);   // �ʼ�
        t.initTree();   // �ʼ�
        t.printPostOrderingOfNodes(tRoot);  // �ʼ�

        // 3. Try to match the pattern tree p to the target tree t.
//        System.out.println("\n\nMatched relations: ");
        String relation = null;
        try{
	        relation = p.patternMatching(t);    // �ϴ��� �� �κи� �ʼ�
	        if (relation == null) return; // Not matched.

//	        System.out.println(relation);
        }catch(Exception e){
//        	System.err.println("main Exception -- patternMatching() part: "+e.getMessage());
        	return;
        }

        ////////////////////////////// Test session ////////////////////////////////
        // Print matched words

        ArrayList<String> fruits = new ArrayList<String>();

//        System.out.println("\n\nPrint matched words: ");
        String[] rel = relation.split("::");
        for(int i=0 ; i < rel.length; i++){
        	String rel2[] = rel[i].split(",");
        	if(rel2.length!=2) continue;
//        	System.out.print(rel[i]+" --> ");
//        	System.out.println(p.p.getIthNode(Integer.parseInt(rel2[0])).value+", "+t.getIthNode(Integer.parseInt(rel2[1])).value);

//            int to = Integer.parseInt(rel2[1]);

            if(p.p.getIthNode(Integer.parseInt(rel2[0])).isLeafNode()){
//                System.out.println(to + " -----> " + t.getIthNode(Integer.parseInt(rel2[1])).value);
                fruits.add(t.getIthNode(Integer.parseInt(rel2[1])).value);

            }
//            System.out.print(t.getIthNode(Integer.parseInt(rel2[1])).value + " ");
        	//System.exit(0);
        }
        Collections.reverse(fruits);
//        System.out.println(fruits);
        // Load matched words into array list
//        System.out.println("\n\nLoad matched words into array list: ");
//        ArrayList<PairMatched> list = null;
//        list = p.patternMatchingToList(t);
        String sum_text = String.join(" ",fruits);
        System.out.println(sum_text);



    }

}