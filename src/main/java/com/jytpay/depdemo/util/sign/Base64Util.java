package com.jytpay.depdemo.util.sign;

import org.apache.commons.codec.binary.Base64;
/**
 * Base64编码解码工具类
 */
public class Base64Util {
	/**
	 * Base64编码
	 * @param str
	 * @return
	 */
		public static byte[] encode(byte[] str)  {
			byte[] result = null;
			
			if (str != null) {
				result = Base64.encodeBase64(str) ;
			}
			return result;
		}

		/**
		 * Base64解密
		 * @param str
		 * @return
		 */
		public static byte[] decode(byte[] str )  {
			byte[] result = null;
			if (str != null) {
				result = Base64.decodeBase64(str) ;
				
			}
			return result ;
		}
}
