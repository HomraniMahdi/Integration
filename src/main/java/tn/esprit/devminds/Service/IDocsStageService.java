package tn.esprit.devminds.Service;

import tn.esprit.devminds.Entities.DocStage;

import java.util.List;

public interface IDocsStageService {

    List<DocStage> getAllDocStages();
    DocStage getDocStageById(Long id);
    DocStage addDocStage(DocStage docStage);
    DocStage updateDocStage(DocStage docStage);
    void deleteDocStage(Long id);
}
