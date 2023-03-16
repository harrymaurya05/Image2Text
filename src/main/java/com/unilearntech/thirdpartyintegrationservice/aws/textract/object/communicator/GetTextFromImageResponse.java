package com.unilearntech.thirdpartyintegrationservice.aws.textract.object.communicator;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import software.amazon.awssdk.services.textract.model.DetectDocumentTextResponse;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetTextFromImageResponse {
    private String text;
}
