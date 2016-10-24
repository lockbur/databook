package com.lockbur.book.manager.service;

import com.lockbur.book.gateway.model.Admin;
import com.lockbur.book.gateway.service.AdminService;
import com.lockbur.book.gateway.service.RoleService;

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
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by Administrator on 2016/10/22.
 */
@Service
public class SecurityUserDetailsService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private AdminService adminService;

    @Resource
    private RoleService roleService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminService.getAdminByUsername(username);
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
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        Collection<GrantedAuthority> authorities = getGrantedAuthorities(admin.getId());

        UserDetails userDetails = new User(
                admin.getUsername(),
                admin.getPassword(),
                admin.getAccountEnabled(), accountNonExpired, credentialsNonExpired, accountNonLocked,
                authorities);

        return userDetails;
    }

    // 获得管理角色数组
    public Collection<GrantedAuthority> getGrantedAuthorities(Long id) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        //查询用户权限
        List<String> list = roleService.findAuthority(id);
        logger.info(id + "用户的权限列表：");
        for (String authority : list) {
            logger.info(id + "的权限列表：{}",authority);
            grantedAuthorities.add(new SimpleGrantedAuthority(authority));
        }
//        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_TEST"));
        return grantedAuthorities;
        //return grantedAuthorities.toArray(new GrantedAuthority[grantedAuthorities.size()]);
    }
}
