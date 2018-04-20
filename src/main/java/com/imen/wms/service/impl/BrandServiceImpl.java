package  com.imen.wms.service.impl;

import  com.imen.wms.dao.impl. BrandDAOImpl;
import  com.imen.wms.domain.Brand;
import  com.imen.wms.query.PageResult;
import  com.imen.wms.query.BrandQueryObject;
import  com.imen.wms.service.IBrandService;
import lombok.Setter;

import java.util.List;

public class  BrandServiceImpl implements IBrandService {
    @Setter
    private  BrandDAOImpl  brandDao;
    @Override
    public void save(Brand brand) {
        brandDao.save(brand);
    }

    @Override
    public void update(Brand brand) {
        brandDao.update(brand);
    }

    @Override
    public void delete(Long id) {
        brandDao.delete(id);
    }

    @Override
    public  Brand get(Long id) {
        return  brandDao.get(id);
    }

    @Override
    public List<Brand> listAll() {
        return  brandDao.listAll();
    }

    @Override
    public PageResult query(BrandQueryObject qo) {
        return  brandDao.query(qo);
    }
}
