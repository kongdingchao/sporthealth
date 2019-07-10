package com.sporthealth.common.thrift.impl;

import com.sporthealth.common.thrift.SubscriptionService;
import org.apache.thrift.TProcessor;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportFactory;

/**
 * @program: sporthealth
 * @description: 订阅服务
 * @author: kongdingchao
 * @create: 2019-07-09 22:30
 **/
public class ThriftServer {
    public static void main(String[] args) throws Exception {
/*
        // 定义服务器使用的socket类型
        TNonblockingServerSocket tNonblockingServerSocket = new TNonblockingServerSocket(8899);

        // 创建服务器参数
        THsHaServer.Args arg = new THsHaServer.Args(tNonblockingServerSocket).minWorkerThreads(2).maxWorkerThreads(4);

        // 请求处理器
        SubscriptionService.Processor<SubscriptionServiceImpl> processor = new SubscriptionService.Processor<>(new SubscriptionServiceImpl());

        // 配置传输数据的格式
        arg.protocolFactory(new TBinaryProtocol.Factory(true, true));
        //arg.protocolFactory(new TCompactProtocol.Factory());
        // 配置数据传输的方式
        arg.transportFactory(new TFramedTransport.Factory());
        // 配置处理器用来处理rpc请求
        arg.processorFactory(new TProcessorFactory(processor));

        // 本示例中使用半同步半异步方式的服务器模型
        TServer server = new THsHaServer(arg);*/

        TProcessor tprocessor = new SubscriptionService.Processor<SubscriptionService.Iface>(new SubscriptionServiceImpl());
        TServerSocket serverTransport = new TServerSocket(8899);
        TThreadPoolServer.Args trArgs = new TThreadPoolServer.Args(serverTransport);
        trArgs.processor(tprocessor);
        // 使用二进制来编码应用层的数据
        trArgs.protocolFactory(new TBinaryProtocol.Factory(true, true));
        // 使用普通的socket来传输数据
        trArgs.transportFactory(new TTransportFactory());
        TThreadPoolServer server = new TThreadPoolServer(trArgs);
        System.out.println("Thrift Server Started!");
        // 启动服务
        server.serve();
    }
}
