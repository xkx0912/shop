package cn.itcast.shop.utils;

import java.util.UUID;

/**
 * ��������ַ����Ĺ�����
 * @author xkx
 *
 */
public class UUIDUtils {
/**
 * ��������ַ����ַ�
 */
	public static String getUUID(){
		
		return UUID.randomUUID().toString().replace("-","");
	}
}
