package com.techthrivecatalyst.javajooq;

import com.techthrivecatalyst.javajooq.generated.tables.Employee;
import java.util.List;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository{

    private static final Employee E = Employee.EMPLOYEE;

    private final DSLContext dsl;


    public UserRepositoryImpl(DSLContext dsl) {
        this.dsl = dsl;
    }

    @Override
    public void insert(String name, String email) {
        dsl.insertInto(E)
                .set(E.NAME, name)
                .set(E.EMAIL, email)
                .execute();
    }

    public List<com.techthrivecatalyst.javajooq.generated.tables.pojos.Employee> findAll() {
        return dsl.select()
                .from(E)
                .fetchInto(com.techthrivecatalyst.javajooq.generated.tables.pojos.Employee.class);
    }

}
