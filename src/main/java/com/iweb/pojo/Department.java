package com.iweb.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ichinose
 * @date 2023/6/10 16:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    private Integer did;
    private String dName;

    public Department(Integer did) {
        this.did = did;
    }

    public Department(String dName) {
        this.dName = dName;
    }
}
