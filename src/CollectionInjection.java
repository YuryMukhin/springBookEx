import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class CollectionInjection {
    private Map<String, Object> map;
    private Properties props;
    private Set set;
    private List list;

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/app-context-xml.xml");
        ctx.refresh();

        CollectionInjection inst = (CollectionInjection) ctx.getBean("injectCollection");

        inst.displayInfo();
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public Properties getProps() {
        return props;
    }

    public void setProps(Properties props) {
        this.props = props;
    }

    public Set getSet() {
        return set;
    }

    public void setSet(Set set) {
        this.set = set;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public void displayInfo() {
        System.out.println("map");
        for (Map.Entry<String, Object> entry: map.entrySet()) {
            System.out.println("K: " + entry.getKey() + "V: " + entry.getValue());
        }

        System.out.println("props");
        for (Map.Entry<Object, Object> entry: props.entrySet()) {
            System.out.println("K: " + entry.getKey() + "V: " + entry.getValue());
        }

        System.out.println("set");
        for (Object ob: set) {
            System.out.println("V: " + ob);
        }

        System.out.println("list");
        for (Object ob: list) {
            System.out.println("V: " + ob);
        }
    }
}
