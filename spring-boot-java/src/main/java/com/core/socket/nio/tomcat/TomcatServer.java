package com.core.socket.nio.tomcat;


import javax.servlet.MultipartConfigElement;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

/**
 * https://blog.csdn.net/qq_38245537/article/details/79009448
 * @author zhangzhigang
 *
 */
public class TomcatServer {

	public static void main(String[] args) throws LifecycleException, InterruptedException {
		// 创建tomcat服务器
		Tomcat tomcat = new Tomcat();
		// 设置port
//		tomcatServer.setPort(8085);
		Connector connector = new Connector("HTTP/1.1");
		connector.setPort(8888);
		tomcat.setConnector(connector);
		// 是否设置自动部署
		tomcat.getHost().setAutoDeploy(false);
		// 创建上下文
		StandardContext standardContext = new StandardContext();
		// Path上下文路径
		standardContext.setPath("/demo");
		// 监听上下文
		standardContext.addLifecycleListener(new Tomcat.FixContextListener());
		// tomcat容器添加standardContext上下文
		tomcat.getHost().addChild(standardContext);
		// 创建servlet
		Wrapper wrapper = tomcat.addServlet("/demo", "indexServlet", new IndexServlet());
		// 解析Content-Type:application/json数据
		wrapper.setMultipartConfigElement(new MultipartConfigElement(""));
		// servlet URL映射
		standardContext.addServletMappingDecoded("/index", "indexServlet");
		tomcat.start();
		System.out.println("Tomcat启动成功");
		// 异步进行接收请求
		tomcat.getServer().await();
	}
}
