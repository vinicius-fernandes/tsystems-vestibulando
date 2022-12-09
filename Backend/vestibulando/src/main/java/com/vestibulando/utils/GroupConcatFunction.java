package com.vestibulando.utils;

import org.hibernate.QueryException;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import java.util.List;

public class GroupConcatFunction extends StandardSQLFunction {

    public static GroupConcatFunction INSTANCE = new GroupConcatFunction();

    public GroupConcatFunction() {
        super("GROUP_CONCAT", StandardBasicTypes.STRING);
    }

    @Override
    public String render(Type firstArgumentType, List arguments, SessionFactoryImplementor factory) throws QueryException {
        return render(arguments);
    }

    @SuppressWarnings("UnusedParameters")
    protected String render(List<Object> arguments) {
        String column;
        String separator = null;
        Boolean distinct = Boolean.TRUE;
        String orderBy = null;

        if (arguments.size() > 0) {
            column = arguments.get(0).toString();
        } else {
            throw new IllegalArgumentException("GROUP_CONCAT should have at least one Column Name parameter!");
        }

        if (arguments.size() > 1) {
            distinct = Boolean.valueOf(arguments.get(1).toString());
        }

        if (arguments.size() > 2) {
            separator = arguments.get(2).toString();
        }

        if (arguments.size() > 4) {
            orderBy = String.format("%s %s", arguments.get(3).toString(), arguments.get(4).toString().replace("'", ""));
        }
        return render(column, separator, Boolean.TRUE, orderBy);
    }

    protected String render(String column, String separator, Boolean distinct, String orderBy) {
        StringBuilder groupConcatFunction = new StringBuilder();
        groupConcatFunction.append("GROUP_CONCAT(");
        if (distinct) {
            groupConcatFunction.append("DISTINCT");
        }
        groupConcatFunction.append(" ").append(column);
        if (orderBy != null) {
            groupConcatFunction.append(" ORDER BY ").append(orderBy);
        }
        if (separator != null) {
            groupConcatFunction.append(" SEPARATOR ").append(separator);
        }
        groupConcatFunction.append(" )");
        return groupConcatFunction.toString();
    }
}