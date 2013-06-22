package com.rollingstone.customer.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rollingstone.customer.model.Product;
import com.rollingstone.customer.utils.HibernateUtil;

@Repository
public class ProductHibernateDaoImpl implements IProductDao {

	@Autowired
	HibernateUtil hbUtil;
	
	Logger logger = Logger.getLogger(ProductHibernateDaoImpl.class);
	
	public Product addProduct(Product product) throws Exception {
		SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();

        try {
        	
        	session.save(product);

        	session.getTransaction().commit();
        }catch(Exception e){
        	session.getTransaction().rollback();
        	logger.error(e.getMessage());
        	throw e;
        }finally{
            session.close();
        }
        
		return null;
	}

	public List<Product> getAllProducts() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();

        Criteria c = session.createCriteria(Product.class);
        List<Product> productList = c.list();

        session.close();

        return productList;
	}

	public boolean removeProduct(long productId) throws Exception {
		SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();

        try{
    		Product product = (Product) session.get(Product.class, productId);
    		session.delete(product);
    		session.getTransaction().commit();
        }catch(Exception e){
        	session.getTransaction().rollback();
        	logger.error(e.getMessage());
        	throw e;
        }finally{
            session.close();
        }

        return true;
	}

	public boolean updateProduct(Product product) throws Exception {
		SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();

        try {
        	session.update(product);

        	session.getTransaction().commit();
        }catch(Exception e){
        	session.getTransaction().rollback();
        	logger.error(e.getMessage());
        	throw e;
        }finally{
            session.close();
        }
        
		return true;
	}
}
