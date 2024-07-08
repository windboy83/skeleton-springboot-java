package com.sample.webflux.api.controller;



import com.sample.webflux.api.common.response.CommonResponse;
import com.sample.webflux.api.common.response.CommonResponseError;
import com.sample.webflux.api.dto.request.TeamReq;
import com.sample.webflux.api.dto.response.TeamRes;
import com.sample.webflux.api.facade.SampleFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SampleController {

    private final SampleFacade sampleFacade;

    @GetMapping("/v1/sample/teams")
    @Operation(summary = "목록조회 샘플", description = "Flux<> 반환타입")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = CommonResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = CommonResponseError.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = CommonResponseError.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = CommonResponseError.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = CommonResponseError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Eerror", content = @Content(schema = @Schema(implementation = CommonResponseError.class)))
    })
    public Flux<TeamRes> getAllTeams(TeamReq teamReq) {
        return sampleFacade.getAllTeams(teamReq);
    }

    @GetMapping("/v2/sample/teams")
    @Operation(summary = "목록조회 샘플", description = "CommonResponse 반환타입")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = CommonResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = CommonResponseError.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = CommonResponseError.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = CommonResponseError.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = CommonResponseError.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Eerror", content = @Content(schema = @Schema(implementation = CommonResponseError.class)))
    })
    public Mono<CommonResponse<List<TeamRes>>> getAllTeamsV2(TeamReq teamReq) {
        return sampleFacade.getAllTeams(teamReq)
                .collectList()
                .map(a -> CommonResponse.success(a));
    }
}
