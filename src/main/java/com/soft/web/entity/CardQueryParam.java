package com.soft.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("CardQueryParam")
public class CardQueryParam implements Serializable {

    private String searchKey;
    private String beginTime;
    private String endTime;
    private Integer status;

}
