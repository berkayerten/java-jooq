package com.techthrivecatalyst.javajooq;

import com.techthrivecatalyst.javajooq.generated.tables.pojos.AppUser;
import java.util.List;

public interface UserRepository {

    void insert(String name, String email);

    List<AppUser> findAll();
}
