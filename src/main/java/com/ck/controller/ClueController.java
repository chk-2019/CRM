package com.ck.controller;

import com.ck.dao.ClueDao;
import com.ck.dao.DataDicDao;
import com.ck.dao.UserDao;
import com.ck.domain.*;
import com.ck.service.*;
import com.sun.javaws.IconUtil;
import com.utils.UUIDUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ClueController {
    @Resource
    private ContactsService contactsService;
    @Resource
    private CustomerService customerService;
    @Resource
    private ClueService clueService;
    @Resource
    private UserService userService;
    @Resource
    private TranService tranService;
    @ResponseBody
    @RequestMapping("clue/getUserList.do")
    public List<User> getUserList(){
        List<User> userList = userService.getUserList();
        return userList;
    }
    @RequestMapping("clue/saveClue.do")
    @ResponseBody
    public String saveClue(Clue clue){
        String result = "false";
        int num= clueService.insertClue(clue);
        if (num >= 0 ){
            result = "true";
        }
        return result;
    }
    @RequestMapping("clue/detail.do")
    @ResponseBody
    public ModelAndView goDetail(int id){
        ModelAndView mv = new ModelAndView();
        Clue clueById = clueService.getClueById(id);
        mv.addObject("clue",clueById);
        mv.setViewName("forward:/workbench/clue/detail.jsp");
        return mv;
    }
    @RequestMapping("clue/getActivityListByClueId.do")
    @ResponseBody
    public List<Activity> getActivityByClueId(int clueId){
        List<Activity> activityByClueId = clueService.getActivityByClueId(clueId);
        return activityByClueId;
    }
    @RequestMapping("clue/unbund.do")
    @ResponseBody
    public String deleteClueActivityRal(int ralId){
        String result = "false";
        int num = clueService.deleteClueActRal(ralId);
        if (num==1)
            result = "true";
        return result;
    }
    @ResponseBody
    @RequestMapping("clue/getActivityListByNameAndNotByClueId.do")
    public List<Activity> getActivityListByNameAndNotByClueId(String aName,int clueId){
        List<Activity> actByNameLists= clueService.getActivityListByNameAndNotByClueId(aName, clueId);
        return actByNameLists;
    }
	@ResponseBody
    @RequestMapping("clue/getActivityListByName.do")
    public List<Activity> getActivityListByName(String aName){
        List<Activity> actByNameLists= clueService.getActivityListByName(aName);
        return actByNameLists;
    }
    @RequestMapping("clue/bund.do")
    @ResponseBody
    public String insertClueActRelation(String cid,String aid[]){
        String result = "false";
        int num = clueService.insertClueActRelation(cid, aid);
        if (num==aid.length)
            result = "true";
        return result;
    }
    @RequestMapping("clue/convert.do")
    @ResponseBody
    public String convertCusAndCont(String clueId,String flag, Tran tran){
        String result="false";
        int cid = Integer.parseInt(clueId);
        Clue clueById = clueService.getClueById(cid);
        String uuid = UUIDUtil.getUUID();
        Customer customer = new Customer();
        String company = "";
        //如果交易标识为空，那么就不创建新的交易
        if(flag == null){
            //根据线索的公司名称查询客户表里面有没有这个客户，如果没有就创建新客户
            company = clueById.getCompany();
            customer = customerService.getCustomerByName(company);
            if (null==customer){
                Customer customer1 = new Customer();
                String uuid1 = UUIDUtil.getUUID();
                customer1.setId(uuid1);
                customer1.setOwner(clueById.getOwner());
                customer1.setAddress(clueById.getAddress());
                customer1.setWebsite(clueById.getWebsite());
                customer1.setName(clueById.getCompany());
                customer1.setPhone(clueById.getPhone());
                customerService.insertCustomer(customer1);
            }
            //根据客户的id和联系人的名字查询联系人表中有没有这个联系人，如果没有这个联系人就创建新联系人
            Customer customer2 = customerService.getCustomerByName(company);
            String customerId = customer2.getId();
            String uuid2 = UUIDUtil.getUUID();
            clueById.setId(uuid2);
            Contacts conByCusAndName = contactsService.getConByCusAndName(customerId, clueById.getFullname());
            if (conByCusAndName==null){
                contactsService.insertContacts(clueById,customerId);
            }
            //执行导致到这里没有报错，就可以返回true了
                result = "true";
            return result;
        }
        else{
            String uuid2 = UUIDUtil.getUUID();
            tran.setId(uuid2);
            tranService.insertTran(clueById,tran);
            company = clueById.getCompany();
            customer = customerService.getCustomerByName(company);
            if (null==customer){
                Customer customer1 = new Customer();
                String uuid1 = UUIDUtil.getUUID();
                customer1.setId(uuid1);
                customer1.setOwner(clueById.getOwner());
                customer1.setAddress(clueById.getAddress());
                customer1.setWebsite(clueById.getWebsite());
                customer1.setName(clueById.getCompany());
                customer1.setPhone(clueById.getPhone());
                customerService.insertCustomer(customer1);
            }
            //根据客户的id和联系人的名字查询联系人表中有没有这个联系人，如果没有这个联系人就创建新联系人
            Customer customer2 = customerService.getCustomerByName(company);
            String customerId = customer2.getId();
            String uuid3 = UUIDUtil.getUUID();
            clueById.setId(uuid3);
            Contacts conByCusAndName = contactsService.getConByCusAndName(customerId, clueById.getFullname());
            if (conByCusAndName==null){
                contactsService.insertContacts(clueById,customerId);
            }
            //执行导致到这里没有报错，就可以返回true了
            result = "true";
           return result;
        }
    }
}
