package com.ck.controller;

import com.ck.dao.CustomerDao;
import com.ck.dao.UserDao;
import com.ck.domain.Customer;
import com.ck.domain.Tran;
import com.ck.domain.Tran_History;
import com.ck.domain.User;
import com.ck.service.CustomerService;
import com.ck.service.TranService;
import com.ck.service.UserService;
import com.utils.DateTimeUtil;
import com.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class TransactionController {
    @Autowired
    private ServletContext servletContext;
    @Resource
    private UserService userService;
    @Resource
    private CustomerService customerService;
    @Resource
    private TranService tranService;
    @ResponseBody
    @RequestMapping("transaction/add.do")
    public ModelAndView addTransaction(){
        ModelAndView mv = new ModelAndView();
        List<User> userList = userService.getUserList();
        mv.addObject("userList",userList);
        mv.setViewName("forward:/workbench/transaction/save.jsp");
        return mv;
    }
    @RequestMapping("transaction/getCustomerName.do")
    @ResponseBody
    public List<String> getCustomerName(String name){
        List<String> strings = customerService.getCustomerName(name);
        return strings;
    }
    @RequestMapping("transaction/save.do")
    @ResponseBody
    public ModelAndView saveTransaction(HttpSession session,Tran transaction,String customerName){

        ModelAndView mv = new ModelAndView();
        String uuid = UUIDUtil.getUUID();
        transaction.setId(uuid);
        User user = (User) session.getAttribute("user");
        String createBy = user.getName();
        transaction.setCreateBy(createBy);
        transaction.setCreateTime(DateTimeUtil.getSysTime());
        Customer customerByName = customerService.getCustomerByName(customerName);
        if (customerByName==null){
            Customer customer = new Customer();
            customer.setId(UUIDUtil.getUUID());
            customer.setOwner(transaction.getOwner());
            customer.setCreateBy(transaction.getCreateBy());
            customer.setCreateTime(transaction.getCreateTime());
            customer.setName(customerName);
            customerService.insertCustomer(customer);
        }
        customerByName = customerService.getCustomerByName(customerName);
        transaction.setCustomerId(customerByName.getId());
        int i = tranService.addTran(transaction);
        if (i==1)
            mv.setViewName("redirect:/workbench/transaction/index.jsp");
        return mv;
    }
    @ResponseBody
    @RequestMapping("transaction/detail.do")
    public ModelAndView getTranById(String id){
        ModelAndView mv = new ModelAndView();
        //根据前端传过来的ID从后台查询到相应的交易对象，并且把这个对象添加到上下文域中
        Tran tranById = tranService.getTranById(id);
        Map<String,String> proMap = (Map<String, String>)servletContext.getAttribute("proMap");

        String possibility = proMap.get(tranById.getStage());
        tranById.setPossibility(possibility);
                mv.addObject("t",tranById);
        mv.setViewName("forward:/workbench/transaction/detail.jsp");
        return mv;
    }
    @RequestMapping("transaction/getHistoryListByTranId.do")
    @ResponseBody
    public List<Tran_History> getHistoryListById(String tranId){
        List<Tran_History> tranHisById = tranService.getTranHisById(tranId);
        Map<String,String> proMap = (Map<String, String>)servletContext.getAttribute("proMap");
//        String possibility = proMap.get(tranHisById.getStage());
//        tranHisById.setPossibility(possibility);
        for (Tran_History th:tranHisById){
            String possibility = proMap.get(th.getStage());
            th.setPossibility(possibility);
        }
        System.out.println(tranHisById);
        return tranHisById;

    }

}
