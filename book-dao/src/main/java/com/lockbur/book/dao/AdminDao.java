package com.lockbur.book.dao;

import com.lockbur.book.domain.AdminEntity;

/**
 * Created by Administrator on 2016/10/22.
 */
public interface AdminDao {

    /**
     * 保存到数据库
     *
     * @param entity
     */
    public void insert(AdminEntity entity);

    /**
     * 根据用户名判断此用户是否存在（不区分大小写）
     */
    public boolean isExistByUsername(String username);

    /**
     * 根据用户名获取管理员对象，若管理员不存在，则返回null（不区分大小写）
     */
    public AdminEntity getAdminByUsername(String username);
}
