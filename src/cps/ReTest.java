package cps;

import org.junit.Test;

import static cps.Re.*;
import static org.junit.Assert.assertTrue;

public class ReTest {
    @Test
    public void should_match() {
        assertTrue(lit('a').match("a"));
        assertTrue(!lit('b').match("a"));
        assertTrue(either(lit('a'), lit('b')).match("b"));
        assertTrue(seq(lit('a'), lit('b')).match("ab"));
        assertTrue(seq(lit('a'), lit('b')).match("ab"));
        assertTrue(star(lit('a')).match("aa"));
        assertTrue(!atLeastOne(lit('a')).match(""));
    }
}
