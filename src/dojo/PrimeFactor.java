package dojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jacky on 14-11-12.
 */
public class PrimeFactor {
    public static List factors(int i) {
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
}
