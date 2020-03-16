package sg.ctx.web.sample.utils;

import com.alibaba.fastjson.JSONObject;
import sg.ctx.web.sample.entity.FixNewOrder;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

/**
 * @author Jemmy Hong
 * @date 2020/3/12 14:04
 */
@SuppressWarnings("AlibabaRemoveCommentedCode")
public class ConverUtil {

    public static final String CLIENT_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJjFrlebgAyhX4MY+zOaVLvXHf05e/swX6cZR3ixzbyxD8fquq9x4c1pukkbDWOyxuOa/FCXiIc7eOQa5F6sxkZe+SDTNshfVa+KpoS4RkXGaUD1CfpVH/PO6+Wht2wDiFDhGk+K6iRP+KUOz2MEkNQICAfR4lArNcAUqXzWfGTFAgMBAAECgYBf5zDT1E0lIKSp0qPXOBnbNWahEVYQUMSrTEY+sTebLXffoy4Adzgt0CJeRDo6jP8n4KJmIUpWwnHYmD0mZcWtbP4cnv+F7zmiB3Lk8aMI7ppqbTbqYGqQk9JtTK7c3SZS3iYKdPDoB1I+6D/UWbWofgXZeaXc0MKxLPpK6wxjYQJBAMy2gAT2zUJYxKcu0ddasn2co5/vHMa4gEZUdWdGxNlA8seA5cTZndEFGtsKBygGltymGf/kxrjEoENLT1QiEx0CQQC/C+dUsUMSYzOStmAlqQwIx64RKl/dqRYYE4OsRG0fHuPcuZ+RPU19eZI2tz+p+dNp7dOQfxYnEl/nzA4tQH/JAkBrw9b4xgOf3aMIb3k/6Rr1TTRFyVZYtl+wpl13lKWjyLEFnTFXw0d09T8B9/F0fKOGXFprfz+tnLTROB/2pklxAkEAt84vEce9/RneS6AVnwYmYugia7d2JmAAtsdHntzyk8d3eSxJ/kWZoUfuyZ/Cq7mFktvS+amb2ZmPFge3+AefKQJAFwQaPEEspJQK7OkuDT7WboJHMgcfG5wxABnngYIEERdYHO3c8n+0pjUpSlLL9PK1A18+sSrJ1bbzmy3p4lOD1A==";

    /**
     * get a sorted string with parameters in ascending order
     * @param originalString
     * @return
     */
    public static String getOrderedParamString(String originalString){
        JSONObject jsonObject = JSONObject.parseObject(originalString);
        StringBuffer paraStr = new StringBuffer();
        LinkedHashSet<String> keySet = jsonObject.keySet().stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
        keySet.forEach(key -> {
            paraStr.append(key).append("=").append(jsonObject.get(key)).append("&");
        });

        String orderedString = paraStr.toString();
        return orderedString.substring(0, orderedString.length() -1);
    }

    public static JSONObject getEncryptSendingJSON(String applicationId, String clientPrivateKey, Object object) {
        //0. change object to a ordered String
        String jsonString = JSONObject.toJSONString(object);

        //1. get server public key
        String serverPublicKey = KeystoreUtil.getServerPublicKeyString();
        System.out.println("\n----------------------------------->serverPublicKey\n" + serverPublicKey);

        //2. get client private key

        //3. encrypt data
        String sortedData = ConverUtil.getOrderedParamString(jsonString);
        System.out.println("\n----------------------------------->sortedData\n" + sortedData);
        String encryptData = null;
        try {
            encryptData = KeystoreUtil.encryptByPublicKey(sortedData, serverPublicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\n----------------------------------->encryptData\n" + encryptData);

        //-. decrypt data to check whether it's correct
        // String decryptData = KeystoreUtil.decryptByPrivateKey(encryptData, CertificateUtil.getPrivateKeyString("server_certificate", "1q2w3e4rT%"));
        // System.out.println("\n----------------------------------->decryptData\n" + decryptData);

        //4. make sign
        String hashData = KeystoreUtil.sha256_HMAC(sortedData, applicationId);
        System.out.println("\n----------------------------------->signHashValue\n" + hashData);
        String sign = null;
        try {
            sign = KeystoreUtil.encryptByPrivateKey(hashData, clientPrivateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\n----------------------------------->sign\n" + sign);

        //-. decrypt sign to check whether it's correct
        // String hashSign = KeystoreUtil.decryptByPublicKey(sign, clientPrivateKey);
        // System.out.println("\n----------------------------------->" + hashSign.equalsIgnoreCase(KeystoreUtil.sha256_HMAC(sortedData, "bd710127-2903-481c-acd7-9e1aa0a7ec51")));

        //5. return the required data
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("applicationId", applicationId);
        jsonObject.put("sign", sign);
        jsonObject.put("encryptData", encryptData);
        return jsonObject;
    }

    public static void main(String[] args) {
        FixNewOrder order = new FixNewOrder();
        order.setClOrdID("202003120037");
        order.setSymbol("BTC/SGD");
        order.setAccount("FIRM01001");
        order.setOrderQty(1L);
        order.setOrdType("4");
        order.setPrice(BigDecimal.valueOf(5000L));
        order.setSide("1");
        order.setTriggerPriceType("4");
        order.setTriggerPrice(BigDecimal.valueOf(5000L));
        order.setTriggerPriceType("2");
        order.setTriggerPriceDirection("D");
        JSONObject encryptSendingJSON = getEncryptSendingJSON("bd710127-2903-481c-acd7-9e1aa0a7ec51", CLIENT_PRIVATE_KEY, order);
        System.out.println("\n----------------------------------->encryptSendingString");
        System.out.println(encryptSendingJSON);
    }
}
