package com.sample.webmvc.api.api.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

  //공통 30001~30100
  COMMON_EXCEPTION_ERROR(30001,"일시적인 오류가 발생했습니다. 잠시 후 다시 시도해주세요."),
  COMMON_NOT_FOUND_ERROR(30002,"리소스를 찾을 수 없습니다."),
  COMMON_BAD_REQUEST_ERROR(30003,"요청 정보가 잘 못 되었습니다."),
  COMMON_UNAUTHORIZED_ERROR(30004,"인가되지 않은 요청입니다."),
  COMMON_PARAMETER_BIND_ERROR(30005,"필수 파라미터 정보가 없습니다.")
  ;

  private final int errorCode;
  private final String errorMsg;

  public int getErrorCode() {
    return errorCode;
  }

  public String getErrorMsg(Object... arg) {
    return String.format(errorMsg, arg);
  }
}
