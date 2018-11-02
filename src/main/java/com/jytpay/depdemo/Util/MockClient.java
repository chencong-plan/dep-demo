package com.jytpay.depdemo.Util;

import com.jytpay.depdemo.Util.sign.CryptoUtils;
import com.jytpay.depdemo.Util.sign.RSAHelper;

import java.io.UnsupportedEncodingException;


public class MockClient {
    String CLIENT_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC1jMzaO9V7El9i" +
            "JM4HI5h5If/7dA94etafIuup3ULNS20XtLDZ/2NlbhYyPCjbyLqhZpQFcUmGI9sY" +
            "MlpzAVe6MKmTDHX3CyUNgNENu+pGPNLEU3apPqwVaBCBOcSyDnKxsrhEIgid8E59" +
            "IMRySH6R5c43zLG4n91eloGhrOt1QuHF0GKYT9MKJOUbeld62VH0OXLbn2VdVd0l" +
            "CowKkTC8vt+bEiSkqJxjkrunwyDnZxytTb6AB3LrYSrGR+tJzSdYh7Go0/IHYC9n" +
            "ejoLOnce6u4rei4NRA/MA5O+UA4OlQ0hUwbBvTLxgPuCl5cpde9KVDKTTcuAmcJG" +
            "FFEAI6aZAgMBAAECggEAescSzs/CKu4c5YGgd8GB3wG4XTR7Ne5U8OAr4f+Jqs7E" +
            "o1CGfZcygCj4B9cZhrF0TazSk++uWnIrPGzpMHeK/ZblR1YY/Q5ja4NfXdtkr0V5" +
            "AF61k/WXaQ6sgsgvln8g373O0fz2OOGIIyUi1WrjdgWk9aWP1xz+F3bPiigoQG7s" +
            "IjfByjafQXCbWIhu411P+sid0EZSKrYUM7CPIXW82ymiRbJGUUDhssGeJUo/FKJe" +
            "bp5oP3zo4Y30DLbkyli6K4W/s5Zea2JOTVVT4Nke44cSRf7lId6hqTv9COzOva+x" +
            "0oHlKbB6r+1ldFYS2y7XrecuahEpTti1UnCo+GmW0QKBgQDa6AeRHKTTI9X2QaL0" +
            "zK+kJEButbqCS+P9y2HqGRZagwiBlllNE+CjE3R+gO/GnmnzWqC9bJPNILcfnobz" +
            "A3smFVX9Ds+R01CI9/FzDyb1m9bcaexsQ97ryeQXqXbAlnaXj6UJyRXei4NxxdCX" +
            "q8zgJ4a5/UhDxG9hVwQ3n9A2jQKBgQDUUEiYiwM1aJZ4dWWxdIXDsQVv4KxXvnh1" +
            "KLVIfVX+0e5uy8Ll54fHerbw0OYfZ6JNyv9aoNwjz4jOAxOEow08985DiheWPVs7" +
            "QexKoB2mcLdyJXMNuEyit9bd4cQ0ZH7Ii+k3tdUpEG3rmL/FZNkmuJODLA0N4jHl" +
            "6LcSEy8DPQKBgHJeR5XJIVQupNrD8B910U4wonk0+PyaKcOvPrjP4p3nV8pcgsuC" +
            "VxrcT0DInH1oxnIeJ1zQHWhOb15VtKvCZdS/0O0lj8zgWnM1FzDF+dTYVqlRrfPi" +
            "gdUu5bx3DVQRSGRm6BL0vDhJK6OFdjXHzIJQJFOHwPmm54ozrEBjfqUBAoGBAJz0" +
            "kQZ8Vi3omLyKhVhPvQezWUKhBKeFPsXWB1aEmctc5PhxmC6gIGee15llxYnX8kuy" +
            "U15rZvLuXMN30LkQVRwKUijp8I/34fcHwChXADRoRaWT+99brQPqBmVXntXgqT2W" +
            "+R1fRisqYyh1buf9qu8OKrchHLFz09GUXap2Fh49AoGAG+y+ZBRuNIW34/pvv4kr" +
            "mDlT0T5VjbJ9Bx8rHHIBRwOmB8EYNi6idaPW0h2kezXP2o235WKk7taPL9YJZhip" +
            "vimjSBgJq64EPLb/ZfDY6UmrJ4qbSLpHkeCLvS3fqAh0pMqSpMI+LmAmk69mZMNo" +
            "J4eKNWMhHvIkqIpaExyjaHQ=";

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
