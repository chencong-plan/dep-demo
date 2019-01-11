package com.jytpay.depdemo.util;

import java.math.BigDecimal;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 字符串工具类
 *
 */
public class StringUtil {

	private static final Log log = LogFactory.getLog(StringUtil.class);

	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	/**
	 * 判断字符串是否为纯数字
	 * <p>
	 * Create Date: 2014年9月29日
	 * </p>
	 * 
	 * @param str
	 * @return true 是，false 否
	 */
	public static boolean isDigital(String str) {
		if (isBlank(str))
			return false;

		return str.matches("\\d+");
	}

	/**
	 * 判断字符串是否字母加数字
	 * <p>
	 * Create Date: 2014年10月9日
	 * </p>
	 * 
	 * @param str
	 * @return true 是，false 否
	 */
	public static boolean isAlpha(String str) {
		if (isBlank(str))
			return false;

		return str.matches("^[A-Za-z0-9]+$");
	}

	/**
	 * @param str
	 *            判断字符串是金额格式：####.####
	 * @return true 是，false 否
	 */
	public static boolean isMoney(String str) {
		try {
			new BigDecimal(str);
		} catch (Exception e) {
			log.error("字符串格式有问题", e);
			return false;
		}
		return true;
	}

	public static boolean isBlank(String str) {
		int length;
		if ((str == null) || ((length = str.length()) == 0))
			return true;
		for (int i = 0; i < length; ++i) {
			if (!(Character.isWhitespace(str.charAt(i)))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNotBlank(String str) {
		int length;
		if ((str == null) || ((length = str.length()) == 0))
			return false;
		for (int i = 0; i < length; ++i) {
			if (!(Character.isWhitespace(str.charAt(i)))) {
				return true;
			}
		}
		return false;
	}

	public static boolean isEmpty(String str) {
		return ((str == null) || (str.length() == 0));
	}

	public static boolean isNotEmpty(String str) {
		return ((str != null) && (str.length() > 0));
	}

	public static boolean equalsIgnoreCase(String str1, String str2) {
		if (str1 == null) {
			return str2 == null;
		}
		return str1.equalsIgnoreCase(str2);
	}

	public static boolean equals(String str1, String str2) {
		if (str1 == null) {
			return str2 == null;
		}
		return str1.equals(str2);
	}

	/**
	 * Convert byte[] to hex string.这里我们可以将byte转换成int，
	 * 然后利用Integer.toHexString(int)来转换成16进制字符串。
	 * 
	 * @param src
	 *            byte[] data
	 * @return hex string
	 */
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * Convert hex string to byte[]
	 * 
	 * @param hexString
	 *            the hex string
	 * @return byte[]
	 */
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	/**
	 * Convert char to byte
	 * 
	 * @param c
	 *            char
	 * @return byte
	 */
	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	public static String getStorableBankCardNo(String bankCardNo) {
		if (bankCardNo == null || bankCardNo.length() < 10) {
			return bankCardNo;
		}
		int length = bankCardNo.length();
		return bankCardNo.substring(0, 6) + bankCardNo.substring(length - 4, length);
	}

	public static String maskBankAcct(String account) {
		if (isBlank(account))
			return account;

		int length = account.length();
		if (account == null || account.length() < 10) {
			return account;
		}

		StringBuffer result = new StringBuffer();
		result.append(account.substring(0, 6));
		result.append("****");
		result.append(account.substring(length - 4, length));

		return result.toString();
	}

	public static String maskCertNo(String certNo) {
		if (isBlank(certNo)) {
			return certNo;
		}
		int length = certNo.length();
		if (certNo == null || certNo.length() < 10) {
			return certNo;
		}

		StringBuffer result = new StringBuffer();
		result.append(certNo.substring(0, 6));
		result.append("****");
		result.append(certNo.substring(length - 4, length));

		return result.toString();
	}

	public static String maskMobileNo(String mobile) {
		if (isEmpty(mobile)) {
			return mobile;
		}

		int length = mobile.length();
		if (mobile == null || mobile.length() < 11) {
			return mobile;
		}

		StringBuffer result = new StringBuffer();
		result.append(mobile.substring(0, 3));
		result.append("****");
		result.append(mobile.substring(length - 4, length));

		return result.toString();
	}

	public static String maskName(String name) {
		if (isEmpty(name)) {
			return name;
		}

		StringBuffer result = new StringBuffer();
		if (!name.matches("^([\u4e00-\u9fa5]+|([a-zA-Z]+\\s?)+)$")) {
			return name;
		}

		String regCN = "[\u4e00-\u9fa5]";
		String word[] = null;
		if (name.matches(".*" + regCN + ".*")) {
			word = new String[name.length()];
			for (int i = 0; i < name.length(); i++) {
				word[i] = name.substring(i, i + 1);
			}
		} else {
			word = name.split("\\s?");
		}

		result.append(word[0]);
		int size = word.length;
		if (size == 1 || size == 2) {
			result.append("*");
		} else {
			for (int i = 1; i < size - 1; i++) {
				result.append("*");
			}
			result.append(word[size - 1]);
		}

		return result.toString();
	}

	public static String getRandomString(int length) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	
	/**
     * 为字符串前加指定数量的空格
     * @param fileName 源串
     * @return boolean
     */
    public static String insHTMLEscape(int count){
    	StringBuilder prefix=new StringBuilder();
    	for(int i=0;i<count;i++){
    		prefix.append((char)0xA0);
    	}
    	return prefix.toString();
    }
}
