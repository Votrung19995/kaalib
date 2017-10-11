/**
 * 
 */
package com.tma.iotgroup.manageuser;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tma.iotgroup.kaamodels.Authority;
import com.tma.iotgroup.kaamodels.User;
import com.tma.iotgroup.kaausers.*;
import com.tma.iotgroup.managejson.JsonManager;

/**
 * @author vlmt
 *
 */
public class ManageUser extends UsersUtil {
	/*
	 * Get All User form device
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
	 * @return HttpResponse.
	 */
	//Get All User:
	public List<User> getAllUser(String host, int port, String protocol, String username, String password) throws ParseException {
        HttpClientContext context = getAuthentication(host,port, protocol,username, password);
		HttpClient client = HttpClientBuilder.create().build();
		JsonManager jsonManager = new JsonManager();
		List<User> users = new ArrayList<User>();
		try {
			HttpResponse response = client.execute(new HttpGet("http://"+host+":"+port+"/kaaAdmin/rest/api/users"),context);
			String json = jsonManager.parseJson(response);
			JSONParser parser = new JSONParser();
		    JSONArray array = (JSONArray) parser.parse(json);
		    Object[] jsonObjects = array.toArray();
		    for(Object object: jsonObjects){
		    	//Add json:
		    	 JSONObject jsonObject = (JSONObject)object;
		    	 String id = jsonObject.get("id").toString();
		    	 String username1 = jsonObject.get("username").toString();
		    	 String externalUid = jsonObject.get("externalUid").toString();
		    	 String tenantId = jsonObject.get("tenantId").toString();
		    	 String authority = jsonObject.get("authority").toString();
		    	 String firstName = jsonObject.get("firstName").toString();
		    	 String lastName = jsonObject.get("lastName").toString();
		    	 String email = jsonObject.get("mail").toString();
		    	 User user = new User(id, username1, externalUid, tenantId, authority, firstName, lastName, email);
		    	 users.add(user);
		    	 
		    }
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		return users;
	}
    //Get user authentication status
	public HttpResponse checkAuthentiction(String host, int port, String protocol, String username, String password){
		
		HttpClientContext context = getAuthentication(host,port, protocol,username, password);
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		try {
			response = client.execute(new HttpGet("http://"+host+":"+port+"/kaaAdmin/rest/api/auth/checkAuth"),context);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		return response;
	}
	//Create/Edit user
	public void createUser(String host, int port, String protocol, User user) throws UnsupportedEncodingException{
		HttpClientContext context = getAuthentication(host,port, protocol,"admin","admin123");
		HttpClient client = HttpClientBuilder.create().build();
		//Send Multy:
		JSONObject notification = new JSONObject();
		notification.put("username", user.getUsername());
		notification.put("firstName",user.getFirstname());
		notification.put("lastName",user.getLastname());
		notification.put("mail", user.getEmail());
		notification.put("authority", user.getAuthor());
		StringEntity params = new StringEntity(notification.toString());
		HttpPost post = new HttpPost("http://"+host+":"+port+"/kaaAdmin/rest/api/user");
		post.addHeader("content-type", "application/json");
		post.setEntity(params);
		HttpClient httpclient = HttpClientBuilder.create().build();
		try {
			HttpResponse response = httpclient.execute(post,context);
			System.out.println("Create user response: "+response.getStatusLine());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
