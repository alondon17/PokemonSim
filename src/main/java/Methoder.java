import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Methoder {
    private final static  int INVALID_INPUT_NUM=-1;
    private static Random rnd=new Random();
    private static Scanner scanner=new Scanner(System.in);
    public static Pokemon[] listToArr(ArrayList<Pokemon> arrayList){
        Pokemon[] arrPoke=new Pokemon[arrayList.size()];
        int i=0;
        for (Pokemon pkmn:arrayList )
        {
            arrPoke[i]=pkmn;
            i++;
        }
        return arrPoke;
    }
    public static int multiChance(double[] chances)
    {
        double range =sumArr(chances);
        double rndResult=Math.random()*range;
        double passedSum=0;
        int i=0;
        while (true){
            if (rndResult>=passedSum+chances[i]){
                passedSum+=chances[i];
                i++;
            }
            else {
                return i;
            }


        }
    }
    public static double sumArr(double[] values){
        double sum=0;
        for (double value : values) {
            sum += value;
        }
        return sum;
    }
    public static Pokemon[] arrayShorten(Pokemon[] player) {
        int length = 0;
        boolean isLegal=true;
        for (Pokemon pokemon : player) {
            if (pokemon != null)
                length++;
            else isLegal=false;
        }
        if (length == 0) {
            return new Pokemon[0];
        }
        if(isLegal)
            return player;
        Pokemon[] newArr = new Pokemon[length];
        int i = 0;
        for (Pokemon pokemon : player) {
            if (pokemon != null) {
                newArr[i] = pokemon;
                i++;
            }
        }
        return newArr;
    }
    public static int getInt(){
        String s=scanner.next();
        int i;
        try{
            i=Integer.parseInt(s);
        }
        catch (NumberFormatException nfe){
            return INVALID_INPUT_NUM;
        }
        return i;
    }
    public  static int[] toIntArr(Array arr) throws SQLException {
        Number[] covArr=(Number[]) arr.getArray();
        int[] newArr=new int[covArr.length];
        for (int i = 0; i < covArr.length; i++) {
            newArr[i]= covArr[i].intValue();
        }
        return newArr;
    }
    public static int random(int upperLimit){
        return rnd.nextInt(upperLimit);
    }
}
