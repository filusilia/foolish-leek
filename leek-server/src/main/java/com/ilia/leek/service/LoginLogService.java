package com.ilia.leek.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ilia.leek.mapper.LoginLogMapper;
import com.ilia.leek.entity.LoginLog;
/**
 * @author Alice on 2021/4/20
 * @version 1.0
 * @since 1.0
 */
@Service
public class LoginLogService extends ServiceImpl<LoginLogMapper, LoginLog> {

}
