# CustomVolley
###对volley的二次封装库

#####使用方法:
######将library导入到你的工程即可。app里是使用例子。library依赖了volley和Gson库。
#####该库的优势：
######对volley进行了二次封装，把json解析放到了该库里面，和业务层剥离开来，业务层调用后拿到的就是已经解析好的json类，从而做到低耦合。

#####举个例子：
```
public HttpRequest orderList(Response.Listener<DouBanRadioBean> listener, Response.ErrorListener errorListener){
        String testUrl = "https://www.douban.com/j/app/radio/channels";
        return new HttpRequest<>(Request.Method.GET, testUrl, DouBanRadioBean.class, null, listener, errorListener);
    }
```

```
HttpRequestManager.getInstance().orderList(new Response.Listener<DouBanRadioBean>() {
            @Override
            public void onResponse(DouBanRadioBean response) {
                //your code
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyErrorHelper.showErrorMessage(MainActivity.this,error);
            }
        }).commit(MainActivity.this);
```
#####library工程类文件说明:
######ErrorBean、ErrorResult 自定错误bean
######VolleyErrorHelper 对volley错误的处理
######HttpRequest 对volley网络请求的封装

#####app工程类文件说明：
######DouBanRadioBean 豆瓣电台信息bean
######HttpRequestManager 网络请求管理器，所有的网络请求都可以写在这里面(包含了get和post【带参数和不带参数】请求的例子)