package chat.dto;

import lombok.Data;

@Data
public class MediaFileDto {

    private String name;

    private int size;

    private String type;

    private byte[] content;
}