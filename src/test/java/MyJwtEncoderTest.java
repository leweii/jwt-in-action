import org.junit.Test;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MyJwtEncoderTest {

    @Test
    public void test() throws NoSuchAlgorithmException, InvalidKeyException {
        MyJwtEncoder encoder = new MyJwtEncoder();
        System.out.println(encoder.encode());
    }

    @Test
    public void testSign() throws NoSuchAlgorithmException, InvalidKeyException {
        MyJwtEncoder encoder = new MyJwtEncoder();
        System.out.println(encoder.encode());
    }
}