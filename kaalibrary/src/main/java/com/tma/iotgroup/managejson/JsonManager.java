/**
 * 
 */
package com.tma.iotgroup.managejson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;

/**
 * @author vlmt
 *
 */
public class JsonManager {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tma.iotgroup.kaaservice.KaaAuthentication#getAuthentication(java.lang
	 * .String, int, java.lang.String, java.lang.String, java.lang.String)
	 * 
	 * @param response
	 * 
	 * @return String.
	 */
	public String parseJson(HttpResponse response) throws IllegalStateException, IOException{
		BufferedReader rd = new BufferedReader(
				new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		return result.toString();
	}
}
