package Project;

@FunctionalInterface
public interface EffectApplicator<A,B,C,D,E,F> {
    F apply(A a,B b,C c,D d,E e);
}
