package cn.sea.service.impl;

import cn.sea.dao.EmpDao;
import cn.sea.entity.Emp;
import cn.sea.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpDao empDao;

    @Override
    @Transactional( propagation = Propagation.SUPPORTS)
    public List<Emp> findAll() {
        return empDao.findAll();
    }

    @Override
    public void save(Emp emp) {
        emp.setId(UUID.randomUUID().toString());
        empDao.save(emp);
    }

    @Override
    public void delete(String id) {
        empDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Emp find(String id) {
        return empDao.find(id);
    }

    @Override
    public void update(Emp emp) {
        empDao.update(emp);
    }
}
