package ro.tradeshift.treechallenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tradeshift.treechallenge.model.*;
import ro.tradeshift.treechallenge.repository.NodeRepository;

import java.util.List;

@Service
public class NodeServiceImplementation implements NodeService {
    private NodeRepository nodeRepository;

    @Autowired
    public NodeServiceImplementation(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    @Override
    public GetChildrenDTO getChildren(String parentIdentification) {
        List<Node> children = nodeRepository.getChildren(parentIdentification);

        return GetChildrenDTO.builder().childrenNodes(children).build();
    }

    @Override
    public void changeParent(String nodeIdentification, String parentIdentification) {
        nodeRepository.changeParent(nodeIdentification, parentIdentification);
    }
}
