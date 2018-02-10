package OTS.tickets.OTSserver.util;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class CodeUtil {

    public String encryptCode(String email) {
        MessageDigest md5 = null;
        StringBuilder builder = new StringBuilder();
        try {
            md5 = MessageDigest.getInstance("md5");
            byte[] cipherData = md5.digest(email.getBytes());
            for (byte cipher : cipherData) {
                String toHexStr = Integer.toHexString(cipher & 0xff);
                builder.append(toHexStr.length() == 1 ? "0" + toHexStr : toHexStr);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println(builder.toString());
        return builder.toString();
    }
}
