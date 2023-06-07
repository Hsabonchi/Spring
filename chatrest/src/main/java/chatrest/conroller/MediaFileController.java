package chatrest.conroller;

import chatrest.dto.MediaFileDto;
import chatrest.entity.MediaFile;
import chatrest.service.MediaFileService;
import jakarta.persistence.Convert;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;

@RequiredArgsConstructor
@RestController
@RequestMapping("/files")
public class MediaFileController {

    private final MediaFileService mediaFileService;
    @PostMapping
    public String uploadMediaFile(@RequestParam ("file") MultipartFile file) throws IOException {

        System.out.println("size   ==>  "+file.getSize());
        // Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
        long fileSizeInKB = file.getSize() / 1024;

        System.out.println("fileSizeInKB   ==>  "+fileSizeInKB);

        MediaFileDto fileDto = new MediaFileDto();
        fileDto.setName(file.getOriginalFilename());
        fileDto.setContent(file.getBytes());
        fileDto.setType(file.getContentType());
        fileDto.setSize((int)file.getSize());
        System.out.println("fileDto ====>  "+fileDto);
        return mediaFileService.addMediaFile(fileDto);

    }


}
