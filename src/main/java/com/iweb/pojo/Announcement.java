package com.iweb.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ichinose
 * @date 2023/6/10 16:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Announcement {
    private Integer aid;
    private String aName;
    private String aContext;

}
