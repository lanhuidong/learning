package com.nexusy.tomcat.digester;

import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * @author lanhuidong
 * @since 2016-02-28
 */
public class Test {

    public static void main(String[] args) throws IOException, SAXException {
        Digester digester = new Digester();
        digester.addObjectCreate("employee", Employee.class);
        digester.addSetProperties("employee");
        digester.addCallMethod("employee", "toString");
        digester.addObjectCreate("employee/office", Office.class);
        digester.addSetProperties("employee/office");
        digester.addSetNext("employee/office", "addOffice");
        digester.addObjectCreate("employee/office/address", Address.class);
        digester.addSetProperties("employee/office/address");
        digester.addSetNext("employee/office/address", "setAddress");
        Employee employee = (Employee) digester.parse(Test.class.getClassLoader().getResourceAsStream("Employee.xml"));
        System.out.println(employee);
        for (Office office : employee.getOffices()) {
            System.out.println(office.getAddress());
        }

    }
}
