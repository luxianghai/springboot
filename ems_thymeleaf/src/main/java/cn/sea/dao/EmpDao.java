package cn.sea.dao;

import cn.sea.entity.Emp;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmpDao {

    List<Emp> findAll();

    void save(Emp emp);

    void delete(String id);

    Emp find(String id);

    void update(Emp emp);

}
