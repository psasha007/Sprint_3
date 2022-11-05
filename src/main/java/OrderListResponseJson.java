import java.util.List;

public class OrderListResponseJson {
    List<AvailableStations> availableStations;
    List<Orders> orders;
    PageInfo pageInfo;

    private String message;

    public List<AvailableStations> getAvailableStations() {
        return availableStations;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setAvailableStations(List<AvailableStations> availableStations) {
        this.availableStations = availableStations;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
