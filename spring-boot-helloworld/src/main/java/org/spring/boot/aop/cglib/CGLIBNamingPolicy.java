package org.spring.boot.aop.cglib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.cglib.core.NamingPolicy;
import net.sf.cglib.core.Predicate;

/**
 * CGLIB实现动态代理 代理命名策略
 * 
 * @author zhangzhigang
 *
 */
public class CGLIBNamingPolicy implements NamingPolicy {
	
	private static CGLIBNamingPolicy INSTANCE;
	
	private static final Logger log = LoggerFactory.getLogger(CGLIBNamingPolicy.class);

	@Override
    public String getClassName(String prefix, String source, Object key, Predicate names) {
    	// prefix  org.spring.boot.aop.cglib.CGLIBUserService
    	// source net.sf.cglib.proxy.Enhancer
        if (prefix == null) {
            prefix = "net.sf.cglib.empty.Object";
        } else if (prefix.startsWith("java")) {
            prefix = "$" + prefix;
        }
        String base =
            prefix + "$$" + 
            source.substring(source.lastIndexOf('.') + 1) +
            getTag() + "$$" +
            key.hashCode();
        String attempt = base;
        int index = 2;
        // 此处判断很重要,否则会抛异常: attempted  duplicate class definition for name: "ByCGLIB"
        while (names.evaluate(attempt))
            attempt = base + "_" + index++;
        return attempt;
    }
	
	public static CGLIBNamingPolicy getInstance() {
		if (null == INSTANCE) {
			synchronized (CGLIBNamingPolicy.class) {
				if (null == INSTANCE) {
					INSTANCE = new CGLIBNamingPolicy();
				}
			}
		}
		return INSTANCE;
	}

    /**
     * Returns a string which is incorporated into every generated class name.
     * By default returns "ByCGLIB"
     */
    private String getTag() {
        return "ByCGLIBProxy";
    }
}
