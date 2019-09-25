package rjbank.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rjbank.model.History;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {


	public ArrayList<History> getByReceiver(int receiver);
	public ArrayList<History> getBySender(int sender);
	
}
