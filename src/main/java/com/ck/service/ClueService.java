package com.ck.service;

import com.ck.domain.Activity;
import com.ck.domain.Clue;

import java.util.List;

public interface ClueService {
    int insertClue(Clue clue);
    Clue getClueById(int id);
    List<Activity> getActivityByClueId(int clueId);
    int deleteClueActRal(int ralId);
    List<Activity> getActivityListByNameAndNotByClueId(String aName,int clueId);
    int insertClueActRelation(String cid,String aid[]);
    List<Activity> getActivityListByName(String aName);
}
