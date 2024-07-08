package com.sample.webmvc.api.api.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {
  @Schema(description = "응답 결과 값", defaultValue = "SUCCESS", example = "SUCCESS", nullable = false)
  private Result result;
  private T data;
  @Schema(description = "응답 실패 시 오류 메시지", defaultValue = "OK", example = "OK", nullable = false)
  private String message;
  @Schema(description = "응답 실패 시 오류 코드", defaultValue = "0", example = "0", nullable = false)
  private int errorCode;

  public static <T> CommonResponse<T> success(T data, String message) {
    return (CommonResponse<T>) CommonResponse.builder()
        .result(Result.SUCCESS)
        .data(data)
        .message(message)
        .build();
  }

  public static <T> CommonResponse<T> success(T data) {
    return success(data, null);
  }

  public static CommonResponse fail(ErrorCode errorCode) {
    return CommonResponse.builder()
        .result(Result.FAIL)
        .message(errorCode.getErrorMsg())
        .errorCode(errorCode.getErrorCode())
        .build();
  }

  public static CommonResponse fail(int errorCode, String message) {
    return CommonResponse.builder()
        .result(Result.FAIL)
        .errorCode(errorCode)
        .message(message)
        .build();
  }

  public enum Result {
    SUCCESS, FAIL
  }
}