package  com.imen.wms.service.impl;

import  com.imen.wms.dao.impl. SupplierDAOImpl;
import  com.imen.wms.domain.Supplier;
import  com.imen.wms.query.PageResult;
import  com.imen.wms.query.SupplierQueryObject;
import  com.imen.wms.service.ISupplierService;
import lombok.Setter;

import java.util.List;

public class  SupplierServiceImpl implements ISupplierService {
    @Setter
    private  SupplierDAOImpl  supplierDao;
    @Override
    public void save(Supplier supplier) {
        supplierDao.save(supplier);
    }

    @Override
    public void update(Supplier supplier) {
        supplierDao.update(supplier);
    }

    @Override
    public void delete(Long id) {
        supplierDao.delete(id);
    }

    @Override
    public  Supplier get(Long id) {
        return  supplierDao.get(id);
    }

    @Override
    public List<Supplier> listAll() {
        return  supplierDao.listAll();
    }

    @Override
    public PageResult query(SupplierQueryObject qo) {
        return  supplierDao.query(qo);
    }
}
