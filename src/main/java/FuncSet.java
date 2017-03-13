import com.sun.tools.javac.util.Pair;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by Ivan Ermolaev
 * ermolaevym@gmail.com
 */
public class FuncSet {

    private static final int BOUNDS = 1000;
    public interface Set extends Function<Integer, Boolean> {}

    public static Set singletonSet(Integer value) { return x -> value == x; }
    public static Boolean contains(Set s, Integer value) { return s.apply(value); }
    public static Set union(Set s1, Set s2) { return x -> s1.apply(x) || s2.apply(x); }
    public static Set intersect(Set s1, Set s2) { return x -> s1.apply(x) && s2.apply(x); }
    public static Set diff(Set s1, Set s2) { return x -> s1.apply(x) && !s2.apply(x); }
    public static Set filter(Set s1, Function<Integer, Boolean> filter) { return x -> s1.apply(x) && filter.apply(x); }

    public static Boolean checkForAll(Set s1, Function<Integer, Boolean> funcPredicate) {
        BiFunction<BiFunction, Integer, Boolean> funcIteration =
                (func, index) -> {
                    if(s1.apply(index) && !funcPredicate.apply(index)) return false;
                    else if(index == BOUNDS) return true;
                    else return (Boolean)func.apply(func, index+1);
                };
        return funcIteration.apply(funcIteration, -BOUNDS);
    }

    public static Boolean checkForFirst(Set s1, Function<Integer, Boolean> funcPredicate) {
        BiFunction<BiFunction, Integer, Boolean> funcIteration =
                (func, index) -> {
                    if(s1.apply(index) && funcPredicate.apply(index)) return true;
                    else if(index == BOUNDS) return false;
                    else return (Boolean)func.apply(func, index+1);
                };
        return funcIteration.apply(funcIteration, -BOUNDS);
    }

    public static Set setFrom(Integer... values) {

        BiFunction<BiFunction, Pair<Set, Integer[]>, Set> funcIteration =

                (BiFunction func, Pair<Set, Integer[]> pair) -> {
                    if(pair.snd == null) return pair.fst;
                    else return (Set)func.apply(
                            func,
                            new Pair<> (
                                    pair.fst == null ?
                                            singletonSet(getLast(pair.snd))
                                            :
                                            union(pair.fst, singletonSet(getLast(pair.snd))),
                                    getShorter(pair.snd)
                            )
                    );
                };
        return funcIteration.apply(funcIteration, new Pair<>(null, values));
    }

    public static Integer[] getShorter(Integer[] values) {
        if(values.length - 1 == 0) {
            return null;
        }
        else {
            return Arrays.copyOfRange(values, 0, values.length-1);
        }
    }

    public static Integer getLast(Integer[] values) {
        return values[values.length-1];
    }
}
