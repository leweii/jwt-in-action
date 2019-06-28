import com.google.gson.Gson;
import com.sun.jndi.toolkit.url.UrlUtil;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class MyJwtEncoder {

    public String encode() throws InvalidKeyException, NoSuchAlgorithmException {
        //{"sub":"hello-world"}
        Map header = new HashMap();
        header.put("alg", "HS256");
        Map payload = new HashMap();
        payload.put("sub", "hello-world");

        String encoder = encodeHeader(header) + "." + encodePayload(payload);
        return encoder + "." + sign(encoder, "lewei");
    }

    private String encodeHeader(Map<String, String> header) {
        Gson gson = new Gson();
        String headerJson = gson.toJson(header);
        return Base64.getEncoder().encodeToString(headerJson.getBytes(StandardCharsets.UTF_8));

    }

    private String encodePayload(Map<String, String> payload) {
        Gson gson = new Gson();
        String payloadJson = gson.toJson(payload);
        return Base64.getEncoder().encodeToString(payloadJson.getBytes(StandardCharsets.UTF_8));
    }

    public String sign(String msg, String secret) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secret_key);


        return Base64.getUrlEncoder().encodeToString(sha256_HMAC.doFinal(msg.getBytes()));
    }
}
