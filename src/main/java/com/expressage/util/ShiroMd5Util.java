package com.expressage.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.expressage.pojo.Employee;

public class ShiroMd5Util {
	//添加user的密码加密方法
    public static String  SysMd5(Employee employee) {
        String hashAlgorithmName = "MD5";//加密方式  
        
        Object crdentials =employee.getPassword();//密码原值  
        
        ByteSource salt = ByteSource.Util.bytes(employee.getAccount());//以账号作为盐值  
        
        int hashIterations = 1024;//加密1024次  
        
        SimpleHash hash = new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations);
        
        return hash.toString();
    }  
    
  //添加user的密码加密方法
    public static String  SysMd5(String password,String account) {
        String hashAlgorithmName = "MD5";//加密方式  
        
        Object crdentials =password;//密码原值  
        
        ByteSource salt = ByteSource.Util.bytes(account);//以账号作为盐值  
        
        int hashIterations = 1024;//加密1024次  
        
        SimpleHash hash = new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations);
        
        return hash.toString();
    }  
}
