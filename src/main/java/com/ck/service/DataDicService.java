package com.ck.service;

import com.ck.dao.DataDicDao;
import com.ck.domain.Clue;
import com.ck.domain.Dic_Value;

import java.util.List;
import java.util.Map;

public interface DataDicService {
    List<String> getDicType();
    List<Dic_Value> getDicValueByType(String type);
    Map<String,List> getValueTypeMap();

}
