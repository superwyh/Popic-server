package util;

import java.lang.reflect.Method;
import java.util.List;

public class DbTool {
	public static String getJsonForVoList(List<?> vos) throws Exception {
		StringBuilder sb = new StringBuilder(50);
		Object vo1 = vos.get(0);
		Class clazz = vo1.getClass();
		String methodName = "";
		sb.append("[");
		Method[] ms = clazz.getDeclaredMethods();
		Object value = null;

		for (Object vo : vos) {
			sb.append("{");
			for (Method m : ms) {
				methodName = m.getName();
				if (methodName.contains("get")) {
					methodName = methodName.substring(3);
					methodName = methodName.substring(0, 1).toLowerCase()
							+ methodName.substring(1);
					value = m.invoke(vo, null);
					if (null == value) {
						value = "";
					}
					sb.append("\"").append(methodName).append("\"");
					sb.append(":");
					sb.append("\"").append(value).append("\"");
					sb.append(",");
					// sb.append(methodName.substring(3, endIndex))
				}

			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("},");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		return sb.toString();
	}
	
	
	public static String getJsonForVo(Object vo) throws Exception {
		StringBuilder sb = new StringBuilder(50);
		Class clazz = vo.getClass();
		Method[] ms = clazz.getDeclaredMethods();
		String methodName = "";
		sb.append("[{");
		Object value = null;
		for (Method m : ms) {
			methodName = m.getName();
			if (methodName.contains("get")) {
				methodName = methodName.substring(3);
				methodName = methodName.substring(0, 1).toLowerCase()
						+ methodName.substring(1);
				value = m.invoke(vo, null);
				if (null == value) {
					value = "";
				}
				sb.append("\"").append(methodName).append("\"");
				sb.append(":");
				sb.append("\"").append(value).append("\"");
				sb.append(",");
				// sb.append(methodName.substring(3, endIndex))
			}

		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("}]");
		return sb.toString();
	}
	
	
	public static void main(String[] args) {
	/**
		MajorVO vo = new MajorVO();
		vo.setMajor_level("111");
		vo.setMajor_name("nanaman");
		vo.setMajor_type("ssss");
		vo.setMid(12);
		try {
			DbTool.getJsonForVo(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	*/
	}
}
