public class EV {
    private static final int MAX_TOTAL = 510;
    private static final int MAX_EV = 510;
    private int HP = 0;
    private int ATTACK = 0;
    private int DEFENCE = 0;
    private int SP_ATTACK = 0;
    private int SP_DEFENCE = 0;
    private int SPEED = 0;
    private int total = 0;

    public int hp() {
        return HP;
    }

    private void hpAdd(byte num) {
        int add = Math.min(Math.min(num, MAX_TOTAL - total), MAX_EV - hp());
        HP += add;
        total += add;
    }

    public int atk() {
        return ATTACK;
    }

    private void atkAdd(byte num) {
        int add = Math.min(Math.min(num, MAX_TOTAL - total), MAX_EV - atk());
        ATTACK += add;
        total += add;
    }

    public int def() {
        return DEFENCE;
    }

    private void defAdd(byte num) {
        int add = Math.min(Math.min(num, MAX_TOTAL - total), MAX_EV - def());
        DEFENCE += add;
        total += add;
    }

    public int spAtk() {
        return SP_ATTACK;
    }

    private void spAtkAdd(byte num) {
        int add = Math.min(Math.min(num, MAX_TOTAL - total), MAX_EV - spAtk());
        SP_ATTACK += add;
        total += add;
    }

    public int spDef() {
        return SP_DEFENCE;
    }

    private void spDefAdd(byte num) {
        int add = Math.min(Math.min(num, MAX_TOTAL - total), MAX_EV - spDef());
        SP_DEFENCE += add;
        total += add;
    }

    public int speed() {
        return SPEED;
    }

    private void speedAdd(byte num) {
        int add = Math.min(Math.min(num, MAX_TOTAL - total), MAX_EV - speed());
        SPEED += add;
        total += add;
    }

    public void addEvs(byte[] arr) {
        for (int i = 0; i < 6 && i < arr.length; i++) {
            if (arr[i] > 0 && arr[i] < 4 && total < MAX_TOTAL) {
                switch (i) {
                    case 0 -> hpAdd((arr[i]));
                    case 1 -> atkAdd((arr[i]));
                    case 2 -> defAdd((arr[i]));
                    case 3 -> spAtkAdd((arr[i]));
                    case 4 -> spDefAdd((arr[i]));
                    case 5 -> speedAdd((arr[i]));
                }
            }
        }
    }
}
