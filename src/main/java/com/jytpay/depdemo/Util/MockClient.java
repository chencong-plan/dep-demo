package com.jytpay.depdemo.Util;

import com.jytpay.depdemo.Util.sign.CryptoUtils;
import com.jytpay.depdemo.Util.sign.RSAHelper;

import java.io.UnsupportedEncodingException;


public class MockClient {
    //客户端私钥 替换称自己的私钥就行了
    String CLIENT_PRIVATE_KEY = "";

    String SERVER_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6RpTg1SDJWCAr8NWLuJI" +
            "v8D9i3HbHBiBGEGNlKJjBuXpHUYTMhN+jKTtHip5xrsNFXA3pz9RXXTtTCpbeqT7" +
            "rtXJtU/Z84L/lWHZNMf0hizxEAnlHKQhtv3tzTYHQ/EgwhXyiSvw9J37VHvu1mA8" +
            "D2VUEifxK0ae8sIOyDAdrq4Ft097QorT/jWPZMWL1yBYbC+2OWmESfXJdiFCQTfl" +
            "2GtUjMR9Ck4p28lfgvwbBaKLLhH0r1ohMt+scp+NiSFK2uKF3KvBdCnKPTdg2Wlx" +
            "MKAnd5QjTNEVvhswlkaUQo5oNj9vEw10W57yOWuCfjG2uIvnP3tQ6i+Dc/gFflXu" +
            "cwIDAQAB";


    public RSAHelper rsaHelper = new RSAHelper();

    {
        try {
            rsaHelper.initKey(CLIENT_PRIVATE_KEY, SERVER_PUBLIC_KEY, 2048);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String signMsg(String json) {
        String hexSign = null;

        try {
            byte[] sign = rsaHelper.signRSA(json.getBytes("UTF-8"), false, "UTF-8");

            hexSign = StringUtil.bytesToHexString(sign);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return hexSign;
    }

    /**
     * 请求报文加密
     *
     * @param json 报文明文
     * @param key  des密钥
     * @return
     */
    public String encryptJson(String json, byte[] key) {
        String enc_xml = CryptoUtils.desEncryptToHex(json, key);

        return enc_xml;
    }

    public String encryptKey(byte[] key) {

        byte[] enc_key = null;
        try {
            enc_key = rsaHelper.encryptRSA(key, false, "UTF-8");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return StringUtil.bytesToHexString(enc_key);
    }

    /**
     * des密钥解密
     *
     * @param hexkey 密钥密文
     * @return
     */
    public byte[] decryptKey(String hexkey) {
        byte[] key = null;
        byte[] enc_key = StringUtil.hexStringToBytes(hexkey);

        try {
            key = rsaHelper.decryptRSA(enc_key, false, "UTF-8");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return key;
    }

    /**
     * 密文解密
     *
     * @param xml_enc 密文
     * @param key     的是密钥
     * @return
     */
    public String decrytXml(String xml_enc, byte[] key) {
        String xml = CryptoUtils.desDecryptFromHex(xml_enc, key);
        return xml;
    }

    /**
     * 验签
     *
     * @param xml  返回明文
     * @param sign 返回签名
     * @return
     */
    public boolean verifyMsgSign(String xml, String sign) {
        byte[] bsign = StringUtil.hexStringToBytes(sign);

        boolean ret = false;
        try {
            ret = rsaHelper.verifyRSA(xml.getBytes("UTF-8"), bsign, false, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ret;
    }
}
