package  ${basePkg}.service.impl;

import  ${basePkg}.dao.impl. ${className}DAOImpl;
import  ${basePkg}.domain.${className};
import  ${basePkg}.query.PageResult;
import  ${basePkg}.query.${className}QueryObject;
import  ${basePkg}.service.I${className}Service;
import lombok.Setter;

import java.util.List;

public class  ${className}ServiceImpl implements I${className}Service {
    @Setter
    private  ${className}DAOImpl  ${objectName}Dao;
    @Override
    public void save(${className} ${objectName}) {
        ${objectName}Dao.save(${objectName});
    }

    @Override
    public void update(${className} ${objectName}) {
        ${objectName}Dao.update(${objectName});
    }

    @Override
    public void delete(Long id) {
        ${objectName}Dao.delete(id);
    }

    @Override
    public  ${className} get(Long id) {
        return  ${objectName}Dao.get(id);
    }

    @Override
    public List<${className}> listAll() {
        return  ${objectName}Dao.listAll();
    }

    @Override
    public PageResult query(${className}QueryObject qo) {
        return  ${objectName}Dao.query(qo);
    }
}
