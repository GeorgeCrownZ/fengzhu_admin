package com.soft.web.controller;

import com.soft.web.common.AjaxResult;
import com.soft.web.common.MyListUtils;
import com.soft.web.entity.Card;
import com.soft.web.entity.CardQueryParam;
import com.soft.web.service.CardService;
import com.soft.web.util.FileUploadUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("card")
public class CardController {

    @Autowired
    CardService cardService;

    @RequiresPermissions("card:view")
    @GetMapping("card")
    public ModelAndView card()
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("card/card");
        return view;
    }

    @PostMapping("/cardlist")
    public Object cardlist(CardQueryParam cardQueryParam) {
        List<Card> cards = cardService.selectCards(cardQueryParam);
        return MyListUtils.getDataTable(cards);
    }

    @PostMapping("/updown")
    public AjaxResult updown(Card card) {
        return cardService.updateStatus(card);
    }

    @PostMapping("/deletecards")
    public AjaxResult deletecards(String ids) {
        return cardService.deleteCards(ids);
    }

    @GetMapping("/addcard")
    public ModelAndView toaddcard(Card card) {
        return new ModelAndView("card/addcard");
    }

    /**
     * 通用上传请求
     */
    @PostMapping("/upload")
    public AjaxResult uploadFile(MultipartFile file) {
        try {
            // 上传文件路径
            String filePath = com.soft.web.util.Global.getCard();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
            ajax.put("url", fileName);
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    @PostMapping("/addcard")
    public AjaxResult addcard(Card card, @RequestParam("cardPict") MultipartFile cardPict) {
        if (!cardPict.isEmpty()) {
            String webPath = null;
            try {
                webPath = FileUploadUtils.upload(com.soft.web.util.Global.getCard(), cardPict);
            } catch (IOException e) {
                AjaxResult.error(e.getMessage());
            }
            card.setCardPic(webPath);
        }
        return cardService.addCard(card);
    }

    @GetMapping("/updatecard/{id}")
    public ModelAndView toupdatecard(@PathVariable("id")Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Card card = cardService.selectCardById(id);
        modelAndView.addObject("card", card);
        modelAndView.setViewName("card/updatecard");
        return modelAndView;
    }

    @PostMapping("/updatecard")
    public AjaxResult updatecard(Card card, @RequestParam("cardPict") MultipartFile cardPict) {
        if (!cardPict.isEmpty()) {
            String webLogoPath = null;
            try {
                webLogoPath = FileUploadUtils.upload(com.soft.web.util.Global.getCard(), cardPict);
            } catch (IOException e) {
                e.printStackTrace();
                return AjaxResult.error("修改失败");
            }
            card.setCardPic(webLogoPath);
        }

        return cardService.updateCard(card);
    }

    @RequiresPermissions("cardorder:view")
    @GetMapping("cardorder")
    public ModelAndView cardorder()
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("card/cardorder");
        return view;
    }
}
