package game.field;

import game.enums.FieldElementStatus;

public class FieldElement {
    private FieldElementStatus status;
    private String code;

    public FieldElement(String code) {
        status = FieldElementStatus.HIDDEN;
        this.code = code;
    }

    public FieldElement() {
        status = FieldElementStatus.HIDDEN;
        code = "empty";
    }
    public FieldElementStatus getStatus() {
        return status;
    }

    public void setStatus(FieldElementStatus status) {
        this.status = status;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
