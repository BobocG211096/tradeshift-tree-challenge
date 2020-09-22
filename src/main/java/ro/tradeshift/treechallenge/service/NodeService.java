package ro.tradeshift.treechallenge.service;

import ro.tradeshift.treechallenge.model.*;

import java.util.List;

public interface NodeService {
    GetChildrenDTO getChildren(String parentIdentification);
    void changeParent(String nodeIdentification, String parentIdentification);
}
