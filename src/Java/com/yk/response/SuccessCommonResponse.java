package com.yk.response;

import com.yk.constant.Consts;

public class SuccessCommonResponse extends CommonResponse{
    public SuccessCommonResponse(){
        super.setCode(Consts.COMMON_RESPONSE_SUCCESS_CODE);
        super.setMsg(Consts.COMMON_RESPONSE_SUCCESS_MSG);
    }

    public SuccessCommonResponse(String data){
        super.setCode(Consts.COMMON_RESPONSE_SUCCESS_CODE);
        super.setMsg(Consts.COMMON_RESPONSE_SUCCESS_MSG);
        super.setData(data);
    }

    public SuccessCommonResponse (int code,String msg,String data){
        super.setCode(code);
        super.setMsg(msg);
        super.setData(data);
    }
}
