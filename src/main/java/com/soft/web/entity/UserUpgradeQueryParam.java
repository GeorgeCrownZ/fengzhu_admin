package com.soft.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpgradeQueryParam implements Serializable {

    private String searchKey;
    private String beginTime;
    private String endTime;

}
