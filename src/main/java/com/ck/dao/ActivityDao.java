package com.ck.dao;

import com.ck.domain.Activity;
import com.ck.domain.ActivityRemark;

import java.util.List;

public interface ActivityDao {
    List<Activity> queryActivity(Activity activity, int startNO, int pageSize);
    int insertActivity(Activity activity);
    int getRowNums();
    int deleteActivity(int acid[]);
    int deleteActivityRemark(int acid[]);
    Activity selectActivityById(int id);
    int updateActivity(Activity activity);
    List<ActivityRemark> selectActivityRemarkByAcId(int activityId);
    ActivityRemark getActivityRemarkById(int id);
    int deleteActivityRemarkById(int id);
    int updateActivityRemark(ActivityRemark activityRemark);

}
