package org.fastcampus.community_feed.acceptance.post;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.fastcampus.community_feed.user.application.dto.FollowUserRequestDto;
import org.springframework.http.MediaType;

public class UserAcceptanceSteps {
    public static ExtractableResponse<Response> followUser(FollowUserRequestDto dto) {
        return RestAssured
                .given()
                .body(dto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/relation/follow")
                .then()
                .extract();
    }
}
