package com.example.ribbon;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;

/**
 * 自定义负载规则
 *      1.自定义类事件IRule接口
 *      2.实现接口中的方法,choose方法中定义规则
 *
 *
 *  在实际环境中，如果要实现自定义的负载规则，可能还需要结合各种因素，例如考虑具
 *  体业务的发生时间、服务器性能等，实现中还可能还涉及使用计算器、数据库等技术，具体
 * 情形会更为复杂，本例的负载规则较为简单，目的是让读者了解负载均衡的原理。
 */
public class MyRule implements IRule {
    ILoadBalancer lb;

    public MyRule(){

    }

    public MyRule(ILoadBalancer lb){
        this.lb = lb;
    }

    @Override
    public Server choose(Object o) {
        //获取全部服务器
        List<Server> servers = lb.getAllServers();
        //只返回第一个Server对象 s(此处可定义规则返回Server)
        return servers.get(0);
    }

    @Override
    public void setLoadBalancer(ILoadBalancer iLoadBalancer) {
        this.lb = iLoadBalancer;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return this.lb;
    }
}
