package tpe;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CoreNLP_Parser Cparser = new CoreNLP_Parser();
//        String sentence = Cparser.Core_parser("Most people think of the homeless as just a lost cause while worrying about things such as racism, the war on Iraq, pressuring kids to succeed, technology, the elections, inflation, or worrying if they will be next to end up on the streets. ");
        Split_Sentence split_s = new Split_Sentence();
//        String sentence = "<ROOT <S <NP <NP <DT A> <JJ formal> <NN orchestra> <NN audience>> <SBAR <S <VP <VBZ is> <VP <VBN turned> <PP <IN into> <NP <DT an> <ADJP <JJ insane> <, ,> <JJ violent>> <NN mob>>> <PP <IN by> <NP <NP <DT the> <JJ crazy> <NNS chantings>> <PP <IN of> <NP <PRP it>>>>>>>>>> <VP <VBZ is> <NP <NNS singers>>> <. .>>>";
//        String sentence = "<ROOT <S <NP <JJS Most> <NNS people>> <VP <VBP think> <PP <IN of> <NP <NP <DT the> <NN homeless>> <CONJP <RB as> <RB just>> <NP <NP <DT a> <VBN lost> <NN cause>> <PP <IN while> <S <VP <VP <VBG worrying> <PP <IN about> <NP <NP <NNS things>> <PP <JJ such> <IN as> <NP <NP <NN racism>> <, ,> <NP <NP <DT the> <NN war>> <PP <IN on> <NP <NNP Iraq>>>>>>>>> <, ,> <VP <VBG pressuring> <NP <NNS kids>> <S <VP <TO to> <VP <VB succeed> <, ,> <NP-TMP <NP <NN technology>> <, ,> <NP <DT the> <NNS elections>>> <, ,> <NP <NN inflation>>>>>> <, ,> <CC or> <VP <VBG worrying> <SBAR <IN if> <S <NP <PRP they>> <VP <MD will> <VP <VB be> <ADJP <JJ next> <S <VP <TO to> <VP <VB end> <PRT <RP up>> <PP <IN on> <NP <DT the> <NNS streets>>>>>>>>>>>>>>>>>>> <. .>>>";
//        ArrayList<String> splited_sentence = split_s.split_sentence("How many miles are there to the moon? How many times do I have to tell you?");
//        String pattern = "<NP * <JJ .+> <NN .+> *>";
//        String pattern = "<S <NP * <NN.*|PR.* .+>> <VP <VB.* .+> <VP <VB.* .+> <PP <IN .+> <NP * <NN .+> *> *> *> *> *>";
//        String pattern = "<S <NP * <NNS .+> *> <VP <VB.* .+> *> *>"; // Most people think. 가장 기본적인 S - NP VP 구조에서 가장 앞 부분을 추출해내는 패턴.
        /* 0. Input a TPE pattern sentence and a parsed sentence.
    	 *   - pattern: TPE pattern sentence
    	 *   - sentence: A parsed sentence (It can be obtained from the Stanford NLP full parser.)
    	 */
    	
    	// 평서문 예제 1
//    	String pattern = "<S <NP.* * <NN.*|PR.* .+> *> * <VP.* * <VB.* .+> <NP * <NN.*|RP.* .+> * > * > * >";
//    	String sentence = "<S <NP <PRP I>> <VP <VBP like> <NP <DT the> <NN movie>>> <. .>>";


        String sentence = Cparser.Core_parser("Titanic is the greatest movie of the 21st Century.With great acting,directing,effects,music and generally everything. ");
//        String sentence = Cparser.Core_parser("Artists are people who draw pictures");
//        String pattern = "<ROOT <S <NP <(PR.*|NN.*) .+>> <VP <VB.* .+> <NP <NP * <(PR.*|NN.*) .+>> <SBAR <WHNP <WP .+>> <S <VP <VBP .+> >>>>>>>";
//        String sentence = Cparser.Core_parser("The movie has very few good points to talk about.");
//        String pattern = "{ ROOT <S <NP * <NN movie>> <VP <VBZ has>  {S <RB very> <JJ few> <JJ .+> <NNS .+> {VP <TO .+> <VB .+> <IN .+>}*>*> *} }";
//        String pattern = "{S <RB very> *  <TO .+> <VB .+> <IN .+>}";
//        String pattern = "{ ROOT <S <NP * <NN movie>> <VP <VBZ has>  {S <RB very> <JJ few> <JJ .+> <NNS .+> <VP <TO to> <VP <VB talk> <PP <IN about>>>>>> *} }";
//        String pattern = "{ROOT {S {NP * <(PR.*|NN.*) .+>} {VP <VB.* .+> *} *}}"; // 1형식 문장
        String pattern = "{S <(PR.*|NN.*) .+> <VB.* .+> * {NP * <JJ.* .+> <(PR.*|NN.*) .+>} *}";




//        String pattern = "<ROOT * <S * <NP * <(PR.*|NN.*) .+>*> * <VP <VB.* .+> * <NP <NP <(PR.*|NN.*) .+>*> * <SBAR <WHNP <WP .+> *> <S <VP <VBP .+> * <NP <NNS .+>* >*>*>*>*>*>*>*>";


    	// 평서문 예제 2    	
//    	String pattern = "<S <NP * <NN.* .+> * > * <VP * <VB.* .+> * <NP * <NP * <NN.* .+> *> <PP <IN .+> <NP * <NN.* .+> * > * > * > * > * >";
//    	String sentence = "<S <NP <NNP Andong>> <VP <VBZ is> <NP <NP <DT a> <JJ historical> <NN city>> <PP <IN in> <NP <NN Korea>>>>> <. !>>";
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
        Patterns p = new Patterns(pattern);  // 필수

        // 2. Define a target tree
//        System.out.println("\n\nTarget Tree: ");

        MakeTree tMT = new MakeTree();  // 필수
        TPETree t = new TPETree();  // 필수
        TreeNode tRoot = null;  // 필수

        String[] str2 = tMT.getStringArray(sentence);   // 필수
        tRoot = tMT.getTree(str2);  // 필수
        t.setRootNode(tRoot);   // 필수
        t.initTree();   // 필수
        t.printPostOrderingOfNodes(tRoot);  // 필수

        // 3. Try to match the pattern tree p to the target tree t.
//        System.out.println("\n\nMatched relations: ");
        String relation = null;
        try{
	        relation = p.patternMatching(t);    // 일단은 이 부분만 필수

	        if (relation == null){
	            relation = ":";
//	            return; // Not matched.
            }

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
//        System.out.println(fruits);
        String sum_text = String.join(" ",fruits);
        System.out.println(sum_text);



    }

}