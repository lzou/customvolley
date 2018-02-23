package com.zl.library;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

/**
 * Created by zoulu on 16/4/1.
 * volley错误处理类
 */
public class VolleyErrorHelper {
    private static final int INTERSERVERERROR = 10001;
    private static final int TIMEOUT = 10002;
    private static final int NONETWORK = 10003;
    private static final int UNKNOWNERROR = 10004;
    private static final int PARSEERROR = 10005;

    /**
     * 根据返回的错误，分辨错误类型并做出相应的处理
     */
    public static String getMessage(Object error,Context context){
        ErrorBean err = new ErrorBean();
        ErrorResult result = new ErrorResult();
        if(error instanceof TimeoutError){
            err.setCode(TIMEOUT);
            err.setDescription("Timeout Error");
            result.setError(err);
            return new Gson().toJson(result);
        }else if(isServerProblem(error)){
            return handleServerError(error,context);
        }else if(isNetworkProblem(error)){
            err.setCode(NONETWORK);
            err.setDescription("Network Error");
            result.setError(err);
            return new Gson().toJson(result);
        }else if(isParseProblem(error)){
            err.setCode(PARSEERROR);
            err.setDescription("Parse Error");
            result.setError(err);
            return new Gson().toJson(result);
        }
        err.setCode(UNKNOWNERROR);
        err.setDescription("Unknown Error");
        result.setError(err);
        return new Gson().toJson(result);
    }


    //判断是否是网络错误
    private static boolean isNetworkProblem(Object error) {
        return error instanceof NetworkError;
    }

    //判断是否是服务端错误
    private static boolean isServerProblem(Object error) {
        return (error instanceof ServerError) || (error instanceof AuthFailureError);
    }

    private static boolean isParseProblem(Object error){
        return (error instanceof ParseError);
    }

    private static String handleServerError(Object error,Context context){
        NetworkResponse response = ((VolleyError) error).networkResponse;
        ErrorBean err = new ErrorBean();
        ErrorResult result = new ErrorResult();
        if(response != null){
            if(response.statusCode >= 400 && response.statusCode < 500){
                return "Network Problem";
            }else if(response.statusCode >= 500){
                err.setCode(INTERSERVERERROR);
                err.setDescription("Internal Server Error");
                result.setError(err);
                return new Gson().toJson(result);
            }
        }
        err.setCode(UNKNOWNERROR);
        err.setDescription("Unknown Error");
        result.setError(err);
        return new Gson().toJson(result);
    }

    /**
     * 显示错误信息
     */
    public static void showErrorMessage(Context context,VolleyError error){
        Toast.makeText(context, new Gson().fromJson(getMessage(error, context),ErrorResult.class).getError().getDescription(), Toast.LENGTH_SHORT).show();
    }
}
