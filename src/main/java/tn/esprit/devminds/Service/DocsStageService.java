package tn.esprit.devminds.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.devminds.Entities.DocStage;
import tn.esprit.devminds.Repository.DocStageRepository;

import java.util.List;

@Service
public class DocsStageService implements IDocsStageService
{
    @Autowired
    DocStageRepository docStageRepository;
    @Override
    public List<DocStage> getAllDocStages() {
        return (List<DocStage>) docStageRepository.findAll();
    }

    @Override
    public DocStage getDocStageById(Long id) {
        return docStageRepository.findById(id).orElse(null);
    }

    @Override
    public DocStage addDocStage(DocStage docStage) {
        return docStageRepository.save(docStage);
    }

    @Override
    public DocStage updateDocStage(DocStage docStage) {
        return docStageRepository.save(docStage);
    }

    @Override
    public void deleteDocStage(Long id) {
        docStageRepository.deleteById(id);
    }
}
