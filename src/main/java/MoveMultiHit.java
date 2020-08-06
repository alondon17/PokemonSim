public class MoveMultiHit extends MoveNorm{
    private int numOfHits;

    public int getNumOfHits() {
        switch (this.id())
        {
            // Double Slap
            case 3:
                return 2+ Methoder.multiChance(new double[]{37.5, 37.5,12.5,12.5});
            default:
                return numOfHits;
        }
    }

    public MoveMultiHit(int id, int type, String name, int power, boolean isPhys, int accuracy, int pp, int numOfHits) {
        super(id, type, name, power, isPhys, accuracy, pp);
        this.numOfHits = numOfHits;
    }


}
