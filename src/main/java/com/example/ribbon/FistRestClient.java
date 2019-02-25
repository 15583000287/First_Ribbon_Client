package com.example.ribbon;

import com.netflix.client.ClientFactory;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.config.ConfigurationManager;
import com.netflix.niws.client.http.RestClient;


/**
 * REST客户端 使用Ribbon实现LoadBalancer（负载均衡）
 */
public class FistRestClient {
    public static void main(String[] args) throws Exception {
        /**
         * 1.设置请求服务器（ConfigurationManager 来设置配置项）
         * my-client 客户端名称
         * localhost:8080,localhost:8081 指定服务器列表    等同于   my-client.ribbon.listOfServers=localhost:8080,localhost:8081
         */
        ConfigurationManager.getConfigInstance().setProperty("my-client.ribbon.listOfServers", "localhost:8080,localhost:8081");

        /**
         * 2.配置规则处理类(MyRule类自定义负载规则)
         */
        ConfigurationManager.getConfigInstance().setProperty( "my-client.ribbon.NFLoadBalancerRuleClassName", MyRule.class.getName());

        //获取REST请求客户端
        RestClient client = (RestClient) ClientFactory.getNamedClient("my-client");
        //创建请求实例
        HttpRequest request = HttpRequest.newBuilder().uri("/user/1").build();
        //发送6次请求服务         使用 RestClient 对象，向“/person/1”地址发出请求
        for(int i=0;i<6;i++){
            HttpResponse response = client.executeWithLoadBalancer(request);
            String result = response.getEntity(String.class);
            System.out.println(result);
        }
    }
}
