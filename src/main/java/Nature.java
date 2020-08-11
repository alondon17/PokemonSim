import java.util.HashMap;
import java.util.InputMismatchException;

public class Nature {
    public static String[] s;
    private int num;

    static {
        s = new String[]{"Hardy", "Lonely", "Brave", "Adamant", "Naughty",
                "Bold", "Docile", "Relaxed", "Impish", "Lax",
                "Timid", "Hasty", "Serious", "Jolly", "Naive",
                "Modest", "Mild", "Quiet", "Bashful", "Rash",
                "Calm", "Gentle", "Sassy", "Careful", "Quirky"};
    }

    public double[] toArr() {
        double[] arr = new double[]{1, 1, 1, 1, 1};
        arr[num/5]+=0.1;
        arr[num%5]-=0.1;
        return arr;

    }

    public Nature(int num) throws InputMismatchException {
        if(num>24||num<0)
            throw new InputMismatchException("Illegal nature value");
        else this.num=num;
    }
    public String toString(){
        return s[num];
    }
}
