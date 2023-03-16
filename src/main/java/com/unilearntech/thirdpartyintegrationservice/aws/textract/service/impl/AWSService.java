package com.unilearntech.thirdpartyintegrationservice.aws.textract.service.impl;

import com.unilearntech.thirdpartyintegrationservice.aws.textract.object.communicator.GetTextFromImageRequest;
import com.unilearntech.thirdpartyintegrationservice.aws.textract.service.IAWSService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.textract.TextractClient;
import software.amazon.awssdk.services.textract.model.Block;
import software.amazon.awssdk.services.textract.model.DetectDocumentTextRequest;
import software.amazon.awssdk.services.textract.model.DetectDocumentTextResponse;
import software.amazon.awssdk.services.textract.model.Document;
import software.amazon.awssdk.services.textract.model.TextractException;
import software.amazon.awssdk.utils.StringUtils;

@Service
@RequiredArgsConstructor
public class AWSService implements IAWSService {
    @Override public String getTextFromImage(GetTextFromImageRequest request) {
        String text = detectTextFromImage(request.getUrl());
        return text;
    }
    public static String detectTextFromImage(String sourceDoc) {
        Region region = Region.US_WEST_2;
        TextractClient textractClient = TextractClient.builder().region(region)
                .credentialsProvider(ProfileCredentialsProvider.create()).build();
        try {

            InputStream sourceStream = new FileInputStream(new File(sourceDoc));
            SdkBytes sourceBytes = SdkBytes.fromInputStream(sourceStream);

            // Get the input Document object as bytes
            Document myDoc = Document.builder().bytes(sourceBytes).build();
            DetectDocumentTextRequest detectDocumentTextRequest = DetectDocumentTextRequest.builder().document(myDoc)
                    .build();
            // Invoke the Detect operation
            DetectDocumentTextResponse textResponse = textractClient.detectDocumentText(detectDocumentTextRequest);
            List<Block> docInfo = textResponse.blocks();
            String resultStr = "";
            for (Block block : docInfo) {
                String str = block.text();
                if (StringUtils.isNotBlank(str)) {
                    //str = str.replaceAll("\\W", "");
                    str = str.trim();
                    resultStr = str;
                    if (str.length() == 4) {
                        resultStr = str;
                        System.out.println("resultStr : " + resultStr);
                    }
                }
            }
            System.out.println("textResponse : "+textResponse);
            return resultStr;
        }
        catch (TextractException | FileNotFoundException e) {
            System.err.println(e.getMessage());
        } finally {
            textractClient.close();
        }
        return null;
    }

}
