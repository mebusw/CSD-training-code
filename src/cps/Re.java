package cps;

import java.util.function.Function;

/**
 * (Continuous Passing Style)
 * https://gist.github.com/yuanmai/a9b5b96a6bea5f1402a1e2790136e52f
 * 用 CPS 实现字符串匹配。基于讲座 https://youtu.be/cnhb4M8-J5M
 */
public interface Re {

    boolean match(String s, Function<String, Boolean> cont);

    default boolean match(String s) {
        return match(s, cont -> true);
    }

    /**
     * 匹配字符
     *
     * @param charLiteral
     * @return
     */
    static Re lit(char charLiteral) {
        return (s, cont) -> {
            if (s.isEmpty()) {
                return false;
            }
            return charLiteral == s.charAt(0) && cont.apply(s.substring(1));
        };
    }

    /**
     * 按顺序匹配a b
     *
     * @param a
     * @param b
     * @return
     */
    static Re seq(Re a, Re b) {
        return (s, cont) -> a.match(s, rest -> b.match(rest, cont));
    }

    /**
     * 匹配a或b
     *
     * @param a
     * @param b
     * @return
     */
    static Re either(Re a, Re b) {
        return (s, cont) -> a.match(s, cont) || b.match(s, cont);
    }

    /**
     * 匹配0到N个
     *
     * @param re
     * @return
     */
    static Re star(Re re) {
        return (s, cont) -> re.match(s, rest -> star(re).match(rest, cont))
                || cont.apply(s);
    }

    static Re atLeastOne(Re re) {
        return seq(re, star(re));
    }
}