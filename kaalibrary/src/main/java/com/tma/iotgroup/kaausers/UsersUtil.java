/**
 * 
 */
package com.tma.iotgroup.kaausers;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;

import com.tma.iotgroup.kaaservice.*;

/**
 * @author vlmt
 *
 */
public class UsersUtil implements KaaAuthentication {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tma.iotgroup.kaaservice.KaaAuthentication#getAuthentication(java.lang
	 * .String, int, java.lang.String, java.lang.String, java.lang.String)
	 * 
	 * @param host This is the host Kaa Server
	 * 
	 * @param port This is the port Kaa Server
	 * 
	 * @param protocol This is the protocol Kaa Server
	 * 
	 * @param username Username is login Kaa Server
	 * 
	 * @param password Password is login Kaa Server
	 * 
	 * @return HttpClientContext.
	 */
	public HttpClientContext getAuthentication(String host, int port, String protocol, String username,String password) {
		HttpHost targetHost = new HttpHost(host,port,protocol);
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));

		AuthCache authCache = new BasicAuthCache();
		authCache.put(targetHost, new BasicScheme());

		// Add AuthCache to the execution context
		final HttpClientContext context = HttpClientContext.create();
		context.setCredentialsProvider(credsProvider);
		context.setAuthCache(authCache);
		return context;
	}

}
