package com.yk.response;

import com.yk.constant.Consts;

public class ErrorCommonResponse extends CommonResponse{
    public ErrorCommonResponse(){
        super.setCode(Consts.COMMON_RESPONSE_ERROR_CODE);
        super.setMsg(Consts.COMMON_RESPONSE_ERROR_MSG);
    }

    public ErrorCommonResponse(String data){
        super.setCode(Consts.COMMON_RESPONSE_ERROR_CODE);
        super.setMsg(Consts.COMMON_RESPONSE_ERROR_MSG);
        super.setData(data);
    }

    public ErrorCommonResponse (int code,String msg,String data){
        super.setCode(code);
        super.setMsg(msg);
        super.setData(data);
    }
}
