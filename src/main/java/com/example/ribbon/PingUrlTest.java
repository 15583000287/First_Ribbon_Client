package com.example.ribbon;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.NoOpPing;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.Server;

import java.util.ArrayList;
import java.util.List;

/**
 * Pring机制(判断服务器是否存活)
 *
 * 在负载均衡器中，提供了 Ping 的机制，每隔一段时间，会去 Ping 服务器，判断服务器是否存活。
 *
 * 该工作由 IPing 接口的实现类负责，如果单独使用 Ribbon，在默认情况下，不会激活 Ping 机制
 *
 * 默认的实现类为 DummyPing。
 *
 *自定义Ping：
 *      1.实现IPing接口
 *      2.实现接口方法
 */
public class PingUrlTest {
    public static void main(String[] args) throws Exception{
        // 创建负载均衡器
        BaseLoadBalancer lb = new BaseLoadBalancer();
        // 添加服务器
        List<Server> servers = new ArrayList<Server>();
        // 8080 端口连接正常
        servers.add(new Server("localhost", 8080));
        // 一个不存在的端口
        servers.add(new Server("localhost", 8888));
        lb.addServers(servers);

        // 设置 IPing 实现类
        lb.setPing(new PingUrl());
        // 设置 Ping 时间间隔为 2 秒
        lb.setPingInterval(2);
        Thread.sleep(6000);
        for(Server s : lb.getAllServers()) {
            System.out.println(s.getHostPort() + " 状态：" + s.isAlive());
        }
    }
}
