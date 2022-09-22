package com.demo.security;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author HP
 */
@Repository
public interface UserDao extends BaseMapper<UserInfo> {
}
