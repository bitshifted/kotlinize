package co.bitshifted.kotlinize.data;

public class MutableData {

    private String status;

    public MutableData(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
