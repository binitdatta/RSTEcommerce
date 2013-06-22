package com.rollingstone.customer.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.sf.ehcache.search.Attribute;
import net.sf.ehcache.search.Direction;
import net.sf.ehcache.search.Query;
import net.sf.ehcache.search.Result;
import net.sf.ehcache.search.Results;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.googlecode.ehcache.annotations.TriggersRemove;
import com.rollingstone.customer.model.Contact;
import com.rollingstone.customer.model.Customer;
import com.rollingstone.customer.model.ResponseObject;

@Repository
public class CustomerHibernateDaoImpl extends AbstractDAO implements ICustomerDao {

	Logger logger = Logger.getLogger(CustomerHibernateDaoImpl.class);

	public ResponseObject<Customer> getAllCustomers(int pageNum, int pageSize) {
		List<Customer> customerList = new ArrayList<Customer>();
        Cache cache = cacheManager.getCache("getAllCustomer");
        if (cache.getSize() == 0){
        	logger.debug("Data coming from DB.");
        	initCustomerCache(cache);
        } else {
        	logger.debug("Data coming from cache.");
        }
		
        Query query = cache.createQuery();
		query.includeKeys();
		query.includeValues();
		Attribute<String> customerName = cache.getSearchAttribute("customerName");
		query.addOrderBy(customerName, Direction.ASCENDING);
		
		Results results = query.execute();
		
		int start = pageNum * pageSize;		
		List<Result> page = results.range(start, pageSize);
		for (Result r : page){
			customerList.add((Customer) r.getValue());
		}
		
		ResponseObject<Customer> resObj = new ResponseObject<Customer>();
		resObj.setTotalItems(results.size());
		resObj.setListOfModels(customerList);
        return resObj ;
	}
	
	public void initCustomerCache(Cache cache){
		SessionFactory sf = hbUtil.getSessionFactory();
        Session session = sf.openSession();

        Criteria c = session.createCriteria(Customer.class);
        List<Customer> customerList = c.list();
        session.close();
    
        int count = 0;
        for (Customer customer : customerList){
        	cache.put(new Element(count, customer));
        	count ++;
        }	
	}
	
	public ResponseObject<Customer> getSearchCustomers(int pageNum, int pageSize, String customerName, String houseNumber, String street) {
		Cache cache = cacheManager.getCache("getAllCustomer");
        if (cache.getSize() == 0){
        	logger.debug("Data coming from DB.");
        	initCustomerCache(cache);
        } else {
        	logger.debug("Data coming from cache.");
        }

		List<Customer> customerList = new ArrayList<Customer>();
		
		Attribute<String> attrName = cache.getSearchAttribute("customerName");
		Attribute<String> attrHouse = cache.getSearchAttribute("houseNumber");
		Attribute<String> attrStreet = cache.getSearchAttribute("street");
		
		Query query = cache.createQuery();
		query.includeKeys();
		query.includeValues();
		query.addOrderBy(attrName, Direction.ASCENDING);
		
		if (!customerName.isEmpty()){
			query.addCriteria(attrName.ilike("*"+customerName+"*"));	
		}
		
		if (!houseNumber.isEmpty()){
			query.addCriteria(attrHouse.ilike("*"+houseNumber+"*"));
		}
		
		if (!street.isEmpty()){
			query.addCriteria(attrStreet.ilike("*"+street+"*"));
		}
		
		Results results = query.execute();
		
		int start = pageNum * pageSize;		
		List<Result> page = results.range(start, pageSize);
		for (Result r : page){
			customerList.add((Customer) r.getValue());
		}
		
		ResponseObject<Customer> resObj = new ResponseObject<Customer>();
		resObj.setTotalItems(results.size());
		resObj.setListOfModels(customerList);
        return resObj ;
	}
	
	@TriggersRemove(cacheName={"getAllCustomer"}, removeAll=true)
	public Customer addCustomer(Customer customer) throws Exception {
		SessionFactory sf = hbUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();

        try {
        	List<Contact> contacts = (List<Contact>) customer.getContacts();

        	customer.getDefaultCard().setCustomer(customer);
        	for (Iterator<Contact> contactItr = contacts.iterator(); contactItr.hasNext(); ){
        		Contact contact = contactItr.next();
        		contact.setCustomer(customer);
        	}
        	
        	session.save(customer);

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
	
	@TriggersRemove(cacheName={"getAllCustomer"}, removeAll=true)
	public boolean removeCustomer(int customerId) throws Exception {
		SessionFactory sf = hbUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();

        try{
    		Customer customer = (Customer) session.get(Customer.class, customerId);
    		session.delete(customer);
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

	@TriggersRemove(cacheName={"getAllCustomer"}, removeAll=true)
	public boolean updateCustomer(Customer customer) throws Exception {
		SessionFactory sf = hbUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();

        try {
        	List<Contact> contacts = (List<Contact>) customer.getContacts();

        	customer.getDefaultCard().setCustomer(customer);
        	for (Iterator<Contact> contactItr = contacts.iterator(); contactItr.hasNext(); ){
        		Contact contact = contactItr.next();
        		contact.setCustomer(customer);
        	}
        	
        	session.update(customer);

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
