package com.example.ribbon;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import sun.swing.PrintingStatus;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * 自定义负载规则测试
 */
public class MyRuleTest {
    public static void main(String[] args){
        // 创建负载均衡器
        BaseLoadBalancer lb = new BaseLoadBalancer();
        // 设置自定义的负载规则
        lb.setRule(new MyRule());

        //添加服务器
        List<Server> servers = new ArrayList<>();
        servers.add(new Server("localhost",8080));
        servers.add(new Server("localhost",8081));
        lb.addServers(servers);

        //进行6次服务器选择
        for(int i=0;i<6;i++){
            Server server = lb.chooseServer(null);
            System.out.println(server);
        }
    }
}
