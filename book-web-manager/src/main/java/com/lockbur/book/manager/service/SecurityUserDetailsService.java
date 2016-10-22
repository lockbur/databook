package com.lockbur.book.manager.service;

import com.lockbur.book.dao.AdminDao;
import com.lockbur.book.domain.AdminEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/10/22.
 */
@Service
public class SecurityUserDetailsService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private AdminDao adminDao;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminEntity admin = adminDao.getAdminByUsername(username);
        if (admin == null) {
            logger.info("管理员[" + username + "]不存在!\"\"");
            throw new UsernameNotFoundException("管理员[" + username + "]不存在!");
        }
        logger.info("admin.getIsAccountLocked:" + admin.getAccountLocked());
        if (admin.getAccountLocked() == true) {
            admin.setLoginFailureCount(0);
            admin.setAccountLocked(false);
            admin.setLockedDate(null);
            //adminDao.update(admin);
        }
        // admin.setAuthorities(getGrantedAuthorities(admin));
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        Collection<GrantedAuthority> authorities = getGrantedAuthorities(admin.getId());

        UserDetails userDetails = new User(
                admin.getUsername(),
                admin.getPassword().toLowerCase(),
                admin.getAccountEnabled(), accountNonExpired, credentialsNonExpired, accountNonLocked,
                authorities);

        return userDetails;
    }


    // 获得管理角色数组
    public Collection<GrantedAuthority> getGrantedAuthorities(Long id) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();

        //查询用户权限
        //TODO..
        //logger.info(admin.getName() + "用户的权限列表：");
        logger.info(id + "用户的权限列表：");
//        for (Role role : admin.getRoleSet()) {
//            //grantedAuthorities.add(new GrantedAuthorityImpl(role.getValue()));
//            for (Module module : role.getModuleSet()) {
//                System.out.print(module.getValue() + ",");
//                grantedAuthorities.add(new GrantedAuthorityImpl(module.getValue()));
//            }
//        }
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return grantedAuthorities;
        //return grantedAuthorities.toArray(new GrantedAuthority[grantedAuthorities.size()]);
    }
}
