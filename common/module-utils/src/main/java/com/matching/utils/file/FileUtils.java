package com.matching.utils.file;

import com.matching.utils.file.dto.FileDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Slf4j
@Service
public class FileUtils {
    private static final List<String> ALLOW_EXCEPTION_LIST = Arrays.asList("gif", "jpg", "jpeg", "png", "bmp");

    @Value("${spring.servlet.multipart.location}")
    String location;

    // 추가 폴더 위치
    private static final String SUB_PATH = File.separator + "data" + File.separator + "metabugo" + File.separator + "resources" + File.separator + "images" + File.separator;

    // 파일 업로드
    public FileDTO upload(MultipartFile file, String folder) {
        FileDTO fileDTO = imageFileCheck(file, SUB_PATH + File.separator + folder + File.separator);

        // 파일 저장
        if (fileDTO != null) {
            // 폴더 생성
            File uploadDirectory = new File(location + fileDTO.getUploadPath());
            if (!uploadDirectory.exists()) {
                uploadDirectory.mkdirs();
            }

            try {
                File newFile = new File(uploadDirectory, Objects.requireNonNull(file.getOriginalFilename()));
                file.transferTo(newFile);
            }
            catch (IllegalStateException | IOException e) {
                log.error("[파일 저장 에러]", e);
            }
        }

        return fileDTO;
    }

    // 이미지 다운로드
    public ResponseEntity<Resource> download(FileDTO fileDTO) throws IOException {
        Path path = Paths.get(location + fileDTO.getUploadPath() + fileDTO.getSaveName());
        String contentType = Files.probeContentType(path);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDisposition(
            ContentDisposition.builder("attachment")
                .filename(fileDTO.getOriginName(), StandardCharsets.UTF_8)
                .build()
        );
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, contentType);

        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return new ResponseEntity<>(resource, httpHeaders, HttpStatus.OK);
    }

    // 이미지 표시
    public ResponseEntity<Resource> display(FileDTO fileDTO) {
        try {
            if (fileDTO == null) {
                log.error("[파일 정보 Null]");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            String path = location + fileDTO.getUploadPath() + fileDTO.getSaveName();
            Resource resource = new FileSystemResource(path);

            if (!resource.exists()) {
                log.error("[Resource 없음]");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            HttpHeaders httpHeaders = new HttpHeaders();
            Path filepath = Paths.get(path);
            httpHeaders.add("Content-Type", Files.probeContentType(filepath));

            return new ResponseEntity<>(resource, httpHeaders, HttpStatus.OK);
        } catch (IOException e) {
            log.error("[이미지 표시 Exception] ", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 이미지 파일 체크
    private FileDTO imageFileCheck(MultipartFile file, String folder) {
        //image 시그니처
        List<int[]> signatureList = new ArrayList<>();
        signatureList.add(new int[] { 0x42, 0x4D });	// BMP, DIB
        signatureList.add(new int[] { 0x47, 0x49, 0x46, 0x38, 0x37, 0x61 });	// GIF
        signatureList.add(new int[] { 0x47, 0x49, 0x46, 0x38, 0x39, 0x61 });	// GIF
        signatureList.add(new int[] { 0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A });	// PNG
        signatureList.add(new int[] { 0xFF, 0xD8, 0xFF, 0xE0 });	// JPEG/JFIF
        signatureList.add(new int[] { 0xFF, 0xD8, 0xFF, 0xE8 });	// JPEG/EXIF
        signatureList.add(new int[] { 0xFF, 0xD8, 0xFF, 0xE1 });	// JPEG/EXIF

        try {
            // file name
            String originalFilename = file.getOriginalFilename();
            // 파일명 check
            if (originalFilename == null) {
                log.error("[파일 업로드 파일명 Null]");
                return null;
            }

            // 확장자 체크
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            if (!ALLOW_EXCEPTION_LIST.contains(extension)) {
                log.error("[허용하지 않는 확장자] {}", extension);
                return null;
            }

            // ##### MIME 체크 (image/*) 체크 (null 체크 어떻게 해야 하나 확인 필요)
            Path source = Paths.get(file.getOriginalFilename());
            String mimeType = Files.probeContentType(source);
            if (mimeType != null && !mimeType.contains("image/")) {
                log.error("[허용하지 않는 MIME] {}", mimeType);
                return null;
            }

            // 헤더 시그니처 체크 (굳이 안해 되긴 함)
            boolean isCheck = true;
            int[] hexSignature = null;
            for (int[] i : signatureList) {
                InputStream is = file.getInputStream();
                hexSignature = i;

                isCheck = true;
                for (int j : hexSignature) {
                    // 하나의 헤더 정보가 모두 같은지 체크
                    if (is.read() != j) {
                        isCheck = false;
                        break;
                    }
                }

                // 모두 같은게 존재
                if (isCheck) {
                    break;
                }
            }

            if (!isCheck) {
                log.error("[이미지 헤더 시그니처가 아님]");
                return null;
            }

            // 파일 넣기
            FileDTO fileDTO = new FileDTO();
            fileDTO.setOriginName(originalFilename);
            fileDTO.setMimeType(mimeType);
            fileDTO.setExtension(extension);
            fileDTO.setSaveName(UUID.randomUUID()+ "." + extension);
            fileDTO.setUploadPath(folder);

            return fileDTO;
        }
        catch (Exception e) {
            log.error("[이미지 업로드 Exception] ", e);
            return null;
        }
    }
}
