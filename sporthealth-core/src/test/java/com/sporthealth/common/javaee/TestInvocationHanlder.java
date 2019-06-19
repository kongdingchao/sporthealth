package com.sporthealth.common.javaee;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: sporthealth
 * @description: TODO
 * @author: kongdingchao
 * @create: 2019-05-08 21:37
 **/

interface Person {
    public String getName();
    public void sayHello();
}

class PersonBean implements Person {
    public PersonBean(String name) {
        this.name = name;
    }

    String name;
    public String getName() {
        return name;
    }

    public void sayHello() {
        System.out.println(name + " say hello!");
    }
}

class PersonProxy implements Person {

    public PersonProxy(Person person) {
        this.person = person;
    }

    Person person;
    public String getName() {
        return person.getName();
    }

    public void sayHello() {
        person.sayHello();
    }
}

public class TestInvocationHanlder implements InvocationHandler {

    PersonBean personBean;

    public void setPersonBean(PersonBean personBean) {
        this.personBean = personBean;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (method.getName().startsWith("get")) {
            System.out.println("There someone like to get something...");
            return method.invoke(personBean, args);
        } else if (method.getName().startsWith("say")) {
            System.out.println("There someone like to say something...");
            return method.invoke(personBean, args);
        } else {
            System.out.println("erro handle...");
        }
        return null;
    }

    public static void main(String[] args) {
        TestInvocationHanlder handler = new TestInvocationHanlder();
        PersonBean personBean = new PersonBean("kdc");
        handler.setPersonBean(personBean);
        Person personProxy = (Person)Proxy.newProxyInstance(PersonBean.class.getClassLoader(),
                PersonBean.class.getInterfaces(), handler);

        personBean.getName();
        personBean.sayHello();
        personProxy.getName();
        personProxy.sayHello();
    }


}
