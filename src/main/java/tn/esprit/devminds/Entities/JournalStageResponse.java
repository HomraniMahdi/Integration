package tn.esprit.devminds.Entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JournalStageResponse {
    private byte[] document;
    private String errorMessage;

    // Constructeurs
    public JournalStageResponse() {
    }

    public JournalStageResponse(byte[] document) {
        this.document = document;
    }

    public JournalStageResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    // Getters et Setters
    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

