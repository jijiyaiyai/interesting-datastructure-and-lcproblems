package com.franky.designpattern.Proxy;

interface IHttp {
    void request(String sendData);
    void onSuccess(String receivedData);
}
class HttpUtil implements IHttp {
    @Override
    public void request(String sendData) {
        System.out.println("网络请求中...");
        System.out.println(sendData);
    }

    @Override
    public void onSuccess(String receivedData) {
        System.out.println(receivedData);
        System.out.println("网络请求完成。");
    }
}
class HttpProxy implements IHttp {
    private final HttpUtil httpUtil;

    public HttpProxy(HttpUtil httpUtil) {
        this.httpUtil = httpUtil;
    }

    @Override
    public void request(String sendData) {
        httpUtil.request(sendData);
    }

    @Override
    public void onSuccess(String receivedData) {
        httpUtil.onSuccess(receivedData);
    }
}

public class StaticProxy {
    public static void main(String[] args) {
        HttpUtil httpUtil = new HttpUtil();
        HttpProxy proxy = new HttpProxy(httpUtil);
        proxy.request("request data");
        proxy.onSuccess("received result");
    }
}
