import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SequenceTest {

    private Sequence s;

    @Before
    public void setUp(){
        s = new Sequence();
    }

    // Even spaces tests
    @Test
    public void testReturnBestXEven(){
        String x = "TAAGGTCA";
        String y = "AACAGTTACC";
        String bestX = "TA_AGGT_CA";
        s.startSequence(x, y);
        assertEquals(s.getBestX(), bestX);
    }

    @Test
    public void testReturnBestYEven(){
        String x = "TAAGGTCA";
        String y = "AACAGTTACC";
        String bestY = "AACAGTTACC";
        s.startSequence(x, y);
        assertEquals(s.getBestY(), bestY);
    }

    // Odd spaces tests
    @Test
    public void testReturnBestXOdd(){
        String x1 = "AACGTAGAC";
        String y1 = "GACATATTAC";
        String bestX1 = "AACGTA_GAC";
        s.startSequence(x1, y1);

        assertEquals(s.getBestX(), bestX1);
    }

    @Test
    public void testReturnBestYOdd(){
        String x1 = "AACGTAGAC";
        String y1 = "GACATATTAC";
        String bestY1 = "GACATATTAC";
        s.startSequence(x1, y1);

        assertEquals(s.getBestY(), bestY1);
    }
}