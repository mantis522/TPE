package tpe;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	/* 0. Input a TPE pattern sentence and a parsed sentence.
    	 *   - pattern: TPE pattern sentence
    	 *   - sentence: A parsed sentence (It can be obtained from the Stanford NLP full parser.)
    	 */
    	
    	// 평서문 예제 1
//    	String pattern = "<S <NP.* * <NN.*|PR.* .+> *> * <VP.* * <VB.* .+> <NP * <NN.*|RP.* .+> * > * > * >";
//    	String sentence = "<S <NP <PRP I>> <VP <VBP like> <NP <DT the> <NN movie>>> <. .>>";


    	// 평서문 예제 2    	
//    	String pattern = "<S <NP * <NN.* .+> * > * <VP * <VB.* .+> * <NP * <NP * <NN.* .+> *> <PP <IN .+> <NP * <NN.* .+> * > * > * > * > * >";
//    	String sentence = "<S <NP <NNP Andong>> <VP <VBZ is> <NP <NP <DT a> <JJ historical> <NN city>> <PP <IN in> <NP <NN Korea>>>>> <. !>>";
//
    	// 의문문 예제
//    	String pattern = "{SBARQ * {WH.* <WRB .+> <RB|JJ .+> * <NN.* .+> * } {SQ * } * }"; // How + many/much + Noun (의문문에서 동사와 그 뒷 부분은 제외한 앞부분 (= 의문사 부분)만 매칭하는 패턴)
////        String pattern = "{{WH.* <WRB .+> <RB|JJ .+> * <NN.* .+> * } {SQ * } * }";
////    	String sentence = "<SBARQ <PP <IN On> <NP <NN average>>> <, ,> <WHNP <WHADJP <WRB How> <JJ many>> <NNS miles>> <SQ <VBP are> <NP <EX there>> <PP <TO to> <NP <DT the> <NN moon>>>> <. ?>>";
//        String sentence = "<SBARQ <WHNP <WHADJP <WRB How> <JJ many>> <NNS people>> <SQ <VP <VBP work> <PP <IN in> <NP <PRP$ your> <NN company>>>>> <. ?>>";
    	// On average, How many miles are there to the moon?
//        String sentence = "<SBARQ <WHNP <WHADJP <WRB How> <JJ many>> <NNS times>> <SQ <VP <VBP have> <NP <NP <DT any>> <PP <IN of> <NP <NP <PRP us>> <VP <VBN been> <VP <VBN involved> <PP <IN in> <NP <NP <DT an> <NN event>> <ADJP <RB remotely> <JJ exciting>>>>>>>>>>> <. ?>>";



//        String pattern = "<S <NP.* <DT .+> <NN.* .+> *> <VP <VB.* .+> <NP <NN.* .+> * >"; // 이건 the physicists가 나오지만 (여기서는 the physicists playing badmitton)
//        // <S <NP.* * <NN.* .+> *> *> 는 the가 빠짐. 빼고 싶은 거 있으면 그냥 * 처리 해버리면 됨
//        String sentence = "<NP <DT the> <JJ wonderful> <NN scene>> <PP <IN with> <S <NP <DT the> <NNS physicists>> <VP <VBG playing> <NP <NN badmitton>>>>>";


//        String pattern = "<S <NP <NP * <NN.* .+> *> *> <VP <VB.* .+> <NP <NP <DT.* .+> <JJ.* .+> <NN.* .+> *> *> *> *>";
//        String pattern = "<S <NP <(PR.*|NN.*) .+> <VP <VB.* .+> <NP <NP <NN.* .+> *> *> *> *>";
//        String pattern = "<S <NP <NP * <(NN.*|PRP) .+> *> *> <VP <VB.* .+> <NP <NP <DT.* .+> <JJ.* .+> <NN.* .+> *> *> *> *>";

//        String pattern = "<S <NP <NP <PR.* .+> <JJ.* .+> <NN.* .+> *> *> <VP <VB.* .+> <NP <NP <DT.* .+> <JJ.* .+> <NN.* .+> *> *> *> *>"; // my favorite part was the wonderful scene
//        String pattern = "<S <NP <NP * <(NN.*|PRP) .+> *> *> <VP <VB.* .+> <NP <NP * <NN.* .+> *> *> *> *>";    // part was scene -> 이건 일단 제외
//        String pattern = "<S <NP <(NN.*|PRP) .+> *>  <VP <VB.* .+> <PP <IN .+> <NP <NN.* .+> * > *> * > *>"; // they waited for Robbins
//        String pattern = "<S * <NP <PRP .+>> <VP <VB.* .+> <NP <NP *  <NN.* .+> *> *> *> *>"; // I loved sweaters
        String pattern = "<NP <NP * <JJ.* .+> <NN.* .+> *> <PP <IN .+> <NP * <VB.* .+> <NN.* .+> * > *> *>";    // wonderful scene with playing badmitton
//        String pattern = "<VP * <VP <VB.* .+> <NP <PR.* .+> * >*> * <VP <VB.* .+> <PRT <RP.* .+> *> <PP <IN .+> <NP <PR.* .+> <NN.* .+> *> *> *>"; // make me go out of my way
//        String pattern = "<S <NP <PR.* .+> *> <VP <VB.* .+> *> *>"; // I think SBAR
//        String pattern = "<S <NP <PR.* .+> *> <VP <VB.* .+> <S.* <S  <NP <NP * <(NN.*|PRP) .+> *> *> *> *> *> *>";

//        String pattern = "<S.* <S  <NP <NP * <(NN.*|PRP) .+> *> *> *> *>";
//        String pattern = "<S.* <S <NP <PRP .+ > *> *> *>";

//        String pattern = "<NP * <VB.* .+> <NN.* .+> * > ";
//        String pattern = "<NP <DT.* .+> <JJ.* .+> <NN.* .+> *> ";

//        String pattern = "";
//        String sentence = "<ROOT <S <NP <PRP she>> <VP <VBZ is> <ADVP <RB so> <RB pretty>> <NP <NP <DT all>> <PP <IN over> <NP <DT the> <NN world>>>>> <. .>>>";
//        String sentence = "<ROOT <S <NP <PRP I>> <VP <VBP think> <SBAR <S <NP <NP <DT the> <NN majority>> <PP <IN of> <NP <DT the> <NNS people>>>> <VP <VBP seem> <S <NP <RB not> <DT the>> <VP <VB get> <NP <NP <DT the> <JJ right> <NN idea>> <PP <IN about> <NP <NP <DT the> <NN movie>> <, ,> <ADVP <IN at> <JJS least>> <SBAR <WHNP <DT that>> <S <VP <VBZ 's> <NP <PRP$ my> <NN opinion>>>>>>>>>>>>>> <. .>>>";
//        String sentence = "<ROOT <S <NP <PRP I>> <VP <VBP think> <SBAR <S <NP <PRP we>> <VP <VBP have> <NP <NP <DT a> <NN lot>> <PP <IN in> <NP <JJ common>>>>>>>> <. .>>>";
//        String sentence = "<S <NP <PRP I>> <VP <VBP think> <SBAR <S <NP <NP <DT the> <NN majority>> <PP <IN of> <NP <DT the> <NNS people>>>> <VP <VBP seem> <S <NP <RB not> <DT the>> <VP <VB get> <NP <NP <DT the> <JJ right> <NN idea>> <PP <IN about> <NP <NP <DT the> <NN movie>> <, ,> <ADVP <IN at> <JJS least>> <SBAR <WHNP <DT that>> <S <VP <VBZ 's> <NP <PRP$ my> <NN opinion>>>>>>>>>>>>>> <. .>>";
        String sentence = "<ROOT <S <S <NP <NP <PRP$ My> <JJ favorite> <NN part>> <, ,> <CC and> <NP <NP <DT the> <JJ only> <NN thing>> <SBAR <WHNP <WDT that>> <S <VP <MD would> <VP <VB make> <NP <PRP me>>> <, ,> <VP <VB go> <PRT <RP out>> <PP <IN of> <NP <PRP$ my> <NN way> <S <VP <TO to> <VP <VB see> <NP <DT this>> <ADVP <RB again>>>>>>>>>>>> <, ,>> <VP <VBD was> <NP <NP <DT the> <JJ wonderful> <NN scene>> <PP <IN with> <NP <DT the> <NN physicists> <VBG playing> <NN badmitton>>>>>> <, ,> <NP <PRP I>> <VP <VBD loved> <NP <NP <DT the> <NNS sweaters>> <CC and> <NP <DT the> <NN conversation>>> <SBAR <IN while> <S <NP <PRP they>> <VP <VBD waited> <PP <IN for> <NP <NNP Robbins>>> <S <VP <TO to> <VP <VB retrieve> <NP <DT the> <NN birdie>>>>>>>>> <. .>>>";


    	// 1.Define a pattern tree        
        Patterns p = new Patterns(pattern);
        
        // 2. Define a target tree
//        System.out.println("\n\nTarget Tree: ");
        MakeTree tMT = new MakeTree();
        TPETree t = new TPETree();
        TreeNode tRoot = null;
        
        String[] str2 = tMT.getStringArray(sentence);
        tRoot = tMT.getTree(str2);
        t.setRootNode(tRoot);
        t.initTree();
        
        t.printPostOrderingOfNodes(tRoot);
        
        
        // 3. Try to match the pattern tree p to the target tree t.
//        System.out.println("\n\nMatched relations: ");
        String relation = null;
        try{
	        relation = p.patternMatching(t);
	        if (relation == null) return; // Not matched.

	        System.out.println(relation);
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

            int to = Integer.parseInt(rel2[1]);

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
        for(int index = 0; index < fruits.size(); index++){
            System.out.print(fruits.get(index) + " ");
        }



    }

}