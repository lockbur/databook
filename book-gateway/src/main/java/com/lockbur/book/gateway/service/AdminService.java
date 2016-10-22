package com.lockbur.book.gateway.service;


import com.lockbur.book.gateway.model.Admin;

/**
 * Service接口 - 管理员
 *
 * @date 2016-10-22 下午05:11:49
 */

public interface AdminService {
    /**
     * 根据用户名判断此用户是否存在（不区分大小写）
     */
    public boolean isExistByUsername(String username);

    /**
     * 根据用户名获取管理员对象，若管理员不存在，则返回null（不区分大小写）
     */
    public Admin getAdminByUsername(String username);

}