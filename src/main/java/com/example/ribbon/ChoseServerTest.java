package com.example.ribbon;

import com.netflix.loadbalancer.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 负载均衡器使用自带负载规则(也可以自定义负载规则)实现loadBalancer
 *
 * RoundRobinRule：轮询调度规则 (系统默认的规则)
 *
 *
 *
 * AvailabilityFilteringRule：可用过滤规则   无法连接的服务器
 *                                           并发数过高的服务器(可设置并发量)
 * WeightedResponseTimeRule：加权响应时间规则   为每个服务器赋予一个权重值，服务器的响应时间
 *                                              越长，该权重值就是越少，这个规则会随机选择服务器，这个权重值有可能会决定服务器的选择
 * ZoneAvoidanceRule：区回避规则
 *                      为每个服务器赋予一个权重值，服务器的响应时间越长，该权重值就是越少，这个规则会随机选择服务器，这个权重值有可能会决定服务器的选择
 * BestAvailableRule：忽略“短路”的服务器，并选择并发数较低的服务器。
 * RandomRule：顾名思义，随机选择可用的服务器
 * RetryRule：含有重试的选择逻辑，如果使用 RoundRobinRule 选择服务器无法连接，那么将会重新选择服务器
 */
public class ChoseServerTest {
    public static void main(String[] args){
        //创建负载均衡器（使用了 BaseLoadBalancer 这个负载均衡器，）
//        ILoadBalancer lb = new BaseLoadBalancer();
        ILoadBalancer lb = new BaseLoadBalancer();  //使用默认RoundRobinRule
        //添加服务器
        List<Server> servers = new ArrayList<>();
        servers.add(new Server("localhost",8080));
        servers.add(new Server("localhost",8081));
        lb.addServers(servers);
        //进行6次服务器选择
        for(int i=0;i<6;i++){
            Server s = lb.chooseServer(null);
            System.out.println(s);
        }
    }
}
