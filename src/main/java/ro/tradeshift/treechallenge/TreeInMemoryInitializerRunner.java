package ro.tradeshift.treechallenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ro.tradeshift.treechallenge.model.Node;
import ro.tradeshift.treechallenge.repository.NodeRepositoryInMemoryImplementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class TreeInMemoryInitializerRunner implements ApplicationRunner {
    private NodeRepositoryInMemoryImplementation nodeRepository;

    @Autowired
    public TreeInMemoryInitializerRunner(NodeRepositoryInMemoryImplementation nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        createTree();
    }

    private void createTree() {
        Node root = Node.builder()
                .identification("root")
                .parent(null)
                .height(0)
                .build();

        Node nodeA = Node.builder()
                .identification("a")
                .parent(root)
                .height(1)
                .build();

        Node nodeB = Node.builder()
                .identification("b")
                .parent(root)
                .children(Collections.emptyList())
                .height(1)
                .build();

        Node nodeC = Node.builder()
                .identification("c")
                .parent(nodeA)
                .height(2)
                .children(Collections.emptyList())
                .build();

        setChildren(root,nodeA, nodeB, nodeC);

        ArrayList<Node> tree = new ArrayList<>(Arrays.asList(root, nodeA, nodeB, nodeC));
        nodeRepository.setTree(tree);

    }

    private void setChildren(Node root, Node nodeA, Node nodeB, Node nodeC) {
        List<Node> rootChildren = new ArrayList<>();
        List<Node> nodeAChildren = new ArrayList<>();

        rootChildren.add(nodeA);
        rootChildren.add(nodeB);
        root.setChildren(rootChildren);

        nodeAChildren.add(nodeC);
        nodeA.setChildren(nodeAChildren);
    }
}
