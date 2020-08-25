import java.util.ArrayList;
import java.util.HashMap;

public class TurnOrderer {
    Integer[] choices;
    HashMap<Integer,TurnChoice> choiceMap;
    int currNum;
    TurnOrderer(int numOfPokemon){
        choiceMap=new HashMap<>();
        choices=new Integer[numOfPokemon*2];
        currNum=0;
    }
    void put(TurnChoice choice){
        choiceMap.put(choice.getUserLoc(),choice);
        choices[currNum]=choice.getUserLoc();
        currNum++;
    }
    TurnChoice getChoice(){
        TurnChoice ret=choiceMap.get(choices[0]);
        pushBack(1);
        return ret;
    }
    void sort(){

            int i, j;
            TurnChoice newValue;
            for (i = 1; i < currNum; i++) {
                newValue = choiceMap.get(choices[i]);
                j = i;
                while (j > 0 && (choiceMap.get(choices[j - 1]).compareTo(newValue)>0)) {
                    choices[j] = choices[j - 1];
                    j--;
                }
                choices[j] = newValue.getUserLoc();
            }

    }
    private void pushTo(int from,int to){
        assert from<currNum:"Illegal from value";
        assert to<currNum:"Illegal to value";
        Integer temp=choices[from];
        if(from<to){
            for (int i = from+1; i <=to; i++) {
                choices[i-1]=choices[i];

            }
            choices[to]=temp;
        }
        if(from>to){
            for (int i = from; i >to; i--) {
                choices[i]=choices[i-1];

            }
            choices[to]=temp;
        }

    }
    private void pushBack(int from){
        for (int i = from; i < currNum; i++) {
           choices[i-1]=choices[i];
           choices[i]=null;
        }
        currNum--;
    }
    public int size(){
        return currNum;
    }
//    private void pushForward(int from,TurnChoice insert){
//        for (int i = currNum-1; i >from; i--) {
//            choices[i]=choices[i-1];
//
//        }
//        choices[from]=insert;
//
//    }
}
