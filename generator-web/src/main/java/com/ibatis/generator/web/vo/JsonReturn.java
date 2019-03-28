package com.ibatis.generator.web.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class JsonReturn implements Serializable {



    protected static final int SUCCESS_CODE   = 200;
    protected static final int ERROR_CODE = 500;

    protected int code ;



    protected Object msg;


    public JsonReturn(int code, Object msg) {
        this.code = code;
        this.msg = msg;
    }



    public static JsonReturn success(Object res){
        return new JsonReturn(SUCCESS_CODE,res);
    }




    public static JsonReturn error(String res){
        return new JsonReturn(ERROR_CODE,res);
    }

}
