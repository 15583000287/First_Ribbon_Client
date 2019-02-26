package com.example.ribbon;

import com.netflix.loadbalancer.DummyPing;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.Server;

/**
 * 自定义Ping
 *
 * 要 使 用 自 定 义 的 Ping 类 ， 通 过 修 改
 * <client>.<nameSpace>.NFLoadBalancerPingClassName 配置即可，在此不再赘
 */
public class MyPing implements IPing {
    @Override
    public boolean isAlive(Server server) {
        System.out.println("这是自定义 Ping 实现类：" + server.getHostPort());

        return false;
    }
}
