import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SequenceTest {

    private Sequence s;
    private String bestX;
    private String bestY;

    @Before
    public void setUp(){
        String x = "TAAGGTCA";
        String y = "AACAGTTACC";
        bestX = "TA_AGGT_CA";
        bestY = "AACAGTTACC";

        s = new Sequence(x, y);
        s.startSequence();
    }


    @Test
    public void testReturnBestX(){
        assertEquals(s.returnBestX(), bestX);
    }

    @Test
    public void testReturnBestY(){
        assertEquals(s.returnBestY(), bestY);
    }
}