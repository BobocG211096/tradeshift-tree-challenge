package ro.tradeshift.treechallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Node {
    private String identification;

    @ToString.Exclude
    private Node parent;

    @ToString.Exclude
    @JsonIgnore
    private List<Node> children;

    private Integer height;
}
