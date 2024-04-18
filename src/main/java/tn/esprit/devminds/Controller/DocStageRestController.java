package tn.esprit.devminds.Controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.devminds.Entities.DocStage;
import tn.esprit.devminds.Service.IDocsStageService;


import java.util.List;
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/DocStage")
public class DocStageRestController {
    @Autowired
    IDocsStageService iDocStageService;

    @GetMapping
    public List<DocStage> getAllDocStages() {
        return iDocStageService.getAllDocStages();
    }

    @GetMapping("/{id}")
    public DocStage getDocStageById(@PathVariable Long id) {
        return iDocStageService.getDocStageById(id);
    }

    @PostMapping
    public DocStage addDocStage(@RequestBody DocStage docStage) {
        return iDocStageService.addDocStage(docStage);
    }

    @PutMapping("/{id}")
    public DocStage updateDocStage(@PathVariable Long id, @RequestBody DocStage docStage) {
        docStage.setIdDoc(id);
        return iDocStageService.updateDocStage(docStage);
    }

    @DeleteMapping("/{id}")
    public void deleteDocStage(@PathVariable Long id) {
        iDocStageService.deleteDocStage(id);
    }
}
