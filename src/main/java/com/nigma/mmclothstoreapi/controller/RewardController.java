package com.nigma.mmclothstoreapi.controller;

import com.nigma.mmclothstoreapi.constant.Route;
import com.nigma.mmclothstoreapi.model.dto.response.CommmonResponse;
import com.nigma.mmclothstoreapi.model.dto.response.RewardResponse;
import com.nigma.mmclothstoreapi.model.entity.Reward;
import com.nigma.mmclothstoreapi.model.mapper.RewardMapper;
import com.nigma.mmclothstoreapi.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(Route.REWARD)
public class RewardController {
    private final RewardService rewardService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<RewardResponse> responses = rewardService.getAll();
        CommmonResponse<List<RewardResponse>> commmonResponse = CommmonResponse.<List<RewardResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get all reward data")
                .data(responses)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commmonResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        RewardResponse response = RewardMapper.toDto(rewardService.getById(id));
        CommmonResponse<RewardResponse> commmonResponse = CommmonResponse.<RewardResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get reward data")
                .data(response)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commmonResponse);
    }
}
