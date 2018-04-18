package ${basePkg}.web.action;

import ${basePkg}.domain.${className};
import ${basePkg}.query.${className}QueryObject;
import ${basePkg}.service.I${className}Service;
import ${basePkg}.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

public class ${className}Action extends BaseAction{
    private static final long serialVersionUID = 6887934604174049132L;
    @Setter
    private I${className}Service ${objectName}Service;
    @Getter
    private ${className} ${objectName}=new ${className}();
    @Getter
    private ${className}QueryObject qo=new ${className}QueryObject();

    @RequiredPermission("${className}列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        ActionContext.getContext().put("pageResult",${objectName}Service.query(qo));
        return LIST;
    }

    @Override
    @RequiredPermission("${className}编辑")
    public String input() throws Exception {
        if(${objectName}.getId()!=null){
            ${objectName}=${objectName}Service.get(${objectName}.getId());
        }
        return INPUT;
    }

    @RequiredPermission("${className}保存或更新")
    public String saveOrUpdate() throws Exception{
        try {
            if(${objectName}.getId()==null){
                ${objectName}Service.save(${objectName});
                super.addActionMessage("保存成功");
            }else{
                ${objectName}Service.update(${objectName});
                super.addActionMessage("修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            super.addActionError("操作失败:"+e.getMessage());
        }
        return SUCCESS;
    }

    @RequiredPermission("${className}删除")
    public String delete() throws Exception{
        ${objectName}Service.delete(${objectName}.getId());
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print("删除成功");
        return NONE;
    }

    public void prepareSaveOrUpdate() throws Exception{
        if (${objectName}.getId() != null) {
            ${objectName} = ${objectName}Service.get(${objectName}.getId());
        }
    }

}
