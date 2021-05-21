package com.soft.web.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

/**
 * <p>
 * 
 * </p>
 *
 * @author md
 * @since 2021-03-29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("Card")
@TableName("fa_card")
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
public class Card implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      /**
     * 风暴商品名称
     */
      private String cardName;

      /**
     * 价格
     */
      private BigDecimal price;

      /**
     * 是否启用
     */
      private Integer status;

      /**
     * 添加时间
     */
      private Date addTime;

      /**
     * 销量
     */
      private Integer saleNum;

      /**
     * 虚拟销量
     */
      private Integer showSaleNum;

      /**
     * 排序
     */
      private Integer sort;

      /**
     * 封面图片
     */
      private String cardPic;

      /**
     * 图文介绍
     */
      private String info;

      /**
     * 回馈天数
     */
      private Integer day;

      /**
     * 回馈比例
     */
      private Float proportion;

      /**
     * 违约比例
     */
      private Float breakContract;

      /**
     * 积分比例
     */
      private BigDecimal score;


}
