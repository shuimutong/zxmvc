package cn.ourpass.zxmvc.servlet;

import java.io.IOException;
import java.lang.reflect.Field;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.ourpass.zxdata.helpkits.AnnotationHelp;
import cn.ourpass.zxmvc.annotation.XAutowired;
import cn.ourpass.zxmvc.annotation.XController;
import cn.ourpass.zxmvc.bean.EntityBean;
import cn.ourpass.zxmvc.bean.RequestMappingBean;
import cn.ourpass.zxmvc.utils.ClassUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * Servlet implementation class XDispatcherServlet
 */
public class XDispatcherServlet extends HttpServlet {
    private final static Logger log = Logger.getLogger(XDispatcherServlet.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XDispatcherServlet() {
        super();
    }

	@Override
    public void init() throws ServletException {
        super.init();
        XServletMaping.scanPaths();
        XServletMaping.printRes();
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    System.out.println("Hello");
	    JSONObject result = new JSONObject();
	    result.put("req", 1);
	    result.put("res", 2);
	    result.put("requestURI", request.getRequestURI());
	    result.put("requestURL", request.getRequestURL());
	    result.put("contextPath", request.getContextPath());
	    log.info(request.getContextPath() + "," + request.getLocalName() + "," + request.getServletPath());
	    
	    String requestUri = request.getRequestURI().replace(request.getContextPath(), "");
	    if(requestUri.startsWith("/")) {
	        requestUri = requestUri.substring(1);
	    }
	    
	    RequestMappingBean requestBean = XServletMaping.getUrlMap().get(requestUri);
	    String destClazzName = requestBean.getClazzName();
	    //获取目标类对象
	    EntityBean entityBean = XServletMaping.getBeanMap().get(destClazzName);
	    ClassUtils.initBeans(XServletMaping.getBeanMap(), entityBean);
	    try {
            requestBean.getMethod().invoke(entityBean.getO(), request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
	    
	    
	    
	    XServletMaping.returnJson(request, response, result);
	}

}
