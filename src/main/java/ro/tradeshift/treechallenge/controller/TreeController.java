package ro.tradeshift.treechallenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tradeshift.treechallenge.model.ChangeParentDTO;
import ro.tradeshift.treechallenge.model.GetChildrenDTO;
import ro.tradeshift.treechallenge.service.NodeService;

@RequestMapping("api/tree")
@RestController
public class TreeController {
    private NodeService nodeService;

    @Autowired
    public TreeController(NodeService nodeService){
        this.nodeService = nodeService;
    }

    @GetMapping("/children/{nodeIdentification}")
    public ResponseEntity<GetChildrenDTO> getChildren(@PathVariable String nodeIdentification) {

        return new ResponseEntity<>(nodeService.getChildren(nodeIdentification), HttpStatus.OK);
    }

    @PutMapping("/parent/{nodeIdentification}")
    public ResponseEntity<String> changeParent(@PathVariable String nodeIdentification,
                                               @RequestBody ChangeParentDTO changeParentDTO ) {
        nodeService.changeParent(nodeIdentification, changeParentDTO.getNodeIdentification());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
