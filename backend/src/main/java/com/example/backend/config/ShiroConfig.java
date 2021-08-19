package com.example.backend.config;

import com.example.backend.shiro.MyRealm;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     * Filter工厂，设置拦截条件和跳转条件
     *
     * @param manager 管理器
     */
    @Bean
    public ShiroFilterFactoryBean filterFactoryBean(DefaultWebSecurityManager manager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(manager);

        //设置页面对role和permission的审核
        Map<String,String> map=new HashMap<>();
        map.put("/main","authc");
        map.put("/admin","roles[admin]");
        map.put("/update","perms[update]");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
        return shiroFilterFactoryBean;
    }

    /**
     * 将自己的realm加入容器
     */
    @Bean
    public MyRealm myRealm(){
        return new MyRealm();
    }

    /**
     * 将myRealm注入manager，管理自定义realm
     *
     * @param myRealm 自定义realm
     */
    @Bean
    public DefaultWebSecurityManager manager(MyRealm myRealm){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm);
        return manager;
    }
}
