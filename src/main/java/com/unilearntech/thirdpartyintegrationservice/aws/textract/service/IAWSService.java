package com.unilearntech.thirdpartyintegrationservice.aws.textract.service;

import com.unilearntech.thirdpartyintegrationservice.aws.textract.object.communicator.GetTextFromImageRequest;
import com.unilearntech.thirdpartyintegrationservice.aws.textract.object.communicator.GetTextFromImageResponse;
import software.amazon.awssdk.services.textract.model.DetectDocumentTextResponse;

public interface IAWSService {
    DetectDocumentTextResponse getTextFromImage(GetTextFromImageRequest request);
}
