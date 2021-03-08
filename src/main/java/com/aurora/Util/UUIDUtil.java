package com.aurora.Util;

import java.util.UUID;

public class UUIDUtil {
	public static String uuid() {
		//生成uuid，去掉其中的横杠
		return UUID.randomUUID().toString().replace("-", "");
	}
}
