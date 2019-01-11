package com.jytpay.depdemo.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

/**
 * 公共转换类型
 * 
 */
public class CommonConvert {
	
	/**
	 * map转变成bean
	 * @param type
	 * @param map
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("rawtypes")
	public static Object convertMapToBean(Class type, Map map) 
			throws IntrospectionException, IllegalAccessException, InstantiationException, InvocationTargetException {
		
        BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性    
        Object obj = type.newInstance(); // 创建 JavaBean 对象    
    
        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();    
        for (int i = 0; i< propertyDescriptors.length; i++) {    
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            
            if (map.containsKey(propertyName)) {    
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                Object value = map.get(propertyName);
                Object[] args = new Object[1];    
                args[0] = value;
                descriptor.getWriteMethod().invoke(obj, args);    
            }
        }
        return obj;    
    }
	
	/**
	 * HttpServletRequest格式 转成 Map<String, String>格式
	 * @param request
	 * @return
	 */
	public static Map<String, String> convertRequestToMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		
		//获取请求里的参数
		Map<String, String[]> paramMaps = request.getParameterMap();
		Iterator<Entry<String, String[]>> iterator = paramMaps.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, String[]> entry = iterator.next();
			if(entry.getValue().length == 1) {
				map.put(entry.getKey(), entry.getValue()[0]);
			}
		}
		return map;
	}
}
