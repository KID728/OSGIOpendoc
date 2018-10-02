package com.kid728.osgi.user.validator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.kid728.osgi.service.user.Validator;
import com.kid728.osgi.user.impl.LDAPValidatorImpl;

public class Activator implements BundleActivator {

    private ServiceRegistration<?> serviceReg = null;

    /*
     * (non-Javadoc)
     * 
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.
     * BundleContext)
     */
    public void start(BundleContext context) throws Exception {
        serviceReg = context.registerService(Validator.class.getName(), new LDAPValidatorImpl(), null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext context) throws Exception {
        if (serviceReg != null)
            serviceReg.unregister();
    }

}
