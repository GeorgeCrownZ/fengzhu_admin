package com.soft.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
@TableName("fa_channel")
public class Channel implements Serializable {

      @TableId(value = "channel_id", type = IdType.AUTO)
      private Long channelId;

    private String channelName;

    private String channelUrl;

    private LocalDateTime createTime;


}
