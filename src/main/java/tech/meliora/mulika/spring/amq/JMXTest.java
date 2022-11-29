package tech.meliora.mulika.spring.amq;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.net.MalformedURLException;
import java.util.Set;

public class JMXTest {

    public static void main(String[] args) throws Exception {

        String urlStr = "service:jmx:rmi:///jndi/rmi://206.225.81.36:5400/jmxrmi";
        JMXServiceURL url = new JMXServiceURL(urlStr);
        JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
        MBeanServerConnection mbeanServerConn = jmxc.getMBeanServerConnection();

        System.out.println("connected: " + urlStr);


        Set<ObjectName> beanSet = mbeanServerConn.queryNames(null, null);


        for (ObjectName objectName : beanSet) {
            System.out.println("objectName: " + objectName);
        }

        ObjectName objectName = new ObjectName("com.meliora.ucm.jmx:type=SystemCounters");

        Object object = mbeanServerConn.getAttribute(objectName, "CMGMessageProduced");

        System.out.println("object: " + object);

    }
}
