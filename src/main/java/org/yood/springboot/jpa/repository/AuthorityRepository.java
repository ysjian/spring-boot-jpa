package org.yood.springboot.jpa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.yood.springboot.jpa.entity.Authority;

import java.sql.SQLException;
import java.util.List;

public interface AuthorityRepository extends CrudRepository<Authority, Authority> {

    @Query("select * from")
    List<Authority> findByUserName(String username) throws SQLException;
}
