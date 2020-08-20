package org.carshop.utilities;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HttpClientUtils {
    private static final Logger log = LoggerFactory.getLogger(HttpClientUtils.class);
    private static final CloseableHttpClient httpClient;

    static {
        httpClient = HttpClients.createDefault();
    }

    public static UrlEncodedFormEntity formEntityFromMap(Map<String, ?> map) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        for (Entry<String, ?> entry : map.entrySet()) {
            String name = entry.getKey();
            Object value = entry.getValue();
            NameValuePair pair = new BasicNameValuePair(name, value.toString());
            params.add(pair);
        }
        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
            return entity;
        } catch (UnsupportedEncodingException e) {
            log.error("oops: ", e);
            throw new RuntimeException(e);
        }
    }

    public static UrlEncodedFormEntity formEntityFromObject(Object obj) {
        BeanWrapper wrapper = new BeanWrapperImpl(obj);
        PropertyDescriptor[] descriptors = wrapper.getPropertyDescriptors();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        for (PropertyDescriptor item : descriptors) {
            String name = item.getName();
            Object value = wrapper.getPropertyValue(name);
            if (!"class".equals(name) &&
                    value != null) {
                NameValuePair pair = new BasicNameValuePair(name, value.toString());
                params.add(pair);
            }
        }
        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
            return entity;
        } catch (UnsupportedEncodingException e) {
            log.error("oops: ", e);
            throw new RuntimeException(e);
        }
    }

    public static String post(String url, HttpEntity entity) {
        return post(url, entity, null);
    }

    public static String post(String url, HttpEntity entity, String type) {
        CloseableHttpResponse httpResponse = null;
        try {
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(10000)   //设置连接超时时间
                    .setConnectionRequestTimeout(60000) // 设置请求超时时间
                    .setSocketTimeout(120000)
                    .setRedirectsEnabled(true)//默认允许自动重定向
                    .build();
            HttpPost request = new HttpPost(url);
            request.setConfig(requestConfig);
            if ("xml".equals(type)) {
                request.setHeader("Content-Type", "text/xml; charset=UTF-8");
            }
            request.setEntity(entity);

            httpResponse = httpClient.execute(request);
            StatusLine statusLine = httpResponse.getStatusLine();
            if (statusLine.getStatusCode() != 200) {
                return null;
            }
            HttpEntity responseEntity = httpResponse.getEntity();
            if (responseEntity == null) {
                return null;
            }
            String response = getContent(responseEntity.getContent(), "utf-8");
            return response;
        } catch (Exception e) {
            log.error("ooooooooooooops: ", e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
            } catch (IOException e) {
                // do nothing
            }
        }
    }

    public static String get(String url) {
        log.debug("http-get请求：{}", url);
        CloseableHttpResponse httpResponse = null;
        try {
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(10000)   //设置连接超时时间
                    .setConnectionRequestTimeout(90000) // 设置请求超时时间
                    .setSocketTimeout(90000)
                    .setRedirectsEnabled(true)//默认允许自动重定向
                    .build();
            HttpGet request = new HttpGet(url);
            request.setConfig(requestConfig);

            httpResponse = httpClient.execute(request);
            StatusLine statusLine = httpResponse.getStatusLine();
            if (statusLine.getStatusCode() != 200) {
                return null;
            }
            HttpEntity responseEntity = httpResponse.getEntity();
            if (responseEntity == null) {
                return null;
            }
            String response = getContent(responseEntity.getContent(), "utf-8");
            log.debug("http-get回复：{}", response);
            return response;
        } catch (Exception e) {
            log.error("ooooooooooooops: ", e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
            } catch (IOException e) {
                // do nothing
            }
        }
    }

    //获取request内容
    public static String getRequestAsString(HttpServletRequest request) throws Exception {
        String charset = "utf-8";
        InputStream stream = request.getInputStream();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset));
            StringWriter writer = new StringWriter();

            char[] chars = new char[256];
            int count = 0;
            while ((count = reader.read(chars)) > 0) {
                writer.write(chars, 0, count);
            }

            return writer.toString();
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }

    //返回内容到response
    public static void asyncReturnResult(HttpServletResponse response, String msg) {
        try {
            response.setHeader("Cache-Control", "no-cache");
            response.setContentType("text/html;charset=utf-8");//注意返回的内容格式
            PrintWriter pw = response.getWriter();
            pw.print(msg);
            pw.close();
        } catch (IOException e) {
            log.error("http post返回内容报错：" + e.getMessage());
        }
    }

    public static String getContent(InputStream is, String charset) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, charset));
            StringBuilder sb = new StringBuilder();
            int ch;
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            return sb.toString();
        } catch (Exception e) {
            log.error("ooooooooooops", e);
            throw new RuntimeException(e);
        }
    }


}
