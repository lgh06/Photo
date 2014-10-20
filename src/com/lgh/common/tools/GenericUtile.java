package com.lgh.common.tools;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericUtile {
	@SuppressWarnings("unchecked")
	public static Class<? extends Object> getSuperGenericClass(Class<? extends Object> clazz){
		Type genericSuperclass = clazz.getGenericSuperclass();
		Type[] params = ((ParameterizedType)genericSuperclass).getActualTypeArguments();
		return (Class<? extends Object>)params[0];
	}
}
