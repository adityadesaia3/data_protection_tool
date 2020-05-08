package sample;

import javafx.stage.FileChooser;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;

class HashGenerator
{
    // JavaFX FileChooser
    static File chooseFile()
    {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose the File");
        File filePathName = chooser.showOpenDialog(null);
        return filePathName;
    }

    static List<File> chooseMultipleFiles()
    {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose multiple Files");
        List<File> listOfFilePathNames = chooser.showOpenMultipleDialog(null);
        return listOfFilePathNames;
    }

    static String generateChecksum(File filePathName, String algorithmName)
    {
        String checksum = "";

        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;

        try
        {
            fileInputStream = new FileInputStream(filePathName);
            bufferedInputStream = new BufferedInputStream(fileInputStream);

            MessageDigest md = MessageDigest.getInstance(algorithmName);
            byte [] byteArray = new byte[8192];

            int readSize;
            while((readSize = bufferedInputStream.read(byteArray)) != -1)
                md.update(byteArray, 0, readSize);

            BigInteger bInt = new BigInteger(1, md.digest());
            checksum = bInt.toString(16);
        }
        catch (Exception e) { e.printStackTrace(); }
        finally
        {
            try
            {
                if (bufferedInputStream != null) bufferedInputStream.close();
                if (fileInputStream != null) fileInputStream.close();
            }
            catch (Exception e) { e.printStackTrace(); }
        }

        checksum = creatingChecksumAsPerAlgorithm(algorithmName, checksum);

        return checksum;
    }

    // For String
    static String generateChecksum(byte [] byteData, String algorithmName)
    {
        String checksum = "";

        try
        {
            MessageDigest md = MessageDigest.getInstance(algorithmName);
            BigInteger bInt = new BigInteger(1, md.digest(byteData));
            checksum =  bInt.toString(16);

            checksum = creatingChecksumAsPerAlgorithm(algorithmName, checksum);
        }
        catch (Exception e) { e.printStackTrace(); }

        return checksum;
    }

    // Optimize creatingChecksumAsPerAlgorithm Function
    private static String creatingChecksumAsPerAlgorithm(String algorithmName, String checksumOriginal)
    {
        StringBuilder checksum = new StringBuilder(checksumOriginal);

        /*
         *  Test every algorithm's DIGIT COUNT and HASH VALUE SIZE
         */
        switch (algorithmName)
        {
            /*
             *  MD5
             *  32 digits long
             *  16 byte hash value
             */
            case "MD5":
                while (checksum.length() < 32) checksum.insert(0, "0");
                break;

            /*
             *  SHA-1
             *  40 digits long
             *  20 byte hash value
             */
            case "SHA-1":
                while (checksum.length() < 40) checksum.insert(0, "0");
                break;

            /*
             *  SHA-224
             *  56 digits long
             *  28 byte hash value
             */
            case "SHA-224":
                while (checksum.length() < 56) checksum.insert(0, "0");
                break;

            /*
             *  SHA-256
             *  64 digits long
             *  32 byte hash value
             */
            case "SHA-256":
                while (checksum.length() < 64) checksum.insert(0, "0");
                break;

            /*
             *  SHA-384
             *  96 digits long
             *  48 byte hash value
             */
            case "SHA-384":
                while (checksum.length() < 96) checksum.insert(0, "0");
                break;

            /*
             *   SHA-512
             *   128 digits long
             *   64 byte hash value
             */
            case "SHA-512":
                while (checksum.length() < 128) checksum.insert(0, "0");
                break;

            /*
             *  SHA3-224
             *  56 digits long
             *  28 byte hash value
             */
            case "SHA3-224":
                while (checksum.length() < 56) checksum.insert(0, "0");
                break;

            /*
             *  SHA3-256
             *  64 digits long
             *  32 byte hash value
             */
            case "SHA3-256":
                while (checksum.length() < 64) checksum.insert(0, "0");
                break;

            /*
             *  SHA3-384
             *  96 digits long
             *  48 byte hash value
             */
            case "SHA3-384":
                while (checksum.length() < 96) checksum.insert(0, "0");
                break;

            /*
             *   SHA3-512
             *   128 digits long
             *   64 byte hash value
             */
            case "SHA3-512":
                while (checksum.length() < 128) checksum.insert(0, "0");
                break;
        }
        return checksum.toString();
    }
}
