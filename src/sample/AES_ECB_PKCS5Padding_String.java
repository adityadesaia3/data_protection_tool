package sample;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

// Exception handling with dialog boxes

public class AES_ECB_PKCS5Padding_String
{
    static String encrypt(String plainText, String key)
    {
        String cipherText = null;
        try
        {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            cipherText = Base64.getEncoder().encodeToString(cipher.doFinal(plainText.getBytes()));
        }
        catch (Exception e) { e.printStackTrace(); }
        return cipherText;
    }

    static String decrypt(String cipherText, String key)
    {
        String plainText = null;
        try
        {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            plainText = new String(cipher.doFinal((Base64.getDecoder().decode(cipherText))));
        }
        catch (Exception e) { e.printStackTrace(); }
        return plainText;
    }
}
