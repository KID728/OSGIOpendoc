/**
 * kid728.com
 * 
 * OGSI
 */
package com.kid728.osgi.service.user;

/**
 * desc: �û���¼��֤����ӿ�
 * 
 * @author kid728
 */
public interface Validator {
    /**
     * �����û�����������֤�û��Ƿ��ܹ���¼
     * 
     * @param username
     * @param password
     * @return boolean
     * @throws Exception
     */
    public boolean validate(String username, String password) throws Exception;
}
