package com.soft.web.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@TableName("fa_order_log")
public class OrderLog implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      private String orderId;

      private Long userId;

    private String opName;

    private String tradeStatus;

    private String payStatus;

    private String sendStatus;

    private String remark;

    @TableField(fill = FieldFill.INSERT)
    private Date addTime;

    private transient SimpleDateFormat dateFormat;

}
