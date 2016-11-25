package com.oneclouder.pidm.menu.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by clouder on 16-9-18.
 */
public class Node {

    /**
     * 节点ｉｄ
     */
    private Integer id;
    /**
     * 节点名称
     */
    private String text;
    /**
     * 节点ｕｒｌ
     */
    private String url;
    /**
     * 节点父ｉｄ
     */
    private Integer parentId;

    private Integer orderNum;

    private Integer openWay;

    private Integer isHidden;

    private String icon;

    private Integer canPublish;

    public Integer getCanPublish() {
        return canPublish;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public void setCanPublish(Integer canPublish) {
        this.canPublish = canPublish;
    }

    /**
     * 记录节点的选中状态
     */
    private State state = new State();

    /**
     * 子节点
     */
    private List<Node> nodes = new LinkedList<>();

    public Integer getOpenWay() {
        return openWay;
    }

    public void setOpenWay(Integer openWay) {
        this.openWay = openWay;
    }

    public Integer getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Integer isHidden) {
        this.isHidden = isHidden;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public class State{

        public boolean checked = false;

    }
}
