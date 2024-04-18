package tn.esprit.devminds.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.devminds.Entities.ChatMessage;

@Repository
public interface ChatMessageRepository extends JpaRepository <ChatMessage,String> {
}
