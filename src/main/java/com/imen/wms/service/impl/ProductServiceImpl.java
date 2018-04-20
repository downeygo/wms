package  com.imen.wms.service.impl;

import  com.imen.wms.dao.impl. ProductDAOImpl;
import  com.imen.wms.domain.Product;
import  com.imen.wms.query.PageResult;
import  com.imen.wms.query.ProductQueryObject;
import  com.imen.wms.service.IProductService;
import lombok.Setter;

import java.util.List;

public class  ProductServiceImpl implements IProductService {
    @Setter
    private  ProductDAOImpl  productDao;
    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    @Override
    public void delete(Long id) {
        productDao.delete(id);
    }

    @Override
    public  Product get(Long id) {
        return  productDao.get(id);
    }

    @Override
    public List<Product> listAll() {
        return  productDao.listAll();
    }

    @Override
    public PageResult query(ProductQueryObject qo) {
        return  productDao.query(qo);
    }
}
