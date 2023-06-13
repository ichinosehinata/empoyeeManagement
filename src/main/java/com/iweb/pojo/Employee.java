package com.iweb.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ichinose
 * @date 2023/6/10 16:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Integer eid;
    private String eName;
    private String eidNumber;
    private String ePhone;
    private String eSex;
    private Department department;
    private Post post;

    public Employee(String eName, String eidNumber, String ePhone, String eSex, Department department, Post post) {
        this.eName = eName;
        this.eidNumber = eidNumber;
        this.ePhone = ePhone;
        this.eSex = eSex;
        this.department = department;
        this.post = post;
    }
}
