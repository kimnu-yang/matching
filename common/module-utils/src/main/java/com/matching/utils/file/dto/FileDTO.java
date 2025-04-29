package com.matching.utils.file.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FileDTO {

    private String originName;

    private String saveName;

    private String extension;

    private String mimeType;

    private String uploadPath;

}
