package com.soft.web.dao;

import com.soft.web.entity.UserGroupScore;
import com.soft.web.entity.UserUpgrade;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft.web.entity.UserUpgradeQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author md
 * @since 2021-03-27
 */
@Mapper
@Repository
public interface UserUpgradeMapper extends BaseMapper<UserUpgrade> {

    public List<UserUpgrade> selectUpgradList(UserUpgradeQueryParam queryParam);

    public List<UserGroupScore> selectUpgradeScoreList(UserUpgradeQueryParam queryParam);

}
