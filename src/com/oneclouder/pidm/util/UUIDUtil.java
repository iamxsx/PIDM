package com.oneclouder.pidm.util;

import java.util.UUID;

public class UUIDUtil {
	public static String generateUid(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
