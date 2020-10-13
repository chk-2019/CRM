package com.ck.service;

import com.ck.dao.DataDicDao;
import com.ck.domain.Clue;
import com.ck.domain.Dic_Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public  class DataDicServiceImpl implements DataDicService {
    @Resource
    private DataDicDao dataDicDao;
    @Override
    public List<String> getDicType() {
        List<String> dicType = dataDicDao.selectDicType();
        return dicType;
    }

    @Override
    public List<Dic_Value> getDicValueByType(String type) {
        List<Dic_Value> dic_values = dataDicDao.selectValueByType(type);
        return dic_values;
    }

    @Override
    public Map<String, List> getValueTypeMap() {
        Map<String,List> valueTypeMap = new HashMap<>();
        List<String> dicTypes = dataDicDao.selectDicType();
        for (String type:dicTypes){
            List<Dic_Value> dic_values = dataDicDao.selectValueByType(type);
            valueTypeMap.put(type,dic_values);
        }
        return valueTypeMap;
    }


}
