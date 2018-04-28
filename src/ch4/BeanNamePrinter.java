package ch4;

import org.springframework.beans.factory.BeanNameAware;

public class BeanNamePrinter implements BeanNameAware {
    private String beanname;

    @Override
    public void setBeanName(String s) {
        this.beanname = s;
    }

    public void someOperation() {
        System.out.println("Bean [" + beanname + "] - someOperation()");
    }
}
