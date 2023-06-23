package chat.service;

import chat.dto.MediaFileDto;
import chat.entity.MediaFile;
import chat.repository.MediaFileRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MediaFileService {

  private final MediaFileRepository mediaFileRepository;

   public  String addMediaFile(MediaFileDto fileDto) {

       MediaFile fileObj = new MediaFile();
       try{
           fileObj.setType(fileDto.getName());
           fileObj.setContent(fileDto.getContent());
           fileObj.setSize(fileDto.getSize());
           fileObj.setType(fileDto.getType());

           System.out.println("fileObj ====> "+fileObj);
           mediaFileRepository.save(fileObj);

       }catch(Exception e){
          System.out.println("errorrrrr in SAVING FILE");
          return "Unsuccessful file saving";
       }
      return  "Successfully upload";
   }

}
