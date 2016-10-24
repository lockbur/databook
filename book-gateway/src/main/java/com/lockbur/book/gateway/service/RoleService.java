package com.lockbur.book.gateway.service;

import java.util.List;

/**
 * Created by Administrator on 2016/10/24.
 */
public interface RoleService {

    public List<String> findAuthority(long id);
}
