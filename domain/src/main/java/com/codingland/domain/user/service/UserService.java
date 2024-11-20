package com.codingland.domain.user.service;

import com.codingland.common.exception.user.UserErrorCode;
import com.codingland.common.exception.user.UserException;
import com.codingland.domain.user.dto.request.EditUserRequest;
import com.codingland.domain.user.dto.response.FindAllUserResponse;
import com.codingland.domain.user.dto.response.UserResponse;
import com.codingland.domain.user.entity.User;
import com.codingland.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /**
     * 유저 단 건 조회
     * @author 김원정
     * @param user_id 유저의 id
     * @throws UserException 유저를 찾지 못할 경우에 발생하는 예외
     */
    public UserResponse findByUserId(Long user_id) {
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new UserException(UserErrorCode.No_USER_INFO));
        return UserResponse.builder()
                .userId(user.getUserId())
                .subId(user.getSubId())
                .name(user.getName())
                .picture(user.getPicture())
                .email(user.getEmail())
                .build();
    }

    /**
     * 등록된 유저를 모두 조회하는 메서드.
     * @author 김원정
     */
   public FindAllUserResponse findAllUser() {
       List<User> users = userRepository.findAll();
       List<UserResponse> findAllUserResponseList = new ArrayList<>();
       for (User user : users) {
           findAllUserResponseList.add(
                   UserResponse.builder()
                           .userId(user.getUserId())
                           .subId(user.getSubId())
                           .name(user.getName())
                           .picture(user.getPicture())
                           .email(user.getEmail())
                           .build()
           );
       }
       return new FindAllUserResponse(findAllUserResponseList);
   }

    /**
     * 유저를 수정하는 메서드.
     * @author 김원정
     * @param editUserRequest 유저 수정 객체
     * @throws com.codingland.common.exception.user.UserException 유저를 찾지 못할 경우에 발생하는 예외
     */
    public void editUser(EditUserRequest editUserRequest) {
        User foundUser = userRepository.findById(editUserRequest.userId())
                .orElseThrow(() -> new UserException(UserErrorCode.No_USER_INFO));
        foundUser.editUser(editUserRequest);
        userRepository.save(foundUser);
    }

    /**
     * 활동 포인트가 증가하는 메서드
     * @author 김원정
     * @param user_id 유저의 id
     * @param increased_point 증가 포인트
     * @throws com.codingland.common.exception.user.UserException 유저를 찾지 못할 경우에 발생하는 예외
     */
    public void increaseActivityPoint(Long user_id, int increased_point) {
        User foundUser = userRepository.findById(user_id)
                .orElseThrow(() -> new UserException(UserErrorCode.No_USER_INFO));
        foundUser.increaseActivityPoint(increased_point);
        userRepository.save(foundUser);
    }

    /**
     * 활동 포인트가 감소하는 메서드
     * @author 김원정
     * @param user_id 유저의 id
     * @param decreased_point 감소 포인트
     * @throws com.codingland.common.exception.user.UserException 유저를 찾지 못할 경우에 발생하는 예외
     */
    public void decreaseActivityPoint(Long user_id, int decreased_point) {
        User foundUser = userRepository.findById(user_id)
                .orElseThrow(() -> new UserException(UserErrorCode.No_USER_INFO));
        foundUser.decreaseActivityPoint(decreased_point);
        userRepository.save(foundUser);
    }

    /**
     * 활동 포인트 조회하는 메서드
     * @author 김원정
     * @param user_id 유저의 id
     * @throws com.codingland.common.exception.user.UserException 유저를 찾지 못할 경우에 발생하는 예외
     */
    public int checkMyActivityPoint(Long user_id) {
        User foundUser = userRepository.findById(user_id)
                .orElseThrow(() -> new UserException(UserErrorCode.No_USER_INFO));
        return foundUser.getActivityPoint();
    }

    /**
     * 기초 트레이닝 완료 메서드
     * @author 김원정
     * @param user_id 유저의 id
     * @throws com.codingland.common.exception.user.UserException 유저를 찾지 못할 경우에 발생하는 예외
     */
    public void completeTraining(Long user_id) {
        User foundUser = userRepository.findById(user_id)
                .orElseThrow(() -> new UserException(UserErrorCode.No_USER_INFO));
        foundUser.completeBasicTraining();
        userRepository.save(foundUser);
    }

    /**
     * 기초 트레이닝 완료 여부 조회
     * @author 김원정
     * @param user_id 유저의 id
     * @throws com.codingland.common.exception.user.UserException 유저를 찾지 못할 경우에 발생하는 예외
     */
    public boolean checkIsCompleteTraining(Long user_id) {
        User foundUser = userRepository.findById(user_id)
                .orElseThrow(() -> new UserException(UserErrorCode.No_USER_INFO));
        return foundUser.isTrainingComplete();
    }

}
