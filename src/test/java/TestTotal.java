/**
 * Created by freddy on 16.04.17.
 */

import org.junit.Test;
import org.softlang.operations.Total;

import static junit.framework.Assert.assertEquals;

public class TestTotal {

    @Test
    public void testTotal() {
        Total t = new Total();
        double total = t.total();
        assertEquals(326927, total, 0);
    }
}
