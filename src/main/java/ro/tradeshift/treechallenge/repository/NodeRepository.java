package ro.tradeshift.treechallenge.repository;

import org.springframework.stereotype.Repository;
import ro.tradeshift.treechallenge.model.*;

import java.util.List;

@Repository
public interface NodeRepository {
    List<Node> getChildren(String parentIdentification);

    void changeParent(String nodeIdentification, String parentIdentification);
}
