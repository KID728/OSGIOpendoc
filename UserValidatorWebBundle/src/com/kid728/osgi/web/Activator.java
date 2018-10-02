package com.kid728.osgi.web;

import javax.servlet.Servlet;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;

import com.kid728.osgi.servlet.LoginServlet;

public class Activator implements BundleActivator, ServiceListener {
    private BundleContext bc;

    private ServiceReference<?> ref;

    private Servlet servlet;

    // ------------------------------------------------Public Method

    /*
     * (non-Javadoc)
     * 
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.
     * BundleContext)
     */
    public void start(BundleContext context) throws Exception {
        bc = context;
        servlet = new LoginServlet(bc);
        registerServlet();
        context.addServiceListener(this, "(objectClass=" + HttpService.class.getName() + ")");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext context) throws Exception {
        try {
            unregisterServlet();
        } catch (Throwable t) {
            t.printStackTrace();
        }

        servlet = null;
        bc = null;
        ref = null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.osgi.framework.ServiceListener#serviceChanged(org.osgi.framework.
     * ServiceEvent)
     */
    public void serviceChanged(ServiceEvent event) {
        switch (event.getType()) {
        case ServiceEvent.REGISTERED:
            registerServlet();
            break;

        case ServiceEvent.UNREGISTERING:
            unregisterServlet();
            break;
        }
    }

    // ------------------------------------------------Private Method

    /*
     * 注册Web应用
     */
    private void registerServlet() {
        if (ref == null) {
            ref = bc.getServiceReference(HttpService.class.getName());
        }

        if (ref != null) {
            try {
                HttpService http = (HttpService) bc.getService(ref);
                http.registerServlet("/osgi/login", servlet, null, null);
                http.registerResources("/osgi/page", "page", null);
                System.out.println("已启动用户登录验证web模块，请通过/osgi/page/login.html访问");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * 卸载Web应用
     */
    private void unregisterServlet() {
        if (ref != null) {
            try {
                HttpService http = (HttpService) bc.getService(ref);
                http.unregister("/osgi/login");
                http.unregister("/osgi/page");
                System.out.println("已卸载用户登录验证web模块！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
