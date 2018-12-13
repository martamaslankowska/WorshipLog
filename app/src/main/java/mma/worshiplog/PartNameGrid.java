package mma.worshiplog;

public class PartNameGrid {

    String partName;
    String extraInfo;

    public PartNameGrid(String partName, String extraInfo) {
        this.partName = partName;
        this.extraInfo = extraInfo;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

}
