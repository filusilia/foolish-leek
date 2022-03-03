package com.ilia.leek.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ilia.leek.mapper.RoleMapper;
import com.ilia.leek.entity.Role;

/**
 * @author Alice on 2021/4/16
 * @version 1.0
 * @since 1.0
 */
@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> {

}

