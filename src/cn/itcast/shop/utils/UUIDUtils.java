package cn.itcast.shop.utils;

import java.util.UUID;

/**
 * 生成随机字符串的工具类
 * @author xkx
 *
 */
public class UUIDUtils {
/**
 * 忽地随机字符串字符
 */
	public static String getUUID(){
		
		return UUID.randomUUID().toString().replace("-","");
	}
}
