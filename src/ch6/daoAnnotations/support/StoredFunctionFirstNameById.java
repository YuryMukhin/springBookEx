package ch6.daoAnnotations.support;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlFunction;

import javax.sql.DataSource;
import java.sql.Types;

public class StoredFunctionFirstNameById extends SqlFunction<String> {
    private static final String SQL_FUNC = "select getFirstNameById(?)";

    public StoredFunctionFirstNameById(DataSource ds) {
        super(ds, SQL_FUNC);
        declareParameter(new SqlParameter(Types.INTEGER));
        compile();
    }
}
