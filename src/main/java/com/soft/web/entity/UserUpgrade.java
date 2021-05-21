package com.soft.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
public class UserUpgrade implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 用户Id
     */
      private Integer userId;

      /**
     * 用户昵称
     */
      private String userNick;

      /**
     * 升级等级
     */
      private String newGrade;

      /**
     * 之前等级
     */
      private String oldGrade;

      /**
     * 1 前台 2后台
     */
      private Integer type;

      /**
     * 说明
     */
      private String info;

      /**
     * 后台操作人员
     */
      private String people;

      /**
     * 添加时间
     */
      private Date addTime;


}
