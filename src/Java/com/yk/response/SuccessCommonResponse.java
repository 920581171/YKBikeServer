package com.yk.response;

import com.yk.constant.Consts;

public class SuccessCommonResponse extends CommonResponse{
    public SuccessCommonResponse(){
        super.setCode(Consts.COMMON_RESPONSE_SUCCESS_CODE);
        super.setMsg(Consts.COMMON_RESPONSE_SUCCESS_MSG);
    }

    public SuccessCommonResponse (Object data){
        super.setCode(Consts.COMMON_RESPONSE_SUCCESS_CODE);
        super.setMsg(Consts.COMMON_RESPONSE_SUCCESS_MSG);
        super.setData(data);
    }

    public SuccessCommonResponse (String msg){
        super.setCode(Consts.COMMON_RESPONSE_SUCCESS_CODE);
        super.setMsg(msg);
    }

    public SuccessCommonResponse (int code,String msg){
        super.setCode(code);
        super.setMsg(msg);
    }

    public SuccessCommonResponse (String msg,Object data){
        super.setCode(Consts.COMMON_RESPONSE_SUCCESS_CODE);
        super.setMsg(msg);
        super.setData(data);
    }

    public SuccessCommonResponse (int code,String msg,Object data){
        super.setCode(code);
        super.setMsg(msg);
        super.setData(data);
    }
}
