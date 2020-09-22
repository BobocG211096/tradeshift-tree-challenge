package ro.tradeshift.treechallenge.repository;

import org.springframework.stereotype.Repository;
import ro.tradeshift.treechallenge.model.Node;

import java.util.*;

import static java.util.Collections.singletonList;

@Repository
public class NodeRepositoryInMemoryImplementation implements NodeRepository {
    private List<Node> tree;

    @Override
    public List<Node> getChildren(String parentIdentification) {
        List<Node> childrenNodes = new ArrayList<>();
        Node parentNode = getNode(parentIdentification);

        traverseNodeAndFetchChildren(parentNode, childrenNodes);

        return childrenNodes;
    }

    @Override
    public void changeParent(String nodeIdentification, String parentIdentification) {
        Node node = getNode(nodeIdentification);
        Node parentNode = getNode(parentIdentification);

        if (node.getHeight() - parentNode.getHeight() > 1) {
            node.setHeight(parentNode.getHeight() + 1);
        }

        node.setParent(parentNode);
        parentNode.setChildren(singletonList(node));
    }

    public List<Node> getTree() {
        return tree;
    }

    public void setTree(List<Node> tree) {
        this.tree = tree;
    }

    private void traverseNodeAndFetchChildren(Node parentNode, List<Node> childrenNodes) {
        int size = parentNode.getChildren().size();

        if (size > 0) {
            for (Node childNode : parentNode.getChildren()) {
                childrenNodes.add(childNode);
                traverseNodeAndFetchChildren(childNode, childrenNodes);
            }
        }
    }

    private Node getNode(String nodeIdentification) {
        return tree.stream()
                .filter(node -> node.getIdentification().equals(nodeIdentification))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }


}
