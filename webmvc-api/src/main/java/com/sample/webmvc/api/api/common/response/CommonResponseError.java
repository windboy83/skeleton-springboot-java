package com.sample.webmvc.api.api.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CommonResponseError {
  @Schema(description = "응답 결과 값", defaultValue = "FAIL", example = "FAIL", nullable = false)
  private CommonResponse.Result result;
  @Schema(description = "응답 실패 시 오류 메시지", defaultValue = "오류가 발생하였습니다.", example = "오류가 발생하였습니다.", nullable = false)
  private String message;
  @Schema(description = "응답 실패 시 오류 코드", defaultValue = "9999", example = "9999", nullable = false)
  private int errorCode;
}
