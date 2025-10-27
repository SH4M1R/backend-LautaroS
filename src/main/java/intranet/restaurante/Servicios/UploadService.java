package intranet.restaurante.Servicios;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class UploadService {

    private final String uploadDir = "src/main/resources/static/upload/";

    public String saveUpload(MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            byte[] bytes = file.getBytes();
            String encodedName = URLEncoder.encode(Objects.requireNonNull(file.getOriginalFilename()), StandardCharsets.UTF_8);
            Path path = Paths.get(uploadDir + encodedName);

            if (!Files.exists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }

            Files.write(path, bytes);
            return encodedName;
        }
        return null;
    }

    public void deleteUpload(String fileName) {
        if (fileName != null) {
            File file = new File(uploadDir + fileName);
            if (file.exists()) {
                file.delete();
            }
        }
    }
}