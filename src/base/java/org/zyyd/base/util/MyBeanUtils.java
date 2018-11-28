package org.zyyd.base.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MyBeanUtils {
	
	/**
	 * object to map
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> introspect(Object obj){
	    Map<String, Object> result = new HashMap<String, Object>();
	    try {
		    BeanInfo info = Introspector.getBeanInfo(obj.getClass());
		    for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
		        Method reader = pd.getReadMethod();
		        if (reader != null)
		            result.put(pd.getName(), reader.invoke(obj));
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}

	    return result;
	}
	
}
