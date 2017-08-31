package kata;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jacky on 2017/8/31.
 */
public class TennisTest {
    @Test
    public void score() {
        assertEquals("love all", Tennis.score(0, 0));
        assertEquals("fifteen all", Tennis.score(1, 1));
        assertEquals("thirty all", Tennis.score(2, 2));

        assertEquals("fifteen love", Tennis.score(1, 0));
        assertEquals("thirty love", Tennis.score(2, 0));

        assertEquals("player one win", Tennis.score(4, 2));
        assertEquals("player one advantage", Tennis.score(5, 4));

        assertEquals("deuce", Tennis.score(3, 3));
        assertEquals("deuce", Tennis.score(4, 4));

        assertEquals("player two win", Tennis.score(2, 4));

    }
}