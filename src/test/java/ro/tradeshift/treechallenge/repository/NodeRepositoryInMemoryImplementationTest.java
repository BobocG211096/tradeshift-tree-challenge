package ro.tradeshift.treechallenge.repository;

import org.apache.el.util.ReflectionUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ro.tradeshift.treechallenge.model.Node;

import java.util.*;

import static java.lang.Integer.valueOf;
import static org.junit.Assert.assertEquals;

public class NodeRepositoryInMemoryImplementationTest {
    private NodeRepositoryInMemoryImplementation nodeRepository;

    @Before
    public void setUp() {
        nodeRepository = new NodeRepositoryInMemoryImplementation();
        createTree();
    }

    @Test
    public void getChildren() {
        List<Node> childrenNodes = nodeRepository.getChildren("a");

        assertEquals(childrenNodes.get(0).getIdentification(), "c");
    }

    @Test
    public void changeParent() {
        nodeRepository.changeParent("c", "root");

        List<Node> tree = nodeRepository.getTree();

        Node nodeC = tree.stream()
                .filter(node -> node.getIdentification().equals("c"))
                .findAny()
                .orElseThrow(NoSuchElementException::new);

        Node root = tree.stream()
                .filter(node -> node.getIdentification().equals("root"))
                .findAny()
                .orElseThrow(NoSuchElementException::new);

        assertEquals(nodeC.getHeight(),  valueOf(1));
        assertEquals(nodeC.getParent().getIdentification(), "root");
        assertEquals(root.getChildren().get(0).getIdentification(), "c");
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