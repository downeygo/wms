package ${basePkg}.service;

import ${basePkg}.domain.${className};
import ${basePkg}.query.PageResult;
import ${basePkg}.query.${className}QueryObject;

import java.util.List;

public interface I${className}Service {
    /**
     * 保存
     *
     * @param ${objectName} 对象
     */
    void save(${className} ${objectName});

    /**
     * 保存
     *
     * @param ${objectName} 对象
     */
    void update(${className} ${objectName});

    /**
     * 删除
     *
     * @param id ID
     */
    void delete(Long id);

    /**
     * 查询
     *
     * @param id 对象ID
     * @return 对象
     */
    ${className} get(Long id);

    /**
     * 查询所有
     *
     * @return 对象集合
     */
    List<${className}> listAll();

    /**
     * 高级查询+分页查询
     * @param qo
     * @return
     */
    PageResult query(${className}QueryObject qo);
}
