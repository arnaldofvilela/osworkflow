/*
 * Copyright (c) 2002-2003 by OpenSymphony
 * All rights reserved.
 */
package com.opensymphony.workflow.loader;

import com.opensymphony.workflow.FactoryException;

import java.util.Properties;


/**
 * @author Hani Suleiman
 * Date: May 10, 2002
 * Time: 11:17:06 AM
 */
public abstract class AbstractWorkflowFactory {
    //~ Instance fields ////////////////////////////////////////////////////////

    protected Properties properties = new Properties();

    //~ Methods ////////////////////////////////////////////////////////////////

    public Properties getProperties() {
        return properties;
    }

    public final void init(Properties p) throws FactoryException {
        this.properties = p;
    }

    public abstract WorkflowDescriptor getWorkflow(String name) throws FactoryException;

    public abstract String[] getWorkflowNames() throws FactoryException;

    /**
     * Save the workflow.
     * @param name The name of the workflow to same.
     * @param descriptor The descriptor for the workflow.
     * @param replace true if an existing workflow with this name should be replaced.
     * @return true if the workflow was saved.
     * @throws FactoryException if there was an error saving the workflow
     * @throws com.opensymphony.workflow.InvalidWorkflowDescriptorException if the descriptor specified is invalid
     */
    public abstract boolean saveWorkflow(String name, WorkflowDescriptor descriptor, boolean replace) throws FactoryException;

    public void initDone() throws FactoryException {
    }
}
