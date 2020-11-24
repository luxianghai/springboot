package cn.sea.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp {

    private String id;
    private String name;
    private String salary;
    private Integer age;
    private Date bir;
}
