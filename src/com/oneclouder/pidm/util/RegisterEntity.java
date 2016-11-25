package com.oneclouder.pidm.util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
/**
 * 给实体类注入值
 * Created with IDEA
 * Author: AngryFeng
 * Date: 9/13/16
 * Time: 4:11 PM
 */
public final class RegisterEntity {
    /**
     * 将action中的值注入实体类entity
     *
     * @param entity
     *            实体类
     * @param action
     *            封装了值的action
     * @return 实体类的对象
     */
    public static Object register(Object entity, Object action) {
        Class<?> actionClass = action.getClass();
        Class<?> entityClass = entity.getClass();
        Method[] actionMethods = actionClass.getDeclaredMethods();
        for (Method method : actionMethods) {
            if (method.getModifiers() == Modifier.PRIVATE)
                continue;
            String methodName = method.getName();
            if (methodName.startsWith("get")) {
                String entityMethod = "set" + methodName.substring(3);
                Method m = null;
                try {
                    m = entityClass.getMethod(entityMethod, method
                            .getReturnType());
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    System.err.println(e.getMessage());
                    continue; // 如果实体类没有对应的方法则抛出此异常后，将继续循环下一个方法
                    // e.printStackTrace();
                }
                try {
                    m.invoke(entity, method.invoke(action, null));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return entity;
    }
}
