package com.ck.service;

import com.ck.domain.Activity;
import com.ck.domain.ActivityRemark;

import java.util.List;

public interface ActivityService {
    public List<Activity> queryActivity(Activity activity, int pageNo, int pageSize);
    public int insertActivity(Activity activity);
    int getActivityNum();
    int removeActivity(int acid[]);
    Activity getActivityById(int id);
    int updateActivity(Activity activity);
    List<ActivityRemark> getActivityRemarkByAcId(int activityId);
    int deleteActivityRemark(int id);
    int updateActivityRemark(ActivityRemark activityRemark);
    ActivityRemark getActivityRemarkById(int id);
}
