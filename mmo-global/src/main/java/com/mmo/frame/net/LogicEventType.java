package com.mmo.frame.net;

/**
 * 事件类型
 * Created by miaopeng on 4-17
 */
public enum LogicEventType {

    /**
     * 内网RPC客户端注册进来
     */
    LOGIC_RPCCLIENT_REGIST,
    /**
     * 内网rpc连接断开
     */
    LOGIC_RPCCLIENT_DISCONNECT,
    /**
     * 内网rpc请求
     */
    LOGIC_RPCCLIENT_REQUEST_COMMING_EVENT,


    /**
     * 客户端http请求注册进来
     */
    LOGIC_HTTPCLIENT_REGIST,
    /**
     * 收到外网服务器发过来的http协议请求
     */
    LOGIC_HTTP_REQUEST_COMMING_EVENT,
    /**
     * http客户端关闭了连接
     */
    LOGIC_HTTPCLIENT_DISCONNECT,


    /**
     * 收到其他服务器发过来的协议包
     */
    LOGIC_SERVER_PROTO_COMMING_EVENT,
    /**
     * 收到客户端发过来协议包
     */
    LOGIC_CLIENT_PROTO_COMMING_EVENT,
    /**
     * 发送协议包大小统计
     */
    LOGIC_STAISTIC_SEND_PROTO_LEN,
    /**
     * 客户端刚刚建立连接还没有注册
     */
    LOGIC_CLIENT_REGISTING,
    /**
     * 客户端断开连接
     */
    LOGIC_CLIENT_DISCONNECT,
    /**
     * 服务器断开连接
     */
    LOGIC_SERVER_DISCONNECT,

}
