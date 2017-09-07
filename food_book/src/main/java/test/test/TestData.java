package test.test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class TestData {
	private String name = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Map<String, String> getRequestData(boolean isMobile) {
		Map<String, String> map = new HashMap<String, String>();
		Class<? extends TestData> dealClass = this.getClass();
		Field[] fields = dealClass.getDeclaredFields();
		for (Field field : fields) {
			if (field.getModifiers() == Modifier.PRIVATE && field.getType() == String.class) {
				String value;
				try {
					value = (String) field.get(this);
					if (null != value && !"".equals(value)) {
						map.put(field.getName(), value);
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
		if (isMobile){
			map.put("isMobile", "TRUE");
		}
		return map;
	}
}
