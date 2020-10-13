package com.ck.dao;

import com.ck.domain.Clue;
import com.ck.domain.Tran;
import com.ck.domain.Tran_History;

import java.util.List;
import java.util.Map;

public interface TranDao {
    int insertTran(Clue clue,Tran tran);
    int addTran(Tran tran);
    Tran getTranById(String id);
    List<Tran_History> getTranHisById(String id);
    Map<String,String> getCharts();
}
