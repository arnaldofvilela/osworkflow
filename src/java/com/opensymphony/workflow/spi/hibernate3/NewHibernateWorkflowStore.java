/*
 * Copyright (c) 2002-2003 by OpenSymphony
 * All rights reserved.
 */
package com.opensymphony.workflow.spi.hibernate3;

import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.module.propertyset.PropertySetManager;
import com.opensymphony.module.propertyset.hibernate.DefaultHibernateConfigurationProvider;

import com.opensymphony.workflow.StoreException;

import net.sf.hibernate.SessionFactory;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.HashMap;
import java.util.Map;


/**
 * @author masini
 *
 * New Refactored Hibernate Store.
 * Look at @link NewHibernateFunctionalWorkflowTestCase for a use case.
 */
public class NewHibernateWorkflowStore extends AbstractHibernateWorkflowStore {
    //~ Instance fields ////////////////////////////////////////////////////////

    Session session;
    SessionFactory sessionFactory;

    //~ Constructors ///////////////////////////////////////////////////////////

    public NewHibernateWorkflowStore() {
        super();
    }

    //~ Methods ////////////////////////////////////////////////////////////////

    public PropertySet getPropertySet(long entryId) throws StoreException {
        HashMap args = new HashMap();
        args.put("entityName", "OSWorkflowEntry");
        args.put("entityId", new Long(entryId));

        DefaultHibernateConfigurationProvider configurationProvider = new DefaultHibernateConfigurationProvider();
        configurationProvider.setSessionFactory(sessionFactory);

        args.put("configurationProvider", configurationProvider);

        return PropertySetManager.getInstance("hibernate", args);
    }

    // Now session management is delegated to user
    public void init(Map props) throws StoreException {
        sessionFactory = (SessionFactory) props.get("sessionFactory");
        session = (Session) props.get("session");
    }

    protected Object execute(InternalCallback action) throws StoreException {
        try {
            return action.doInHibernate(session);
        } catch (HibernateException e) {
            throw new StoreException(e);
        }
    }
}