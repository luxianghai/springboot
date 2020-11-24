package cn.sea.controller;

import cn.sea.entity.Emp;
import cn.sea.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    private EmpService empService;

    @PostMapping("/update")
    public String update(Emp emp) {
        empService.update(emp);
        return "redirect:/emp/findAll";
    }

    @GetMapping("/find")
    public String find(String id,Model model) {
        model.addAttribute("emp", empService.find(id));
        return "ems/updateEmp";
    }

    // 查询所有员工
    @GetMapping("/findAll")
    public String findAll(Model model) {

        List<Emp> emps = empService.findAll();
        model.addAttribute("emps", emps);
        return "ems/emplist";
    }

    // 添加员工信息
    @PostMapping("/save")
    public String save(Emp emp) {
        empService.save(emp);
        return "redirect:/emp/findAll";
    }

    // 删除员工信息
    @GetMapping("/delete")
    public String delete(String id) {
        empService.delete(id);
        return "redirect:/emp/findAll" ;
    }
}
