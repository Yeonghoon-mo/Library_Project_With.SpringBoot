package com.library.domain.attach;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FileResponse {

    private String originalFilename;    // 원본 파일명
    private String saveFilename;        // 저장 파일명
    private long fileSize;              // 파일 크기

}
