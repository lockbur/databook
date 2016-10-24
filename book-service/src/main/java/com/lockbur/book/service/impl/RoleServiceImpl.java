package com.lockbur.book.service.impl;

import com.lockbur.book.dao.AuthorityDao;
import com.lockbur.book.gateway.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/10/24.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private AuthorityDao authorityDao;

    @Override
    public List<String> findAuthority(long id) {
        List<String> result = authorityDao.findAuthorityByRole(id);
        return result;
    }
}
