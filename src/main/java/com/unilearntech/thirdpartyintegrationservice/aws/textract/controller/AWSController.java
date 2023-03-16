package com.unilearntech.thirdpartyintegrationservice.aws.textract.controller;

import com.unilearntech.thirdpartyintegrationservice.aws.textract.object.communicator.GetTextFromImageRequest;
import com.unilearntech.thirdpartyintegrationservice.aws.textract.object.communicator.GetTextFromImageResponse;
import com.unilearntech.thirdpartyintegrationservice.aws.textract.service.IAWSService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.textract.model.DetectDocumentTextResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/aws")
public class AWSController {
    private final IAWSService awsService;
    @RequestMapping(method = RequestMethod.POST,value = "/getTextFromImage")
    public GetTextFromImageResponse getTextFromImage(@RequestBody GetTextFromImageRequest request){
        String text = awsService.getTextFromImage(request);
        return GetTextFromImageResponse.builder().text(text).build();
    }
}
