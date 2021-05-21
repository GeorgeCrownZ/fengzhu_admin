package com.soft.web.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author md
 * @since 2021-03-27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGroupScore implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 下单用户id
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String userNick;

    /**
     * 上级用户id
     */
    private Long topUserId;

    /**
     * 上级用户名称
     */
    private String topUserNick;

    /**
     * 下单积分
     */
    private BigDecimal billScore;

    /**
     * 上级原来积分
     */
    private BigDecimal topOldScore;

    /**
     * 上级现在积分
     */
    private BigDecimal topNewScore;

    /**
     * 活动积分时间
     */
    private Date addTime;


}
