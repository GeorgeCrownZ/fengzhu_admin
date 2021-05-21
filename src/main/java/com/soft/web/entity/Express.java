package com.soft.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2021-03-22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("fa_express")
public class Express implements Serializable {

      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      /**
     * 快递公司名
     */
      private String expressName;

      /**
     * 公司编码
     */
      private String expressCode;


}
