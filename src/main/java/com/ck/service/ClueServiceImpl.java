package com.ck.service;

import com.ck.dao.ClueDao;
import com.ck.dao.UserDao;
import com.ck.domain.Activity;
import com.ck.domain.Clue;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClueServiceImpl implements ClueService {
    @Resource
    private ClueDao clueDao;
    @Resource
    private UserDao userDao;
    @Override
    public int insertClue(Clue clue) {
        int num = clueDao.insertClue(clue);
        return num;
    }

    @Override
    public Clue getClueById(int id) {
        Clue clue = clueDao.selectClueById(id);
        return clue;
    }

    @Override
    public List<Activity> getActivityByClueId(int clueId) {
        List<Activity> activityByClueId = clueDao.getActivityByClueId(clueId);
//        for (Activity activity:activityByClueId){
//            String owner = activity.getOwner();
//            String nameById = userDao.getNameById(owner);
//            activity.setO     wner(nameById);
//        }
        return activityByClueId;
    }

    @Override
    public int deleteClueActRal(int ralId) {
        int num = clueDao.deleteClueActivityRelation(ralId);
        return num;
    }

    @Override
    public List<Activity> getActivityListByNameAndNotByClueId(String aName, int clueId) {
        List<Activity> activityList = clueDao.selectActivityListByNameAndNotByClueId(aName, clueId);
        return activityList;
    }

    @Override
    public int insertClueActRelation(String cid, String aid[]) {
        int num = 0;
        for (String acid:aid){
            clueDao.insertClueActRelation(cid,acid);
            num++;
        }
        return num;

    }
	@Override
    public List<Activity> getActivityListByName(String aName) {
        List<Activity> activities = clueDao.selectActivityListByName(aName);
        return activities;
    }
}
