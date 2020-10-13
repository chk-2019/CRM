package com.ck.dao;

import com.ck.domain.Activity;
import com.ck.domain.Clue;

import java.util.List;

public interface ClueDao {
    int insertClue(Clue clue);
    Clue selectClueById(int id);
    List<Activity> getActivityByClueId(int clueId);
    int deleteClueActivityRelation(int ralId);
    List<Activity> selectActivityListByNameAndNotByClueId(String aName,int clueId);
    int insertClueActRelation(String cid,String aid);
    List<Activity> selectActivityListByName(String name);
}
