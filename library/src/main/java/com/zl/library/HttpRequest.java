package com.zl.library;

/**
 * Created by zoulu on 16/3/2.
 * 对volley二次封装
 */

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


public class HttpRequest<T> extends Request<T>{
    private RequestQueue mRequestQueue;
    private static final String CHARSET = "utf-8";

    private final Class<T> clazz;
    private final Listener<T> listener;
    private final String mRequestBody;
    private final Gson gson = new Gson();

    /** Content type for request. */
    private static final String PROTOCOL_CONTENT_TYPE =
            String.format("application/json; charset=%s", CHARSET);
    private static final Map<String, String> sHeaders = new HashMap<>();
    private static final String PROTOCOL_CHARSET = "utf-8";
    private static final String TAG = "CustomVolley";

    /**
     * 设置公共标准http header项
     * @param name 头项名称
     * @param value 头项值
     */
    public static void setCommonHeaderEntry(String name, String value){
        sHeaders.put(name, value);
    }

    /**
     * 请求接口方法
     * @param method         请求方式 GET or POST
     * @param url            接口地址
     * @param clazz          返回结果对应的bean
     * @param requestBody    post请求发送的内容
     * @param listener       请求成功监听
     * @param errorListener  请求失败监听
     */
    public HttpRequest(int method, String url,Class<T> clazz,String requestBody, Listener<T> listener, ErrorListener errorListener) {
        super(method, url, errorListener);
        this.listener = listener;
        this.clazz = clazz;
        mRequestBody = requestBody;
    }

    //此处因为response返回值需要json数据,和JsonObjectRequest类一样即可
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,HttpHeaderParser.parseCharset(response.headers));
            //如果参数传的是String类型，返回值就是String 则不适用gson解析。
            if(clazz.isAssignableFrom(String.class) ){
                return (Response<T>) Response.success(jsonString, HttpHeaderParser.parseCacheHeaders(response));
            }else {
                return Response.success(gson.fromJson(jsonString, clazz), HttpHeaderParser.parseCacheHeaders(response));
            }
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }catch (JsonSyntaxException e){
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        if(listener != null){
            listener.onResponse(response);
        }
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError{
        return sHeaders;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        try {
            return mRequestBody == null ? null : mRequestBody.getBytes(PROTOCOL_CHARSET);
        } catch (UnsupportedEncodingException uee) {
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                    mRequestBody, PROTOCOL_CHARSET);
            return null;
        }
    }

    //设置内容类型
    @Override
    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }

    /**
     * 提交请求
     */
    public HttpRequest commit(Context context){
        addToRequestQueue(context, this, TAG);
        return this;
    }

    /**
     * 提交请求
     */
    public void commit(Context context,Object tag){
        if(tag!=null) {
            addToRequestQueue(context, this, tag);
        }else {
            addToRequestQueue(context, this, TAG);
        }
    }

    /**
     * 取消请求
     * @param tag  请求的tag
     */
    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    private <T> void addToRequestQueue(Context context, Request<T> req, Object tag) {
        // set the default tag if tag is empty
        req.setTag(tag == null ? TAG : tag);
        getRequestQueue(context).add(req);
    }

    private RequestQueue getRequestQueue(Context context) {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context);
        }
        return mRequestQueue;
    }

}

