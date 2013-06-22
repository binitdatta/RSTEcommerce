package com.rollingstone.customer.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rollingstone.customer.model.Vendor;
import com.rollingstone.customer.utils.HibernateUtil;

@Repository
public class VendorHibernateDaoImpl implements IVendorDao {

	@Autowired
	HibernateUtil hbUtil;
	
	Logger logger = Logger.getLogger(VendorHibernateDaoImpl.class);
	
	public Vendor addVendor(Vendor vendor) throws Exception {
		SessionFactory sf = hbUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();

        try {
        	
        	
        	session.save(vendor);

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

	public List<Vendor> getAllVendors() {
		SessionFactory sf = hbUtil.getSessionFactory();
        Session session = sf.openSession();

        Criteria c = session.createCriteria(Vendor.class);
        List<Vendor> vendorList = c.list();

        session.close();

        return vendorList;
	}

	

	public boolean updateVendor(Vendor vendor) throws Exception {
		SessionFactory sf = hbUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();

        try {
        
        	session.update(vendor);

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

	@Override
	public boolean removeVendor(long vendorId) throws Exception {
		SessionFactory sf = hbUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();

        try{
        	Vendor vendor = (Vendor) session.get(Vendor.class, vendorId);
    		session.delete(vendor);
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
