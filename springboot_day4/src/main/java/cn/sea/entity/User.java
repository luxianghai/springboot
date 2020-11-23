package cn.sea.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class User {

    private String name;
    private Integer age;
    private Date bir;
}
