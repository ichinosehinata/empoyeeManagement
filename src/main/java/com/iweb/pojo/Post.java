package com.iweb.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ichinose
 * @date 2023/6/10 16:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private Integer pid;
    private String pName;

    public Post(Integer pid) {
        this.pid = pid;
    }

    public Post(String pName) {
        this.pName = pName;
    }
}
