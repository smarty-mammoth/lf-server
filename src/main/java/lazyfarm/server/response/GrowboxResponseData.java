package lazyfarm.server.response;

import lazyfarm.server.entities.GrowBox;

import java.util.List;

public class GrowboxResponseData extends ResponseData {
    private List<GrowBox> growBoxes;

    public List<GrowBox> getGrowBoxes() {
        return growBoxes;
    }

    public void setGrowBoxes(List<GrowBox> growBoxes) {
        this.growBoxes = growBoxes;
    }
}
