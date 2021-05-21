package com.soft.web.dao;

import com.soft.web.entity.Card;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft.web.entity.CardQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author md
 * @since 2021-03-29
 */
@Mapper
@Repository
public interface CardMapper extends BaseMapper<Card> {

    public List<Card> selectCards(CardQueryParam cardQueryParam);

    public Card selectCardById(@Param("id")Long id);

    public int updateStatus(Card card);

    public int deleteCards(@Param("ids")Long[] ids);

}
