package com.iweb.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ichinose
 * @date 2023/6/10 16:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    private Integer uid;
    private String username;
    private String password;
    private boolean isAdmin;

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
