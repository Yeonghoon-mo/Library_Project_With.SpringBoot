package com.library.utils;

import com.library.domain.attach.FileResponse;
import com.library.exception.CustomException;
import com.library.exception.ErrorCode;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class FileUtil {

    // 운영체제별 업로드 경로
    public String uploadPath() {
        String os = System.getProperty("os.name").toLowerCase();
        String windowPath = Paths.get("C:", "develop", "upload-files", "files").toString();
        String linuxPath = Paths.get("/", "usr", "local", "lib", "apache-tomcat-2129", "webapps", "files").toString();
        return (os.contains("win")) ? windowPath : linuxPath;
    }

    // 파일명 랜덤 문자열 반환
    private String getRandomString() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    // 첨부파일 업로드
    public FileResponse uploadFile(MultipartFile file) {

        if (file == null || file.getSize() == 0) {
            return null;
        }

        final String uploadPath = this.uploadPath();
        final File dir = new File(uploadPath);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        try {
            final String extension = FilenameUtils.getExtension(file.getOriginalFilename()); // 파일 확장자
            final String saveFilename = getRandomString() + "." + extension; // 저장할 파일명
            file.transferTo(new File(dir, saveFilename)); // 파일 write
            return new FileResponse(file.getOriginalFilename(), saveFilename, file.getSize());

        } catch (IOException e) {
            throw new CustomException(ErrorCode.FILE_UPLOAD_FAILED);
        }
    }

    // 서버에 첨부 파일을 생성하고, 업로드 파일 목록 반환
    public List<FileResponse> uploadFiles(List<MultipartFile> files) {

        if (CollectionUtils.isEmpty(files)) {
            return Collections.emptyList();
        }

        List<FileResponse> uploadFiles = new ArrayList<>();

        for (MultipartFile file : files) {
            if (file != null && file.getSize() > 0) {
                uploadFiles.add(uploadFile(file));
            }
        }

        return uploadFiles;
    }
}

