package com.haw.mvsspring;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class APITests {

    @LocalServerPort
    private int port;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void getAllSongsReturns200() {
        RestAssured.given().get("/api/v1/songs").then().statusCode(200);
    }

    @Test
    public void getWrongParameterReturns400() {
        RestAssured.given().get("/api/v1/songs?anyKey=hhh").then().statusCode(400);
    }

    @Test
    public void getTooManyParameterReturns400() {
        RestAssured.given().get("/api/v1/songs?anyKey=hhh&secondKey=bb&thirdKey=cc").then().statusCode(400);
    }

    @Test
    public void getSongsFromWringDBnameReturns500() {

        final String sql = "ALTER TABLE song RENAME  TO songsss;"
                + "(username varchar_ignorecase(50) not null primary key,password varchar_ignorecase(500) not null,enabled boolean not null)";
        jdbcTemplate.execute(sql);
        RestAssured.given().get("/api/v1/songs").then().statusCode(500);

        final String sql2 = "ALTER TABLE songsss RENAME  TO song;"
                + "(username varchar_ignorecase(50) not null primary key,password varchar_ignorecase(500) not null,enabled boolean not null)";
        jdbcTemplate.execute(sql2);
    }

    @Test
    public void getEmptySearchReturns204() {
        RestAssured.given().get("/api/v1/songs?album=000").then().statusCode(204);
    }

    @Test
    public void getCurrentSongReturns200() {
        RestAssured.given().get("/api/v1/songs/current").then().statusCode(200);
    }

    @Test
    public void changeSongInfoWithWrongParameterReturns400() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("wrongparameter", "123");
        RestAssured.given().header("Content-Type", "application/json").body(requestParams.toJSONString())
                .put("/api/v1/songs/1").then().statusCode(400);
    }

}
