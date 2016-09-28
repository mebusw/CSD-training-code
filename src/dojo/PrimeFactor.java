package dojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jacky on 14-11-12.
 */
public class PrimeFactor {
    public static List factors1(int i) {
        List result = new ArrayList();
        for (int factor = 2; factor < i; factor++) {
            while (i % factor == 0 && i != factor) {
                result.add(factor);
                i = i / factor;
            }
        }
        result.add(i);
        return result;
    }

    public static List factors(int n) {
        return factors2(n, 2);
    }

    public static List factors2(int n, int p) {
        List lst = new ArrayList();
        if (n < 2) return lst;
        if (n % p == 0) {
            lst.add(p);
            lst.addAll(factors2(n / p, p));
            return lst;
        }
        return factors2(n, p + 1);
    }
}
