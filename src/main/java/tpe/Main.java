package tpe;

import tpe.datastructure.PairMatched;

import java.util.ArrayList;

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
//    	String pattern = "<S <NP.* * <NN.*|PR.* .+> *> <VP.* * <VB.* .+> <NP * <NN.*|RP.* .+> * <NN.*|RP.* .+> * > * > * >";
//    	String sentence = "<S <NP <PRP I>> <VP <VBP like> <NP <DT the> <NN horror> <NN movie>>> <. .>>";

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


//        String pattern = "<S <NP.* <DT .+> <NN.* .+> *> <VP <VB.* .+> <NP <NN.* .+> * >"; // 이건 the physicists가 나오지만 (여기서는 the physicists playing badmitton)
//        // <S <NP.* * <NN.* .+> *> *> 는 the가 빠짐. 빼고 싶은 거 있으면 그냥 * 처리 해버리면 됨
//        String sentence = "<NP <DT the> <JJ wonderful> <NN scene>> <PP <IN with> <S <NP <DT the> <NNS physicists>> <VP <VBG playing> <NP <NN badmitton>>>>>";

        String pattern = "<NP <NP.* * <NN.* .+> * > <VP <VB.* .+> <NP <NN.* .+> * > * >";
//        String sentence = "<NP <NP <DT the> <NNS physicists>> <VP <VBG playing> <NP <NN badmitton>>>>";
//        String pattern = "<S <NP.* * <NN.*|PR.* .+> * > * >";
//        String pattern = "<NP <DT.* .+> <JJ.* .+> <NN.* .+> *>";
//        String pattern = "<NP <DT.* .+> <JJ.* .+> <NN.* .+> <PP * <IN.* .+> <NP.* <NN.* .+> * > * >";
////        String pattern = "<S <NP * <(NN.*|PR.*).+> *> * <VP * <V.+ .+> * <NP * <(NN.*|PR.*) .+> *> *> *>";
////        String pattern = "<S <NP * <NN.* .+> * > * <VP * <VB.* .+> * <NP * <NP * <NN.* .+> *> <PP <IN .+> <NP * <NN.* .+> * > * > * > * > * >";
        String sentence = "<S <S <NP <NP <PRP$ My> <JJ favorite> <NN part>> <, ,> <CC and> <NP <NP <DT the> <JJ only> <NN thing>> <SBAR <WHNP <WDT that>> <S <VP <MD would> <VP <VB make> <S <NP <PRP me>> <, ,> <VP <VB go> <ADVP <RB out>> <PP <IN of> <NP <PRP$ my> <NN way> <S <VP <TO to> <VP <VB see> <NP <DT this>> <ADVP <RB again>>>>>>>>>>>>>> <, ,>> <VP <VBD was> <NP <NP <DT the> <JJ wonderful> <NN scene>> <PP <IN with> <NP <NP <DT the> <NNS physicists>> <VP <VBG playing> <NP <NN badmitton>>>>>>>> <, ,> <NP <PRP I>> <VP <VBD loved> <NP <NP <DT the> <NNS sweaters>> <CC and> <NP <DT the> <NN conversation>>> <SBAR <IN while> <S <NP <PRP they>> <VP <VBD waited> <PP <IN for> <NP <NNS Robbins>>> <S <VP <TO to> <VP <VB retrieve> <NP <DT the> <NN birdie>>>>>>>>> <. .>>";

    	// 1.Define a pattern tree        
        Patterns p = new Patterns(pattern);
        
        // 2. Define a target tree
        System.out.println("\n\nTarget Tree: ");
        MakeTree tMT = new MakeTree();
        TPETree t = new TPETree();
        TreeNode tRoot = null;
        
        String[] str2 = tMT.getStringArray(sentence);
        tRoot = tMT.getTree(str2);
        t.setRootNode(tRoot);
        t.initTree();
        
        t.printPostOrderingOfNodes(tRoot);
        
        
        // 3. Try to match the pattern tree p to the target tree t.
        System.out.println("\n\nMatched relations: ");
        String relation = null;
        try{
	        relation = p.patternMatching(t);
	        if (relation == null) return; // Not matched.
	        
	        System.out.println(relation);
        }catch(Exception e){
        	System.err.println("main Exception -- patternMatching() part: "+e.getMessage());
        	return;
        } 
        
        
        ////////////////////////////// Test session ////////////////////////////////
        // Print matched words
        System.out.println("\n\nPrint matched words: ");
        String[] rel = relation.split("::");
        for(int i=0 ; i < rel.length; i++){
        	String rel2[] = rel[i].split(",");
        	if(rel2.length!=2) continue;
        	System.out.print(rel[i]+" --> ");
        	System.out.println(p.p.getIthNode(Integer.parseInt(rel2[0])).value+", "+t.getIthNode(Integer.parseInt(rel2[1])).value);
        	//System.exit(0);
        }
        
        // Load matched words into array list
        System.out.println("\n\nLoad matched words into array list: ");
        ArrayList<PairMatched> list = null;
        list = p.patternMatchingToList(t);
        
    }

}