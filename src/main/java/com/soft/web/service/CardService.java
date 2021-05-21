package com.soft.web.service;

import com.soft.web.common.AjaxResult;
import com.soft.web.dao.CardMapper;
import com.soft.web.entity.Card;
import com.soft.web.entity.CardQueryParam;
import com.soft.web.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author md
 * @since 2021-03-29
 */
@Service
public class CardService {

    @Autowired
    CardMapper cardMapper;

    public List<Card> selectCards(CardQueryParam cardQueryParam) {
        return cardMapper.selectCards(cardQueryParam);
    }

    public AjaxResult addCard(Card card) {
        card.setStatus(0);
        card.setAddTime(new Date());
        int result = cardMapper.insert(card);
        if(result > 0) {
            return AjaxResult.success("添加成功");
        }
        return AjaxResult.error("添加失败");
    }

    public Card selectCardById(Long id) {
        return cardMapper.selectCardById(id);
    }

    public AjaxResult updateCard(Card card) {
        Card card1 = selectCardById(card.getId());
        if(card.getCardPic()==null) {
            card.setCardPic(card1.getCardPic());
        }
        int result = cardMapper.updateById(card);
        if(result > 0) {
            return AjaxResult.success("修改成功");
        }
        return AjaxResult.error("修改失败");
    }

    public AjaxResult updateStatus(Card card) {
        int result = cardMapper.updateStatus(card);
        if(result > 0) {
            return AjaxResult.success("修改成功");
        }
        return AjaxResult.error("修改失败");
    }

    public AjaxResult deleteCards(String ids) {
        Long[] sids = Convert.toLongArray(ids);
        int result = cardMapper.deleteCards(sids);
        if(result > 0) {
            return AjaxResult.success("删除成功");
        }
        return AjaxResult.error("删除失败");
    }

}
