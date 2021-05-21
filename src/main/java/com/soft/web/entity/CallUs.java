package com.soft.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author md
 * @since 2021-03-19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("fa_callus")
public class CallUs implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      /**
     * 回复id
     */
      private Long pid;

      /**
     * 用户id
     */
      private Long userId;

      /**
     * 用户昵称
     */
      private String userNick;

    private String userAccount;

      /**
     * 用户姓名
     */
      private String trueName;

      /**
     * 联系方式
     */
      private String mobileNumber;

      /**
     * 内容
     */
      private String saycontent;

    private Date addTime;

    private Date updateTime;


}
