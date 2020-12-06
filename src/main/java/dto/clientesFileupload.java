package dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import validators.Nif;
import validators.UniqueNif;
@Data
public class clientesFileupload {
    private MultipartFile image;
 

}