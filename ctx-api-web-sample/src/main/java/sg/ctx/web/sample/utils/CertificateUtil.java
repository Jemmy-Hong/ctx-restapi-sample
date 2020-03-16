package sg.ctx.web.sample.utils;


import java.security.*;
import java.security.cert.Certificate;
import java.util.Base64;

/**
 * @author hongyu
 */
public class CertificateUtil {

    public static String KEY_STORE_FILE = "keystore.p12";
    public static String CLIENT_KEYSTORE_PASSWORD = "1q2w3e4rT%";

    public static String SERVER_CERTIFICATE_ALIAS = "server_certificate";
    public static String CLIENT_CERTIFICATE_PASSWORD = "1q2w3e4rT%";


    public static void main(String[] args) throws Exception {
        //server keys
        System.out.println(KeystoreUtil.getServerPublicKeyString());
        System.out.println(getPrivateKeyString(SERVER_CERTIFICATE_ALIAS, CLIENT_KEYSTORE_PASSWORD));

        System.out.println("\n");
        //client keys:
        PublicKey clientPublicKey = KeystoreUtil.getPublicKey("keystore.p12", "client_certificate", CLIENT_CERTIFICATE_PASSWORD);
        System.out.println(Base64.getEncoder().encodeToString(clientPublicKey.getEncoded()));
        System.out.println(getPrivateKeyString("client_certificate", CLIENT_CERTIFICATE_PASSWORD));

    }

    /**
     * get private key
     * @param alias
     * @param password
     * @return
     */
    public static String getPrivateKeyString(String alias, String password) throws Exception {
        KeyStore keyStore = KeystoreUtil.getKeyStore(KEY_STORE_FILE, CLIENT_KEYSTORE_PASSWORD);
        KeyPair keyPair = getPrivateKey(keyStore, alias, password.toCharArray());
        PrivateKey privateKey = keyPair.getPrivate();
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    /**
     *get key pair
     * @param keystore
     * @param alias
     * @param password
     * @return
     */
    public static KeyPair getPrivateKey(KeyStore keystore, String alias, char[] password) {
        try {
            Key key = keystore.getKey(alias, password);
            if (key instanceof PrivateKey) {
                Certificate cert = keystore.getCertificate(alias);
                PublicKey publicKey = cert.getPublicKey();
                return new KeyPair(publicKey, (PrivateKey) key);
            }
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return null;
    }

}
