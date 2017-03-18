package kg.goent.bean;

import kg.goent.model.SegmentType;

import javax.faces.bean.ManagedBean;

/**
 * Created by azire on 3/18/2017.
 */
@ManagedBean
public class BmcBean {

    private SegmentType st = new SegmentType();

    public SegmentType getSt() {
        return st;
    }

    public void setSt(SegmentType st) {
        this.st = st;
    }

    public void createSegmentType(){

    }
}
