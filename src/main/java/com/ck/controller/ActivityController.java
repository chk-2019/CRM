package com.ck.controller;

import com.ck.dao.ActivityDao;
import com.ck.dao.UserDao;
import com.ck.domain.Activity;
import com.ck.domain.ActivityRemark;
import com.ck.domain.User;
import com.ck.service.ActivityService;
import com.ck.service.UserService;
import com.utils.DateTimeUtil;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller()
public class ActivityController {
    @Resource
    private UserService userService;
    @Resource
    //获取用户列表
    private ActivityService activityService;
    @RequestMapping("getUserList.do")
    @ResponseBody
    public List<User> getUserList(){
        List<User> userList = userService.getUserList();
        return userList;
    }
    //将用户创建的新的市场活动保存
    @ResponseBody
    @RequestMapping("activity/save.do")
    public boolean insertActivity(Activity activity){
        int num = activityService.insertActivity(activity);
        boolean result = false;
        if (num==1){
            result = true;
           return  result;
        }
        return result;
    }
    //获取已经存在的市场活动列表
    @RequestMapping("activity/pageList.do")
    @ResponseBody
    public Map<Object,Object> queryActivity(Activity activity,int pageNo,int pageSize){
        Map<Object,Object> activityMap = new HashMap<>();
        int total = activityService.getActivityNum();
        List<Activity> activityList = activityService.queryActivity(activity, pageNo, pageSize);
        activityMap.put("total",total);
        activityMap.put("dataList",activityList);
        return activityMap;
    }
    //删除一条或多条市场活动记录
    @ResponseBody
    @RequestMapping(value = "activity/delete.do",produces = "text/plain;charset=utf-8")
    public String deleteActivity(int id[]){
        String success = "false";
        for (int i=0;i<id.length;i++){
            System.out.println(id[i]);
        }
        int removeActivityNum = activityService.removeActivity(id);
        if(removeActivityNum>=0)
            success = "true";
        return success;

    }
    @ResponseBody
    @RequestMapping("activity/getUserListAndActivity.do")
    public Map<Object,Object> getUserListAndActivity(int id){
        Map<Object,Object> userListAndAct = new HashMap<>();
        List<User> userList = userService.getUserList();
        Activity activityById = activityService.getActivityById(id);
        userListAndAct.put("userList",userList);
        userListAndAct.put("activityById",activityById);
        return userListAndAct;
    }
    //修改市场活动
    @ResponseBody
    @RequestMapping("activity/update.do")
    public String updateActivity(Activity activity){
        String success = "false";
        int updateNum = activityService.updateActivity(activity);
        if (updateNum==1)
        success="true";
        return success;
    }
    //跳转到市场活动详情界面
    @ResponseBody
    @RequestMapping("activity/detail.do")
    public ModelAndView goToDetail(int id){
        ModelAndView mv = new ModelAndView();
        Activity activityById = activityService.getActivityById(id);
        mv.addObject("a",activityById);
        mv.setViewName("forward:/workbench/activity/detail.jsp");
        return mv;
    }
    @ResponseBody
    @RequestMapping("activity/getRemarkListByAid.do")
    public List<ActivityRemark> getActivityRemarkById(int activityId){
        List<ActivityRemark> activityRemarkById = activityService.getActivityRemarkByAcId(activityId);
        return activityRemarkById;
    }
    @ResponseBody
    @RequestMapping("activity/deleteRemark.do")
    public String deleteActivityRemark(int id){
        String success = "false";
        int remarkNum = activityService.deleteActivityRemark(id);
        if (remarkNum>=0)
            success = "true";
        return success;
    }

    @ResponseBody
    @RequestMapping("activity/updateRemark.do")
    public ActivityRemark updateActivityRemark(ActivityRemark activityRemark, HttpSession session){
        User user = (User)session.getAttribute("user");
        String name = user.getName();
        activityRemark.setEditBy(name);
        String editTime = DateTimeUtil.getSysTimeOnlyYMD();
        activityRemark.setEditTime(editTime);
        activityRemark.setEditFlag("1");
        String success = "false";
        ActivityRemark result = new ActivityRemark();
        int updateActivityRemark = activityService.updateActivityRemark(activityRemark);
        if (updateActivityRemark==1){
            result = activityService.getActivityRemarkById(activityRemark.getId());
        }
        return result;

    }
//    @ResponseBody
//    @RequestMapping(value = "activity/delete.do",produces = "text/plain;charset=utf-8")
//    public Boolean deleteActivity(int id[], HttpServletResponse response) {
//        Boolean success = false;
//        for (int i = 0; i < id.length; i++) {
//            System.out.println(id[i]);
//        }
////        int removeActivityNum = activityService.removeActivity(id);
////        if(removeActivityNum>=0)
//        success = true;
//        response.setContentType("text/javascript; charset=utf-8");
//        return success;
//    }
}
