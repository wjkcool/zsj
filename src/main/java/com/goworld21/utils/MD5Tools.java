package com.goworld21.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Tools {

	public static byte[] getKeyedDigest(byte[] buffer, byte[] key) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(buffer);
			return md5.digest(key);
		} catch (NoSuchAlgorithmException e) {
		}
		return null;
	}

	public static String getKeyedDigest(String strSrc, String key) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(strSrc.getBytes("UTF8"));

			String result = "";
			byte[] temp;
			temp = md5.digest(key.getBytes("UTF8"));
			for (int i = 0; i < temp.length; i++) {
				result += Integer.toHexString((0x000000ff & temp[i]) | 0xffffff00).substring(6);
			}

			return result;

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String md5(String... strings) {
		String result = "";
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			StringBuilder key = new StringBuilder();
			for (String str : strings) {
				key.append(str);
			}
			byte[] temp;
			temp = md5.digest(key.toString().getBytes("UTF-8"));
			for (int i = 0; i < temp.length; i++) {
				result += Integer.toHexString((0x000000ff & temp[i]) | 0xffffff00).substring(6);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String mi;
		String s = "hf1000";

		// 第二个参数请填空字符串
		mi = MD5Tools.getKeyedDigest(s, "123");

		System.out.println("mi:" + mi);
		System.out.println(md5(s, "1", "23"));
	}

	/**
	 * 
	 * Description:
	 * 	微信MD5
	 * @param 
	 * @return
	 * @throw Exception
	 *
	 */
	public static String weiXinMD5(String plainText) {
		StringBuffer buf = new StringBuffer("");
		try {
			// 生成实现指定摘要算法的 MessageDigest 对象。
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 使用指定的字节数组更新摘要。
			md.update(plainText.getBytes("UTF-8"));
			// 通过执行诸如填充之类的最终操作完成哈希计算。
			byte b[] = md.digest();
			// 生成具体的md5密码到buf数组
			int i;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			// System.out.println("32位: " + buf.toString());// 32位的加密
			// System.out.println("16位: " + buf.toString().substring(8, 24));//
			// 16位的加密，其实就是32位加密后的截取
			return buf.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return buf.toString();
		}
	}

}