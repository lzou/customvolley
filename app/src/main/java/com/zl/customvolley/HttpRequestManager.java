package com.zl.customvolley;

/**
 * Created by zoulu on 16/3/2.
 * 网络请求管理器
 */


import com.android.volley.Request;
import com.android.volley.Response;
import com.zl.library.HttpRequest;


public class HttpRequestManager {

    private static HttpRequestManager sInstance;

    public static HttpRequestManager getInstance(){
        if (sInstance == null) {
            sInstance = new HttpRequestManager();
        }
        return sInstance;
    }

    /**
     * 用get方法从豆瓣获取电台信息
     */
    public HttpRequest orderList(Response.Listener<DouBanRadioBean> listener, Response.ErrorListener errorListener){
        String testUrl = "https://www.douban.com/j/app/radio/channels";
        return new HttpRequest<>(Request.Method.GET, testUrl, DouBanRadioBean.class, null, listener, errorListener);
    }

    /**
     * post请求接口方法  例子
     */
//    public HttpRequest sendHomeWork(Response.Listener<String> listener, Response.ErrorListener errorListener){
//        return new HttpRequest<>(Request.Method.POST, "your url",String.class, null, listener, errorListener);
//    }

    /**
     * post请求接口方法  例子
     * @param bean 请求的参数,转换成json。这个按服务器的要求来,最终传的都是String
     */
//    public HttpRequest sendHomeWork(Response.Listener<String> listener, Response.ErrorListener errorListener, JavaBean bean){
//        return new HttpRequest<>(Request.Method.POST, "your url",String.class, new Gson().toJson(bean), listener, errorListener);
//    }


}
