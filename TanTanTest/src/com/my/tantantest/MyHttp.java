package com.my.tantantest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class MyHttp {

	// 响应
	private static HttpResponse mHttpResponse = null;
	// 实体
	private static HttpEntity mHttpEntity = null;

	public static void sendHttp() {
		// 生成一个请求对象
		HttpGet httpGet = new HttpGet(
				"https://api.coursera.org/api/courses.v1?start=0&limit=10");
		// 生成一个Http客户端对象
		HttpClient httpClient = new DefaultHttpClient();

		// 下面使用Http客户端发送请求，并获取响应内容

		InputStream inputStream = null;
		try {
			// 发送请求并获得响应对象
			mHttpResponse = httpClient.execute(httpGet);
			// 获得响应的消息实体
			mHttpEntity = mHttpResponse.getEntity();

			// 获取一个输入流
			inputStream = mHttpEntity.getContent();

			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(inputStream));

			String result = "";
			String line = "";

			while (null != (line = bufferedReader.readLine())) {
				result += line;
			}

			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
