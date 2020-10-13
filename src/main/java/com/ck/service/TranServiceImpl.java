package com.ck.service;

import com.ck.dao.TranDao;
import com.ck.domain.Clue;
import com.ck.domain.Tran;
import com.ck.domain.Tran_History;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TranServiceImpl implements TranService {
    @Resource
    private TranDao tranDao;
    @Override
    public int insertTran(Clue clue,Tran tran) {
        int num = tranDao.insertTran(clue,tran);
        return num;
    }

    @Override
    public int addTran(Tran tran) {
        int i = tranDao.addTran(tran);
        return i;
    }

    @Override
    public Tran getTranById(String id) {
        Tran tranById = tranDao.getTranById(id);
        return tranById;
    }

    @Override
    public List<Tran_History> getTranHisById(String id) {
        List<Tran_History> tranHisById = tranDao.getTranHisById(id);
        return tranHisById;
    }
}
