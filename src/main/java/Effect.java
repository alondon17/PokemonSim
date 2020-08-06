public class Effect {
    private EffectType eCode;
    private int eChance;
    private int statusStage;
    public Effect(EffectType eCode, int eChance, int statusStage){
        this.eCode =eCode;
        this.eChance = eChance;
        this.statusStage=statusStage;
    }
    public Effect(EffectType eCode, int eChance){
        this.eCode =eCode;
        this.eChance = eChance;
        this.statusStage=0;
    }

    public EffectType eCode() {
        return eCode;
    }
}
enum EffectType{
    BRN,
    FRZ,
    PAR,
    PSN,
    BPSN,
    SLP;
}
