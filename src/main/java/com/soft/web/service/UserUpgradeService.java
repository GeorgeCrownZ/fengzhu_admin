package com.soft.web.service;

import com.soft.web.entity.UserGroupScore;
import com.soft.web.entity.UserUpgrade;
import com.soft.web.dao.UserUpgradeMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft.web.entity.UserUpgradeQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author md
 * @since 2021-03-27
 */
@Service
public class UserUpgradeService extends ServiceImpl<UserUpgradeMapper, UserUpgrade>{

    @Autowired
    UserUpgradeMapper userUpgradeMapper;

    public List<UserUpgrade> selectUpgradList(UserUpgradeQueryParam queryParam) {
        return userUpgradeMapper.selectUpgradList(queryParam);
    }

    public List<UserGroupScore> selectUpgradeScoreList(UserUpgradeQueryParam queryParam) {
        return userUpgradeMapper.selectUpgradeScoreList(queryParam);
    }
}
