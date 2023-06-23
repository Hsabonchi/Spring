package chat.conroller;

import chat.dto.MediaFileDto;
import chat.service.MediaFileService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


@RequiredArgsConstructor
@RestController
@RequestMapping("/files")
public class MediaFileController {

    private final MediaFileService mediaFileService;
    @Value("${path.tosave.files}")
    private String destPath;

    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile file) throws Exception {

        if (file.getSize() > 256000) {
            return "File size  limit exceed";
        }

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!"png".equals(extension) && !"jpeg".equals(extension) && !"jpg".equals(extension) && !"gif".equals(extension)) {
            throw new Exception("Only jpg/jpeg and png files are accepted");
        }


        try {
            String fileName = file.getOriginalFilename();
            String location = destPath + fileName;
            file.transferTo(new File(location));

            MediaFileDto fileDto = new MediaFileDto();
            fileDto.setLocation(location);
            fileDto.setType(file.getContentType());
            fileDto.setSize((int) file.getSize());
            mediaFileService.addMediaFile(fileDto);
            return "File uploaded Sucessfully";
        } catch (Exception e) {
            return "Unsuccessful file saving ===> " + e;
        }

    }


}
