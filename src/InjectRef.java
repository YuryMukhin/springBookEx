import org.springframework.context.support.GenericXmlApplicationContext;

public class InjectRef {
    private Oracle oracle;

    public void setOracle(Oracle oracle) {
        this.oracle = oracle;
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/app-context-xml.xml");
        ctx.refresh();

        InjectRef iref = (InjectRef) ctx.getBean("injectRef");
        System.out.println(iref);
    }

    @Override
    public String toString() {
        return oracle.defineMeaningOfLife();
    }
}