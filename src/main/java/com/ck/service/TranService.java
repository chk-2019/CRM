package com.ck.service;

import com.ck.domain.Clue;
import com.ck.domain.Tran;
import com.ck.domain.Tran_History;

import java.util.List;

public interface TranService {
    int insertTran(Clue clue,Tran tran);
    int addTran(Tran tran);
    Tran getTranById(String id);
    List<Tran_History> getTranHisById(String id);
}
