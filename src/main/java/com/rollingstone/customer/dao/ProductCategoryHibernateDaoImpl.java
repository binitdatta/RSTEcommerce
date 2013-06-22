package com.rollingstone.customer.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rollingstone.customer.model.ProductCategory;
import com.rollingstone.customer.utils.HibernateUtil;

@Repository
public class ProductCategoryHibernateDaoImpl implements IProductCategoryDao {

	@Autowired
	HibernateUtil hbUtil;

	Logger logger = Logger.getLogger(ProductCategoryHibernateDaoImpl.class);

	public ProductCategory addProductCategory(ProductCategory category)
			throws Exception {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();

		try {

			session.save(category);

			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error(e.getMessage());
			throw e;
		} finally {
			session.close();
		}

		return null;
	}

	public List<ProductCategory> getAllProductCategories() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		Criteria c = session.createCriteria(ProductCategory.class);
		List<ProductCategory> productCategoryList = c.list();

		session.close();

		return productCategoryList;
	}

	public boolean removeProductCategory(long productCategoryId)
			throws Exception {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();

		try {
			ProductCategory productCategory = (ProductCategory) session.get(
					ProductCategory.class, productCategoryId);
			session.delete(productCategory);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error(e.getMessage());
			throw e;
		} finally {
			session.close();
		}

		return true;

	}

	public boolean updateProductCategory(ProductCategory productCategory)
			throws Exception {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();

		try {
			session.update(productCategory);

			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error(e.getMessage());
			throw e;
		} finally {
			session.close();
		}

		return true;

	}
}
