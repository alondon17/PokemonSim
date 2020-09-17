import com.sun.jdi.Value;

import java.security.KeyStore;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;
//
//public class EffectManager {
//    public static HashMap<EffectType,Effect> battleStartReset(HashMap<EffectType,Effect> effects) {
//        return (HashMap<EffectType, Effect>) effects.values().stream()
//                .filter(value->(shouldKeepEffect(value.eCode())))
//                .collect(Collectors.toMap(Effect::eCode, effect -> effect));
//    }
//    public static boolean shouldKeepEffect(EffectType effectType){
//        switch (effectType){
//            case BRN,FRZ,PAR,BPSN,PSN,SLP:
//                return true;
//            default:return false;
//        }
//    }
//}
