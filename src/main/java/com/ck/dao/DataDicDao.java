package com.ck.dao;

import com.ck.domain.Dic_Value;

import java.util.List;

public interface DataDicDao {
    List<String> selectDicType();
    List<Dic_Value> selectValueByType(String typeCode);
}
