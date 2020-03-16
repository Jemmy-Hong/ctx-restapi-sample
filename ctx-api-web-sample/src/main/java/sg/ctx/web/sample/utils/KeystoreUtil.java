package sg.ctx.web.sample.utils;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author Jemmy Hong
 * @date 2020/3/11 15:48
 */
public class KeystoreUtil {

    public static final String KEY_ALGORITHM = "RSA";

    private static final int MAX_ENCRYPT_BLOCK = 117;

    private static final int MAX_DECRYPT_BLOCK = 128;

    public static String KEY_STORE_FILE = "keystore.p12";
    public static String SERVER_CERTIFICATE_ALIAS = "server_certificate";
    public static String KEY_STORE_PASSWORD = "1q2w3e4rT%";

    public static String getServerPublicKeyString(){
        String publicKeyString = null;
        try {
            PublicKey key = getPublicKey(KEY_STORE_FILE, SERVER_CERTIFICATE_ALIAS, KEY_STORE_PASSWORD);
            publicKeyString = Base64.getEncoder().encodeToString(key.getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return publicKeyString;
    }

    /**
     * get keystore instance
     * @param keyStoreFile
     * @param password
     * @return
     * @throws Exception
     */
    public static KeyStore getKeyStore(String keyStoreFile, String password) throws Exception {
        //FileInputStream is = new FileInputStream(keyStoreFile);
        InputStream is = KeystoreUtil.class.getResourceAsStream("/" + keyStoreFile);
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(is, password.toCharArray());
        is.close();
        return ks;
    }


    public static PublicKey getPublicKey(String keyStorePath, String alias, String storePass) throws Exception {
        KeyStore ks = getKeyStore(keyStorePath, storePass);
        PublicKey key = ks.getCertificate(alias).getPublicKey();
        return key;

    }

    /**
     * <p></p>
     * @param data
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static String encryptByPublicKey(String data, String publicKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        byte[] dataBytes = data.getBytes("utf-8");
        int inputLen = dataBytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(dataBytes, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(dataBytes, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    /**
     * <p>  </p>
     * @param data
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static String encryptByPrivateKey(String data, String privateKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
        byte[] encryptBytes = data.getBytes();
        int inputLen = encryptBytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(encryptBytes, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptBytes, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    /**
     *
     * @param message
     * @param secret
     * @return
     */
    public static String sha256_HMAC(String message, String secret) {
        String hash = "";
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] bytes = sha256_HMAC.doFinal(message.getBytes());
            hash = byteArrayToHexString(bytes);
        } catch (Exception e) {
            System.out.println("Error HmacSHA256 ===========" + e.getMessage());
        }
        return hash;
    }

    /**
     *
     * @param b
     * @return
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b!=null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1) {
                hs.append('0');
            }
            hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }

    /**
     * <p> </p>
     *
     * @param encryptedData
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static String decryptByPublicKey(String encryptedData, String publicKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicK);
        byte[] dataBytes = Base64.getDecoder().decode(encryptedData);
        int inputLen = dataBytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(dataBytes, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(dataBytes, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return new String(decryptedData);
    }

    /**
     * <p><p/>
     * @param encryptedData
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static String decryptByPrivateKey(String encryptedData, String privateKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        byte[] encryptBytes = Base64.getDecoder().decode(encryptedData);
        int inputLen = encryptBytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptBytes, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptBytes, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return new String(decryptedData);
    }

}
