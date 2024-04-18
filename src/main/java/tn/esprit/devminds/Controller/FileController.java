package tn.esprit.devminds.Controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.devminds.Entities.Evenement;
import tn.esprit.devminds.Entities.FileEntity;
import tn.esprit.devminds.Repository.FileRepository;
import tn.esprit.devminds.Service.EventServiceImp;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/events")
@CrossOrigin(origins = "http://localhost:4200")
public class FileController {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private EventServiceImp eventService;

    @GetMapping("/files")
    public ResponseEntity<List<FileEntity>> getFile() {
        List<FileEntity> files = fileRepository.findAll();
        return ResponseEntity.ok(files);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable Long id) {
        FileEntity fileEntity = fileRepository.findById(id).orElse(null);
        if (fileEntity != null) {
            try {
                byte[] content = Files.readAllBytes(Paths.get(fileEntity.getFilePath()));
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.parseMediaType(fileEntity.getContentType()));
                headers.setContentDisposition(ContentDisposition.attachment().filename(fileEntity.getFilename()).build());
                ByteArrayResource resource = new ByteArrayResource(content);
                return ResponseEntity.ok().headers(headers).body(resource);
            } catch (IOException e) {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get-image-url/{filename}")
    public ResponseEntity<String> getUploadedImageURL(@PathVariable String filename) {
        FileEntity fileEntity = fileRepository.findByFilename(filename);
        if (fileEntity != null) {
            return ResponseEntity.ok(fileEntity.getFilePath()); // Assuming filePath stores the relative path
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam(required = false) Long eventId) throws IOException {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file to upload");
        }

        // Generate a unique filename
        String fileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        String uploadDir = "C://Users/ihebb/Downloads/jpg2png"; // Replace with your upload directory
        String filePath = Paths.get(uploadDir, fileName).toString();

        // Create the upload directory if it doesn't exist
        Files.createDirectories(Paths.get(uploadDir));

        try {
            // Save the uploaded file
            Files.write(Paths.get(filePath), file.getBytes());

            Evenement event;
            if (eventId != null) {
                event = eventService.getEventById(eventId);
                if (event == null) {
                    return ResponseEntity.notFound().build();
                }
            } else {
                event = new Evenement();
            }

            event.setUrl(filePath);
            eventService.addEvent(event);

            return ResponseEntity.ok(filePath);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//VM //--add-opens java.base/java.net=ALL-UNNAMED

}
