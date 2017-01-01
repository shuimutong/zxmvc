package cn.ourpass.zxmvc.utils;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author xiaojianyu
 * 
 */
public class BaseParamUtil {

	private BaseParamUtil() {
	}

	public static short getShort(String stringNumber, short defaultValue) {
		if (StringUtils.isBlank(stringNumber)) {
			return defaultValue;
		}

		try {
			return Short.parseShort(stringNumber);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static int getInt(String stringNumber, int defaultValue) {
		if (StringUtils.isBlank(stringNumber)) {
			return defaultValue;
		}

		try {
			return Integer.parseInt(stringNumber);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static Integer getInteger(String stringNumber, Integer defaultValue) {
		if (StringUtils.isBlank(stringNumber)) {
			return defaultValue;
		}

		try {
			return Integer.parseInt(stringNumber);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static Double getDouble(String stringNumber, Double defaultValue) {
		if (StringUtils.isBlank(stringNumber)) {
			return defaultValue;
		}

		try {
			return Double.parseDouble(stringNumber);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static Float getFloat(String stringNumber, Float defaultValue) {
		if (StringUtils.isBlank(stringNumber)) {
			return defaultValue;
		}

		try {
			return Float.parseFloat(stringNumber);
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	/**
	 * 复选框打钩传true，不打勾false
	 * 
	 * @param stringNumber
	 * @param defaultValue
	 * @return
	 */
	public static Boolean getBoolean(String stringNumber, Boolean defaultValue) {
		if (StringUtils.isBlank(stringNumber)) {
			return defaultValue;
		}

		try {
			return true;
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static int getInt(String stringNumber, int min, int defaultValue) {
		try {
			int returnInt = getInt(stringNumber, defaultValue);

			if (returnInt >= min) {
				return returnInt;
			} else {
				return defaultValue;
			}
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static int getInt(String stringNumber, int min, int max,
			int defaultValue) {
		try {
			int returnInt = getInt(stringNumber, min, defaultValue);

			if (returnInt <= max) {
				return returnInt;
			} else {
				return defaultValue;
			}
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static long getLong(String stringNumber, long defaultValue) {
		if (StringUtils.isBlank(stringNumber)) {
			return defaultValue;
		}

		try {
			return Long.parseLong(stringNumber);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static long getLong(String stringNumber, long min, long defaultValue) {
		try {
			long returnInt = getLong(stringNumber, defaultValue);

			if (returnInt >= min) {
				return returnInt;
			} else {
				return defaultValue;
			}
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static long getLong(String stringNumber, long min, long max,
			long defaultValue) {
		try {
			long returnInt = getLong(stringNumber, min, defaultValue);

			if (returnInt <= max) {
				return returnInt;
			} else {
				return defaultValue;
			}
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static String getString(HttpServletRequest request, String input,
			String defaultValue) {
		if (StringUtils.isBlank(input)) {
			return defaultValue;
		}

		return xssClean(request, input.trim());
	}

	public static String getString(String input, String[] validValues,
			String defaultValue) {
		boolean caseSensitive = false;

		return getString(input, validValues, defaultValue, caseSensitive);
	}

	public static String getString(String input, String[] validValues,
			String defaultValue, boolean caseSensitive) {
		if (StringUtils.isBlank(input)) {
			return defaultValue;
		}
		if (caseSensitive == false) {
			input = input.toLowerCase();

			for (int i = 0, n = validValues.length; i < n; i++) {
				validValues[i] = validValues[i].toLowerCase();
			}
		}
		if (validValues != null) {
			for (int i = 0, n = validValues.length; i < n; i++) {
				if (input.equals(validValues[i])) {
					return input;
				}
			}
		}
		return defaultValue;
	}

	public static Boolean getBooleanRe(String str, Boolean defaultValue) {
		if (StringUtils.isBlank(str)) {
			return defaultValue;
		}

		try {
			return Boolean.parseBoolean(str);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	private static String xssClean(HttpServletRequest request, String value) {
		/*if (!StringUtils.isBlank(value)) {
			try {
				Policy policy = Policy.getInstance(request.getRealPath("/")
						+ "/WEB-INF/antisamy-ebay-1.4.4.xml");
				AntiSamy antiSamy = new AntiSamy();
				final CleanResults cr = antiSamy.scan(value, policy,
						AntiSamy.SAX);
				return cr.getCleanHTML();
			} catch (ScanException e) {
				e.printStackTrace();
			} catch (PolicyException e) {
				e.printStackTrace();
			}
		}*/
		return value;
	}
	
	public static BigDecimal getBigDecimal(String stringNumber,BigDecimal defaultValue) {
		if (StringUtils.isBlank(stringNumber)) {
			return defaultValue;
		}

		try {
			return new BigDecimal(stringNumber);
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	public static Date getDate(String stringNumber,Date defaultValue) {
		if (StringUtils.isBlank(stringNumber)) {
			return defaultValue;
		}

		try {
			return  DateUtils.getDateByTimeType(stringNumber);
		} catch (Exception e) {
			return defaultValue;
		}
	}
}
