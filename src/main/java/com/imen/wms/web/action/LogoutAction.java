package com.imen.wms.web.action;

import com.imen.wms.util.UserContext;

public class LogoutAction extends BaseAction {
    private static final long serialVersionUID = 5426542808337287602L;

    @Override
    public String execute() throws Exception {
        UserContext.setEmployee(null);
        return LOGIN;
    }
}
