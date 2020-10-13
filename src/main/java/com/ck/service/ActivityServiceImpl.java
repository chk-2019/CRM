package com.ck.service;

import com.ck.dao.ActivityDao;
import com.ck.domain.Activity;
import com.ck.domain.ActivityRemark;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Resource
    private ActivityDao activityDao;
    @Override
    public List<Activity> queryActivity(Activity activity, int pageNo, int pageSize) {
        int startNo = (pageNo-1)*2;
        List<Activity> activityList=activityDao.queryActivity(activity, startNo, pageSize);
        return activityList;
    }

    @Override
    //插入一个市场活动
    public int insertActivity(Activity activity) {
        int num = activityDao.insertActivity(activity);
        return num;
    }

    @Override
    //获取市场活动的总数量
    public int getActivityNum() {
        int rowNums = activityDao.getRowNums();
        return rowNums;
    }

    @Override
    @Transactional
    public int removeActivity(int[] acid) {
        activityDao.deleteActivityRemark(acid);
        int deleteActivityNum = activityDao.deleteActivity(acid);
        return deleteActivityNum;
    }

    @Override
    public Activity getActivityById(int id) {
        Activity activityById = activityDao.selectActivityById(id);
        return activityById;

    }

    @Override
    public int updateActivity(Activity activity) {
        int updateNum = activityDao.updateActivity(activity);
        return updateNum;
    }

    @Override
    public List<ActivityRemark> getActivityRemarkByAcId(int activityId) {
        List<ActivityRemark> activityRemarks = activityDao.selectActivityRemarkByAcId(activityId);
        return activityRemarks;
    }

    @Override
    public int deleteActivityRemark(int id) {
        int remarkNum = activityDao.deleteActivityRemarkById(id);
        return remarkNum;
    }

    @Override
    public int updateActivityRemark(ActivityRemark activityRemark) {
        int nums = activityDao.updateActivityRemark(activityRemark);
        return nums;
    }

    @Override
    public ActivityRemark getActivityRemarkById(int id) {
        ActivityRemark activityRemarkById = activityDao.getActivityRemarkById(id);
        return activityRemarkById;
    }
}
