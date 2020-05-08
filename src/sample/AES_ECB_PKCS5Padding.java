package sample;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;

/*
Length of key must be 16 characters only.
 */


class AES_ECB_PKCS5Padding
{
    static void encrypt(File filePathName, String key)
    {
        boolean successfullyEncryptedFlag = false;

        File newFilePathName = null;
        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bufferedOutputStream = null;

        try
        {
            // Get parent folder and append the fileName + .Encrypted_AES_ECB to it
            newFilePathName = new File(filePathName.getPath() + ".Encrypted_AES_ECB");
            fileOutputStream = new FileOutputStream(newFilePathName);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

            // File Handling of reading the file for encryption
            FileInputStream fileInputStream = null;
            BufferedInputStream bufferedInputStream = null;
            try
            {
                fileInputStream = new FileInputStream(filePathName);
                bufferedInputStream = new BufferedInputStream(fileInputStream);

                byte [] byteArray = new byte[8192];
                int readSize = 0;

                while((readSize = bufferedInputStream.read(byteArray)) != -1)
                {
                    // Exception Handling
                    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                    // Use SHA 512 first 16 bytes for 128 bit key size
                    SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
                    cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
                    byte [] byteToWrite = cipher.doFinal(byteArray, 0, readSize);
                    //System.out.println(byteToWrite.length);
                    bufferedOutputStream.write(byteToWrite);
                    bufferedOutputStream.flush();
                }
                successfullyEncryptedFlag = true;
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    if (bufferedInputStream != null) bufferedInputStream.close();
                    if (fileInputStream != null) fileInputStream.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (bufferedOutputStream != null) bufferedOutputStream.close();
                if (fileOutputStream != null) fileOutputStream.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        if(successfullyEncryptedFlag)
        {
            String temp = filePathName.getPath();
            filePathName.delete();
            newFilePathName.renameTo(new File(temp));
        }
        else
        {
            newFilePathName.delete();
        }
    }

    static boolean decrypt(File filePathName, String key)
    {
        boolean successfullyDecryptedFlag = false;

        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        File newFilePathName = null;

        try
        {
            // Decryption
            newFilePathName = new File(filePathName.getPath() + ".Decrypted_AES_ECB");
            fileOutputStream = new FileOutputStream(newFilePathName);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            try
            {
                // File Handling
                FileInputStream fileInputStream = null;
                BufferedInputStream bufferedInputStream = null;
                try
                {
                    fileInputStream = new FileInputStream(filePathName);
                    bufferedInputStream = new BufferedInputStream(fileInputStream);

                    // Much Crucial
                    byte [] byteArray = new byte[8208];
                    int readSize = 0;

                    while((readSize = bufferedInputStream.read(byteArray)) != -1)
                    {
                        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                        // Use SHA 512 first 16 bytes for 128 bit key size
                        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
                        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
                        byte [] byteToWrite = cipher.doFinal(byteArray, 0, readSize);
                        bufferedOutputStream.write(byteToWrite, 0, byteToWrite.length);
                        bufferedOutputStream.flush();
                    }
                    successfullyDecryptedFlag = true;
                }
                catch(Exception e) { e.printStackTrace(); }
                finally
                {
                    try
                    {
                        if (bufferedInputStream != null) bufferedInputStream.close();
                        if (fileInputStream != null) fileInputStream.close();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            catch (Exception e) { e.printStackTrace(); }
        }
        catch (Exception e) { e.printStackTrace(); }
        finally
        {
            try
            {
                if (bufferedOutputStream != null) bufferedOutputStream.close();
                if (fileOutputStream != null) fileOutputStream.close();
            }
            catch (Exception e) { e.printStackTrace(); }
        }

        if(successfullyDecryptedFlag)
        {
            String temp = filePathName.getPath();
            filePathName.delete();
            newFilePathName.renameTo(new File(temp));
            return true;
        }
        else
        {
            newFilePathName.delete();
            return false;
        }
    }
}
