package com.cskaoyan.utils;

public class ResponseUtil {

    public static ResponseVo success(Object data){
        ResponseVo<Object> responseVo = new ResponseVo<>();
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        responseVo.setData(data);
        return responseVo;
    }

    public static ResponseVo fail(Object data,String errmsg,int errno){
        ResponseVo<Object> responseVo = new ResponseVo<>();
        responseVo.setData(data);
        responseVo.setErrmsg(errmsg);
        responseVo.setErrno(errno);
        return responseVo;
    }
}
