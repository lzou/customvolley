package com.zl.library;


/**
 * Created by zoulu on 16/3/25.
 * 网络请求错误返回信息
 */
public class ErrorResult {
        private ErrorBean error;
        public void setError(ErrorBean error){
            this.error = error;
        }
        public ErrorBean getError(){
            return this.error;
        }
}
