package com.soft.web.service;

import com.soft.web.annotation.OprateLogAOP;
import com.soft.web.dao.MemberMapper;
import com.soft.web.dao.ShopMapper;
import com.soft.web.entity.*;
import com.soft.web.util.Convert;
import com.soft.web.util.PasswordHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MemberService {
    @Resource
    private MemberMapper memberMapper;

    @Resource
    private ShopMapper shopMapper;

    public List<Member> memberlist(String searchkey) {
        return memberMapper.memberlist(searchkey);
    }

    public int enable(Long id, Integer status) {
        return memberMapper.enable(id,status);
    }

    @OprateLogAOP(model = "用户管理", method = "添加用户", remark = "#member.userAccount")
    public int addsave(Member member) {
        PasswordHelper passwordHelper=new PasswordHelper();
        //生成登录密码
        PwdData pwdData = new PwdData(member.getUserAccount(),member.getPassword(), member.getPasswordSalt());
        PwdData pwdData1 = passwordHelper.encryptPassword(pwdData);
        member.setPasswordSalt(pwdData1.getSalt());
        member.setPassword(pwdData1.getPassword());

        //生成支付密码
        pwdData.setPassword(member.getPayPassword());
        PwdData pwdData2 = passwordHelper.encryptPassword(pwdData);
        member.setPayPassword(pwdData2.getPassword());

        member.setUserState(1);
        return memberMapper.insert(member);
    }

    public Member getByid(Long id) {
        return memberMapper.selectById(id);
    }

    @OprateLogAOP(model = "用户管理", method = "修改用户", remark = "#member.userAccount")
    public int editsave(Member member) {
        PasswordHelper passwordHelper=new PasswordHelper();
        Member db = memberMapper.selectById(member.getId());
        db.setUserAccount(member.getUserAccount());
        db.setUserNick(member.getUserNick());
        db.setTrueName(member.getTrueName());
        db.setMobileNumber(member.getMobileNumber());
        db.setPassword(member.getPassword());
        db.setPincode(member.getPincode());
        if(member.getHeadPic()!=null){
            db.setHeadPic(member.getHeadPic());
        }
        if(member.getPassword()!=null){
            //生成登录密码
            PwdData pwdData = new PwdData(db.getUserAccount(),member.getPassword(), db.getPasswordSalt());
            PwdData pwdData1 = passwordHelper.encryptPassword(pwdData);
            db.setPassword(pwdData1.getPassword());
//            db.setPassword(member.getPassword());
        }
        if(member.getPayPassword()!=null){
            //生成支付密码
            PwdData pwdData = new PwdData(db.getUserAccount(),member.getPayPassword(), db.getPasswordSalt());
            PwdData pwdData2 = passwordHelper.encryptPassword(pwdData);
            db.setPayPassword(pwdData2.getPassword());
        }
        return memberMapper.updateById(db);
    }

    @OprateLogAOP(model = "用户管理", method = "删除用户", remark = "#ids")
    public int deleteByIds(String ids) {
        Long[] user_ids = Convert.toLongArray(ids);
        return memberMapper.deleteInBatch(user_ids);
    }

    public int setEmployee(Long id, Integer status) {
        return memberMapper.setEmployee(id,status);
    }

    public List<UserAddress> selectAddressByUserId(@Param("userId")Long userId) {
        return memberMapper.selectAddressByUserId(userId);
    }

    public int employeesave(Long userId, Long shopId) {
        Member member = memberMapper.selectById(userId);
        Shop shop = shopMapper.selectById(shopId);
        if(shopId!=0L){
            member.setIsEmployee(1);
            member.setShopId(shopId);
            member.setShopName(shop.getShopName());
        }
        else{
            member.setIsEmployee(0);
            member.setShopId(0L);
            member.setShopName("");
        }
        return memberMapper.updateById(member);
    }
}
