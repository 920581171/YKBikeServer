package com.yk.response;

import com.yk.constant.Consts;

import java.util.Date;

public class ErrorCommonResponse extends CommonResponse{
    public ErrorCommonResponse(){
        super.setCode(Consts.COMMON_RESPONSE_ERROR_CODE);
        super.setMsg(Consts.COMMON_RESPONSE_ERROR_MSG);
    }

    public ErrorCommonResponse (Object object){
        super.setCode(Consts.COMMON_RESPONSE_ERROR_CODE);
        super.setMsg(Consts.COMMON_RESPONSE_ERROR_MSG);
        super.setData(object);
    }

    public ErrorCommonResponse (String msg){
        super.setCode(Consts.COMMON_RESPONSE_ERROR_CODE);
        super.setMsg(msg);
    }

    public ErrorCommonResponse (int code,String msg){
        super.setCode(code);
        super.setMsg(msg);
    }

    public ErrorCommonResponse (int code,String msg,Object object){
        super.setCode(code);
        super.setMsg(msg);
        super.setData(object);
    }
}
