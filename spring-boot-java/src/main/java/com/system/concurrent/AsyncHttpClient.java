package com.system.concurrent;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.reactor.IOReactorException;

/**
 * 高并发-异步-异步回调
 * 
 *
 */
public abstract class AsyncHttpClient {
	private static final int DEFAULT_MAX_TOTAL = 512;
	
	private static final int DEFAULT_MAX_PER_ROUTE = 64;
	
	private static final int DEFAULT_CONNECTION_TIMEOUT = 5000;
	
	private static final int DEFAULT_SOCKET_TIMEOUT = 3000;
	
	private static final int DEFAULT_TIMEOUT = 1000;
	
	private static CloseableHttpAsyncClient asyncHttpClient;
	
	static {
		DefaultConnectingIOReactor ioReactor;
		try {
			ioReactor = new DefaultConnectingIOReactor(
					IOReactorConfig
					.custom()
					.setSoKeepAlive(true)
					.build());
			
			PoolingNHttpClientConnectionManager pcm = new PoolingNHttpClientConnectionManager(ioReactor);
			pcm.setMaxTotal(DEFAULT_MAX_TOTAL);
			pcm.setDefaultMaxPerRoute(DEFAULT_MAX_PER_ROUTE);
			
			RequestConfig requestConfig = RequestConfig.custom()
			.setConnectTimeout(DEFAULT_CONNECTION_TIMEOUT)
			.setSocketTimeout(DEFAULT_SOCKET_TIMEOUT)
			.setConnectionRequestTimeout(DEFAULT_TIMEOUT)
			.build();
			
			
			asyncHttpClient =  HttpAsyncClients
					.custom()
					.setThreadFactory(
						new BasicThreadFactory
						.Builder()
						.namingPattern("AsyncHttpThread-%d")
						.build())
					.setConnectionManager(pcm)
					.setDefaultRequestConfig(requestConfig)
					.build();
		} catch (IOReactorException e) {
			e.printStackTrace();
		}
		
	}
	
	public static CloseableHttpAsyncClient getInstance() {
		return asyncHttpClient;
	}
}