package cn.ourpass.zxmvc.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 
 * 请求帮助类
 *
 */
public class RequestUtil {

	public static final String ENCODING = "UTF-8";
	
	private final static Logger log = Logger.getLogger(RequestUtil.class);
	
	public static short getShort(HttpServletRequest request, String key,
			short defaultValue) {
		String str = request.getParameter(key);
		return BaseParamUtil.getShort(str, defaultValue);
	}

	public static int getInt(HttpServletRequest request, String key,
			int defaultValue) {
		String str = request.getParameter(key);
		return BaseParamUtil.getInt(str, defaultValue);
	}
	public static Integer getInteger(HttpServletRequest request, String key,
			Integer defaultValue) {
		String str = request.getParameter(key);
		return BaseParamUtil.getInteger(str, defaultValue);
	}
	public static Boolean getBoolean(HttpServletRequest request, String key,
			Boolean defaultValue) {
		String str = request.getParameter(key);
		return BaseParamUtil.getBoolean(str, defaultValue);
	}
	/**
	 * str 不空
	 * @param request
	 * @param key
	 * @param defaultValue
	 * @return
	 * @author wff
	 */
	public static Boolean getBooleanRe(HttpServletRequest request, String key,
			Boolean defaultValue) {
		String str = request.getParameter(key);
		return BaseParamUtil.getBooleanRe(str, defaultValue);
	}
	
	public static final Float getFloat(HttpServletRequest request,String numStr, Float defaultNum) {
		String str = request.getParameter(numStr);
		try {
			return BaseParamUtil.getFloat(str, defaultNum);
		} catch (NumberFormatException e) {
			return defaultNum;
		}
	}
	public static final Double getDouble(HttpServletRequest request,String numStr, Double defaultNum) {
		String str = request.getParameter(numStr);
		return BaseParamUtil.getDouble(str, defaultNum);
	}
	
	public static long getLong(HttpServletRequest request, String key,
			long defaultValue) {
		String str = request.getParameter(key);
		return BaseParamUtil.getLong(str, defaultValue);
	}

	public static String getString(HttpServletRequest request, String key,
			String defaultValue) {
		String str = request.getParameter(key);
		return BaseParamUtil.getString(request,str, defaultValue);
	}

	public static String[] getStringValues(HttpServletRequest request, String key){
		String[] values = request.getParameterValues(key);
		if (values==null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = BaseParamUtil.getString(request,values[i], "");
        }
        return encodedValues;
	}
	
	public static Integer[] getIntegerValues(HttpServletRequest request, String key){
        String[] values = request.getParameterValues(key);
        if (values==null) {
            return null;
        }
        int count = values.length;
        Integer[] encodedValues = new Integer[count];
        for (int i = 0; i < count; i++) {
            Integer value=BaseParamUtil.getInteger(values[i],null);
            if(null!=value){
                encodedValues[i]=value;
            }
        }
        return encodedValues;
    }
	
	public static String getString(HttpServletRequest request, String key,
			String[] validValues, String defaultValue) {
		boolean caseSensitive = false;
		return getString(request, key, validValues, defaultValue, caseSensitive);
	}

	public static String getString(HttpServletRequest request, String key,
			String[] validValues, String defaultValue, boolean caseSensitive) {
		String str = request.getParameter(key);
		return BaseParamUtil.getString(str, validValues, defaultValue,
				caseSensitive);
	}

	/**
	 * 设置reponse返回时常
	 * @param response
	 * @param cacheAge
	 * @author xiaojianyu
	 */
	public static void setCacheHeader(HttpServletResponse response, int cacheAge) {
		response.setHeader("Pragma", "Public");
		// HTTP 1.1
		response.setHeader("Cache-Control", "max-age=" + cacheAge);
		response.setDateHeader("Expires", System.currentTimeMillis() + cacheAge
				* 1000L);
	}

	/**
	 * 设置返回no cache
	 * @param response
	 * @author xiaojianyu
	 */
	public static void setNoCacheHeader(HttpServletResponse response) {
		// HTTP 1.0
		response.setHeader("Pragma", "No-cache");
		// HTTP 1.1
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	}



	/**
	 * 判断是否是ajax请求
	 * @param request
	 * @return
	 * @author xiaojianyu
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String xReq = request.getHeader("X-Requested-With");
		return (xReq != null);
	}

	/**
	 * 获取请求的refer
	 * @param request
	 * @return
	 * @author xiaojianyu
	 */
	public static String getRefer(HttpServletRequest request) {
		return request.getHeader("REFERER");
	}

	/**
	 * encode url
	 * @param url
	 * @return
	 * @author xiaojianyu
	 */
	public static String encodeURL(String url) {
		try {
			return java.net.URLEncoder.encode(url, ENCODING);
		} catch (UnsupportedEncodingException e) {
			// do nothing
			return null;
		}
	}

	/**
	 * decode url
	 * @param url
	 * @return
	 * @author xiaojianyu
	 */
	public static String decodeURL(String url) {
		try {
			return java.net.URLDecoder.decode(url, ENCODING);
		} catch (UnsupportedEncodingException e) {
			// do nothing
			return null;
		}
	}

	/**
	 * 获取完整的url
	 * @param request
	 * @return
	 * @author xiaojianyu
	 */
	public static String getRequestCompleteURL(HttpServletRequest request) {
		return request.getRequestURL().append("?").append(
				request.getQueryString()).toString();
	}

	

	/**
	 * 处理js escape后的串
	 * 
	 * @author xiaojianyu
	 * 
	 * @param src
	 * @return
	 */
	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src
							.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src
							.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}

	/**
	 * 在java端模拟js escape
	 * 
	 * @author xiaojianyu
	 * 
	 * @param src
	 * @return
	 */
	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);

		for (i = 0; i < src.length(); i++) {

			j = src.charAt(i);

			if (Character.isDigit(j) || Character.isLowerCase(j)
					|| Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}
	/**
	 * a标签传中文参数接收乱码处理
	 * @param str
	 * @return
	 * @author wyh
	 */
	public static String getAParamByrequest(String content){
		String str = null;
		try {
			str = new String(content.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static BigDecimal getBigDecimal(HttpServletRequest request, String key,
			BigDecimal defaultValue) {
		String str = request.getParameter(key);
		return BaseParamUtil.getBigDecimal(str, defaultValue);
	}
	
	public static Date getDate(HttpServletRequest request, String key,
			Date defaultValue) {
		String str = request.getParameter(key);
		return BaseParamUtil.getDate(str, defaultValue);
	}
	
	/**
	 * 获取ip
	 * @param request
	 * @return
	 */
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getRemoteAddr();
	    }
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}
	
	/**
	 * 获取用户的agent
	 * @param request
	 * @return
	 */
	public static String getUserAgent(HttpServletRequest request) {
		return request.getHeader("user-agent");
	}
}
