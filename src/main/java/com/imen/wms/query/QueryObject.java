package com.imen.wms.query;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryObject {
    @Getter
    @Setter
    private int currentPage = 1;
    @Getter
    @Setter
    private int pageSize = 10;

    private List<String> conditions = new ArrayList<>();//HQL
    private List<Object> params = new ArrayList<>();//查询条件参数

    private boolean isBuild = false;

    private void init() {
        if (!isBuild) {
            this.customizedQuery();
            isBuild = true;
        }
    }
    public String getQuery(){
        this.init();
        if (conditions.size() == 0) {
            return "";
        }
        return " WHERE "+StringUtils.join(conditions," AND ");
    }

    /*public String getQuery() {
        this.init();
        StringBuilder sb = new StringBuilder(80);
        if (conditions.size() == 0) {
            return "";
        }
        for (int index = 0; index < conditions.size(); index++) {
            if (index == 0) {
                sb.append(" WHERE ");
            } else {
                sb.append(" AND ");
            }
            sb.append(conditions.get(index));
        }
        return sb.toString();
    }*/
    //暴露给子类的定制查询

    public List<Object> getParameters() {
        this.init();
        return params;
    }

    protected void addQuery(String condition, Object... param) {
        conditions.add(condition);
        params.addAll(Arrays.asList(param));
    }

    protected void customizedQuery() {
    }

    protected boolean hasLength(String str) {
        return str != null && !"".equals(str.trim());
    }
}
