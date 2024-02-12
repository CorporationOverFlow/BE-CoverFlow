package com.coverflow.question.presentation;

import com.coverflow.global.annotation.AdminAuthorize;
import com.coverflow.global.annotation.MemberAuthorize;
import com.coverflow.global.handler.ResponseHandler;
import com.coverflow.question.application.AnswerService;
import com.coverflow.question.dto.request.SaveAnswerRequest;
import com.coverflow.question.dto.request.UpdateAnswerRequest;
import com.coverflow.question.dto.response.FindAnswerResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/answer")
@RestController
public class AnswerController {

    private final AnswerService answerService;

    @GetMapping("/find-answer")
    @MemberAuthorize
    public ResponseEntity<ResponseHandler<List<FindAnswerResponse>>> findAnswer(
            @RequestParam("id") @Valid final Long id
    ) {
        return ResponseEntity.ok()
                .body(ResponseHandler.<List<FindAnswerResponse>>builder()
                        .statusCode(HttpStatus.OK)
                        .message("특정 질문에 대한 전체 답변 조회에 성공했습니다.")
                        .data(answerService.findAnswer(id))
                        .build()
                );
    }

    @GetMapping("/admin/find-answer")
    @AdminAuthorize
    public ResponseEntity<ResponseHandler<FindAnswerResponse>> findAnswerById(
            @RequestParam("id") @Valid final Long id
    ) {
        return ResponseEntity.ok()
                .body(ResponseHandler.<FindAnswerResponse>builder()
                        .statusCode(HttpStatus.OK)
                        .message("특정 답변 조회에 성공했습니다.")
                        .data(answerService.findById(id))
                        .build()
                );
    }

    @PostMapping("/save-answer")
    @MemberAuthorize
    public ResponseEntity<ResponseHandler<Void>> saveAnswer(
            @RequestBody @Valid final SaveAnswerRequest saveAnswerRequest,
            @AuthenticationPrincipal final UserDetails userDetails
    ) {
        answerService.saveAnswer(saveAnswerRequest, userDetails.getUsername());
        return ResponseEntity.ok()
                .body(ResponseHandler.<Void>builder()
                        .statusCode(HttpStatus.OK)
                        .message("답변 등록에 성공했습니다.")
                        .build());
    }

    @PostMapping("/admin/update-answer")
    @AdminAuthorize
    public ResponseEntity<ResponseHandler<Void>> updateAnswer(
            @RequestBody @Valid final UpdateAnswerRequest updateAnswerRequest
    ) {
        answerService.updateAnswer(updateAnswerRequest);
        return ResponseEntity.ok()
                .body(ResponseHandler.<Void>builder()
                        .statusCode(HttpStatus.OK)
                        .message("답변 수정에 성공했습니다.")
                        .build());
    }

    @PostMapping("/admin/delete-answer/{answerId}")
    @AdminAuthorize
    public ResponseEntity<ResponseHandler<Void>> deleteAnswer(
            @RequestBody @Valid final Long answerId
    ) {
        answerService.deleteAnswer(answerId);
        return ResponseEntity.ok()
                .body(ResponseHandler.<Void>builder()
                        .statusCode(HttpStatus.OK)
                        .message("답변 삭제에 성공했습니다.")
                        .build());
    }
}
