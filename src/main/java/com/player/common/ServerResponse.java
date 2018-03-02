package com.player.common;

import com.sun.scenario.effect.impl.sw.sse.SSERendererDelegate;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.ws.Response;
import java.io.Serializable;

public class ServerResponse<T> implements Serializable{
    private int status;
    private String msg;
    private T Date;

    public ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ServerResponse(int status, String msg, T date) {
        this.status = status;
        this.msg = msg;
        Date = date;
    }

    public ServerResponse(int status) {
        this.status = status;
    }

    public ServerResponse(int status, T date) {
        this.status = status;
        Date = date;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getDate() {
        return Date;
    }

    @JsonIgnore
   public  boolean isSussess(){
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public static <T> ServerResponse<T> createBySuccess(){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> createByMassage(String msg){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }

    public static <T> ServerResponse<T> createBySuccess(T date){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),date);
    }

    public static <T> ServerResponse<T> createBySuccess(String msg,T date){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg,date);
    }

    public static <T> ServerResponse<T> createByError(){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }

    public static <T> ServerResponse<T> createByErrorMessage(String msg){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),msg);
    }

    public static <T> ServerResponse<T> createByErrorCodeMessage(int code,String msg){
        return new ServerResponse<T>(code,msg);
    }
}