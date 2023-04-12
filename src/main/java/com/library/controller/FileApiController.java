package com.library.controller;

import com.library.exception.CustomException;
import com.library.exception.ErrorCode;
import com.library.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.apache.tika.Tika;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
public class FileApiController {

    private final FileUtil fileUtil;

    /**
     * 첨부파일 이미지 프린트
     *
     * @param savedFilename - 업로드 파일명
     */
    @GetMapping("/api/image-files")
    public ResponseEntity<byte[]> printAttachImage(@RequestParam final String savedFilename) {

        // 파일 경로 (업로드 경로 + 파일명)
        final String filePath = Paths.get(fileUtil.uploadPath(), savedFilename).toString();

        try {
            InputStream stream = Files.newInputStream(Paths.get(filePath));
            byte[] byteArr = IOUtils.toByteArray(stream);

            String mimeType = new Tika().detect(byteArr);
            MediaType mediaType = MediaType.valueOf(mimeType);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(mediaType);

            stream.close();
            return new ResponseEntity<>(byteArr, headers, HttpStatus.OK);

        } catch (IOException e) {
            throw new CustomException(ErrorCode.IMAGE_PRINT_FAILED);
        }
    }

    /**
     * 첨부파일 다운로드
     *
     * @param originalFilename - 원본 파일명
     * @param savedFilename    - 업로드 파일명
     */
    @GetMapping("/api/file-download")
    public ResponseEntity<Resource> downloadFile(@RequestParam final String originalFilename, @RequestParam final String savedFilename) throws MalformedURLException {

        String uploadPath = Paths.get(fileUtil.uploadPath(), savedFilename).toString();

        UrlResource resource = new UrlResource("file:" + uploadPath);

        String encodedUploadFileName = UriUtils.encode(originalFilename, StandardCharsets.UTF_8);

        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }
}
