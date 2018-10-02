/**
 * kid728.com
 * 
 * OGSI
 */
package com.kid728.osgi.service.user;

/**
 * desc: 用户登录验证服务接口
 * 
 * @author kid728
 */
public interface Validator {
    /**
     * 根据用户名和密码验证用户是否能够登录
     * 
     * @param username
     * @param password
     * @return boolean
     * @throws Exception
     */
    public boolean validate(String username, String password) throws Exception;
}
