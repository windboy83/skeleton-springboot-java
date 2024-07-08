package com.sample.webmvc.api.api.controller;

import com.sample.webmvc.api.api.common.response.CommonResponse;
import com.sample.webmvc.api.api.common.response.CommonResponseError;
import com.sample.webmvc.api.api.dto.response.MemberRes;
import com.sample.webmvc.api.api.dto.response.TeamRes;
import com.sample.webmvc.api.api.facade.SampleFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SampleController {
    private final SampleFacade sampleFacade;

    @GetMapping("/v1/teams")
    @Operation(summary = "목록조회 샘플", description = "CommonResponse 반환타입")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = CommonResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = CommonResponseError.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = CommonResponseError.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = CommonResponseError.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = CommonResponseError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Eerror", content = @Content(schema = @Schema(implementation = CommonResponseError.class)))
    })
    public CommonResponse<List<TeamRes>> getTeams() {
        return CommonResponse.success(sampleFacade.getTeamList());
    }

    @GetMapping("/v1/members")
    @Operation(summary = "목록조회 샘플", description = "CommonResponse 반환타입")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = CommonResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = CommonResponseError.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = CommonResponseError.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = CommonResponseError.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = CommonResponseError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Eerror", content = @Content(schema = @Schema(implementation = CommonResponseError.class)))
    })
    public CommonResponse<List<MemberRes>> getMembers() {
        return CommonResponse.success(sampleFacade.getMembers());
    }
}
