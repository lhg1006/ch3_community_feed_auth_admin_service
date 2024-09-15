package org.fastcampus.community_feed.auth.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fastcampus.community_feed.auth.domain.UserAuth;
import org.fastcampus.community_feed.common.repository.TimeBaseEntity;

@Entity
@Table(name="community_user_auth")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserAuthEntity extends TimeBaseEntity {

    @Id
    private String email;
    private String password;
    private String role;
    private Long userId;
    private LocalDateTime lastLoginDt;

    public UserAuthEntity(UserAuth userAuth, Long userId) {
        this.email = userAuth.getEmail();
        this.password = userAuth.getPassword();
        this.role = userAuth.getRole();
        this.userId = userId;
    }

    public UserAuth toUserAuth() {
        return new UserAuth(userId, email, password, role);
    }

    public void updateLastLoginAt() {
        this.lastLoginDt = LocalDateTime.now();
    }
}
