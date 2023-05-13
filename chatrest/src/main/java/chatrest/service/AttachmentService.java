package chatrest.service;

import java.util.List;
import org.springframework.stereotype.Service;
import chatrest.entity.Attachment;
import chatrest.repository.AttachmentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttachmentService {

  private final AttachmentRepository attachmentRepository;

  public Attachment findById(Long id) {
    return attachmentRepository.findById(id).get();

  }

  public List<Attachment> findAllAttachments() {
    return attachmentRepository.findAll();

  }

}
