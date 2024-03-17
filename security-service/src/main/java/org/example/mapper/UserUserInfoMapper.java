package org.example.mapper;

import org.example.entity.User;
import org.example.security.UserInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserUserInfoMapper {
    User userInfoToUser(UserInfo userInfo);
    UserInfo userToUserInfo(User user);
}
