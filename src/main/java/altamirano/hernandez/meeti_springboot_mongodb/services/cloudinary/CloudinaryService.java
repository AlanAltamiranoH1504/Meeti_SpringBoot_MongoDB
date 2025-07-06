package altamirano.hernandez.meeti_springboot_mongodb.services.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {
    @Autowired
    private Cloudinary cloudinary;

    public Map uploadImagen(MultipartFile imagen) throws IOException {
        File uploadedFile = File.createTempFile("temp", imagen.getOriginalFilename());
        imagen.transferTo(uploadedFile);

        Map result = cloudinary.uploader().upload(uploadedFile, ObjectUtils.emptyMap());
        return result;
    }
}
