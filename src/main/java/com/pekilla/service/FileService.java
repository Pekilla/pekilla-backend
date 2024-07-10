package com.pekilla.service;

import com.pekilla.util.RandomUtil;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Validated
@Service
public class FileService {
    @Value("${IMAGE_SERVER_URL}")
    public String imageServerUrl;

    @Value("${UPLOAD_PATH}")
    public String uploadPath;

    public String getImageUrl(String fileName, FileType fileType) {
        return fileName != null ? imageServerUrl+fileType.folder+"/"+fileName : null;
    }

    @RequiredArgsConstructor
    public enum FileType {
        USER_ICON("/users/icon"),
        USER_BANNER("/users/banner"),
        CATEGORY_ICON("/categories/icon"),
        CATEGORY_BANNER("/categories/banner");

        private final String folder;
    }

    public String getExtensionFromMultipartFile(MultipartFile multipartFile) {
        String[] seperatedFileName = Objects.requireNonNull(multipartFile.getOriginalFilename()).split("\\.");
        return seperatedFileName[seperatedFileName.length - 1];
    }

    /**
     * Function to save a file from the frontend.
     *
     * @param multipartFile The file from the frontend.
     * @param fileType To know the file is designated to which folder.
     *
     * @return The file name.
     */
    public String saveFile(@NotNull MultipartFile multipartFile, FileType fileType) throws IOException {
        String extension = getExtensionFromMultipartFile(multipartFile);

        File destinationFile = new File(RandomUtil.randFileName(uploadPath+fileType.folder, extension));

        while (destinationFile.exists()) {
            destinationFile.renameTo(new File(RandomUtil.randFileName(uploadPath+fileType.folder, extension)));
        }

        destinationFile.createNewFile();
        multipartFile.transferTo(destinationFile);

        return destinationFile.getName();
    }
}
