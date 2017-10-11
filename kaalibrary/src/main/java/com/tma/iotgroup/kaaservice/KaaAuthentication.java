/**
 * 
 */
package com.tma.iotgroup.kaaservice;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;

/**
 * @author vlmt
 *
 */
public interface KaaAuthentication {
	public HttpClientContext getAuthentication(String host, int port, String protocol,String username,String password);
}
