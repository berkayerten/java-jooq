package com.techthrivecatalyst.javajooq;

import com.techthrivecatalyst.javajooq.generated.tables.AppUser;
import java.util.List;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository{

    private static final AppUser AU = AppUser.APP_USER;

    private final DSLContext dsl;


    public UserRepositoryImpl(DSLContext dsl) {
        this.dsl = dsl;
    }

    public void insert(String name, String email) {
        dsl.insertInto(AU)
                .set(AU.NAME, name)
                .set(AU.EMAIL, email)
                .execute();
    }

    public List<com.techthrivecatalyst.javajooq.generated.tables.pojos.AppUser> findAll() {
        return dsl.select()
                .from(AU)
                .fetchInto(com.techthrivecatalyst.javajooq.generated.tables.pojos.AppUser.class);
    }

}
