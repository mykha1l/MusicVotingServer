package com.haw.mvsspring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.haw.mvsspring.authentication.SecurityConfig;
import com.haw.mvsspring.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("sqliteUser")
public class UserDataAccessService implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertUser(User user) {
        final String sqlUsers = "INSERT INTO users (username, password, enabled) VALUES(?, ?, ?)";
        jdbcTemplate.update(sqlUsers, new Object[] { user.getUsername(), user.getPassword(), user.isEnabled() });

        final String sqlAuthorities = "INSERT INTO authorities (username, authority) VALUES(?, ?)";
        jdbcTemplate.update(sqlAuthorities, new Object[] { user.getUsername(), user.getAuthority() });

        final String sqlDetails = "INSERT INTO user_details (username, nationality, genre, age) VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(sqlDetails,
                new Object[] { user.getUsername(), user.getNationality(), user.getGenre(), user.getAge() });
        return 0;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = this.jdbcTemplate.query(
                "SELECT * FROM users INNER JOIN authorities ON users.username = authorities.username",
                new RowMapper<User>() {
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        User user = new User(rs.getString("username"), rs.getString("password"), 0, null, null,
                                rs.getString("authority"), rs.getBoolean("enabled"));
                        return user;
                    }
                });
        return users;
    }

    @Override
    public int init() {
        final String sql = "CREATE TABLE IF NOT EXISTS users"
                + "(username varchar_ignorecase(50) not null primary key,password varchar_ignorecase(500) not null,enabled boolean not null)";
        jdbcTemplate.execute(sql);
        final String sqlAuth = "CREATE TABLE IF NOT EXISTS authorities"
                + "(username varchar_ignorecase(50) not null primary key,authority varchar_ignorecase(50) not null,constraint fk_authorities_users foreign key(username) references users(username))";
        jdbcTemplate.execute(sqlAuth);
        final String sqlDetails = "CREATE TABLE IF NOT EXISTS user_details"
                + "(username varchar_ignorecase(50) not null primary key,nationality varchar_ignorecase(50),genre varchar_ignorecase(50), age number, constraint fk_user_details foreign key(username) references users(username))";
        jdbcTemplate.execute(sqlDetails);
        final String sqlAdmin = "INSERT OR IGNORE INTO users ( username, password, enabled ) VALUES( ?, ?, ? )";
        jdbcTemplate.update(sqlAdmin,
                new Object[] { "admin", securityConfig.passwordEncoder().encode("admin"), 1});
        final String sqlAuthorities = "INSERT OR IGNORE INTO authorities ( username, authority) VALUES( ?, ? )";
        jdbcTemplate.update(sqlAuthorities, new Object[] { "admin", "ROLE_ADMIN" });
        return 0;
    }

    @Override
    public User getUser(final String name) {
        final String sql = "SELECT * FROM users WHERE username = ?";
        final User user = jdbcTemplate.queryForObject(sql, new Object[] { name }, (rs,
                rowNum) -> new User(rs.getString("username"), rs.getString("password"), rs.getBoolean("enabled")));
        final String sqlAuth = "SELECT authority FROM authorities WHERE username = ?";
        final String auth = jdbcTemplate.queryForObject(sqlAuth, new Object[] { name }, String.class);
        user.setAuthority(auth);
        return user;
    }

}
