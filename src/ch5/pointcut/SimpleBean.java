package ch5.pointcut;

public class SimpleBean {
    public void foo() {
        System.out.println("foo");
    }

    public void foo(int x) {
        System.out.println("foo " + x);
    }

    public void bar() {
        System.out.println("bar");
    }
}
