# CustomVolley
### 自定义volley封装库，自带数据解析和错误处理方法

##### 使用方法:
你的工程依赖library即可。app里是使用例子。library依赖了volley和Gson库。
##### 该库的优势：
对volley进行了二次封装，把json解析放到了该库里面，和业务层剥离开来，业务层调用后拿到的就是已经解析好的json类，从而做到低耦合。

##### 举个例子：
##### 定义一个接口请求
```
public HttpRequest orderList(Response.Listener<DouBanRadioBean> listener, Response.ErrorListener errorListener){
        String testUrl = "https://www.douban.com/j/app/radio/channels";
        return new HttpRequest<>(Request.Method.GET, testUrl, DouBanRadioBean.class, null, listener, errorListener);
    }
```
##### 使用
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
##### library工程下类文件说明:
ErrorBean、ErrorResult 自定义错误bean

VolleyErrorHelper 对volley错误的统一处理类

HttpRequest 对volley网络请求的封装类

##### app工程类文件说明：
DouBanRadioBean 豆瓣电台信息bean

HttpRequestManager 网络请求管理器，所有的网络请求都可以写在这里面(包含了get和post【带参数和不带参数】请求的例子)

![截图](/capture/1.png)
![截图](/capture/2.png)