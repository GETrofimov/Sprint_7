package order.body;

import java.util.List;

public class OrderBodyResponse {
    private List<OrderResponse> orderResponse;
    private PageInfoResponse pageInfoResponse;
    private List<AvailableStationResponse> availableStationResponses;

    public OrderBodyResponse() {}

    public List<OrderResponse> getOrder() {
        return orderResponse;
    }

    public void setOrder(List<OrderResponse> orderResponse) {
        this.orderResponse = orderResponse;
    }

    public PageInfoResponse getPageInfo() {
        return pageInfoResponse;
    }

    public void setPageInfo(PageInfoResponse pageInfoResponse) {
        this.pageInfoResponse = pageInfoResponse;
    }

    public List<AvailableStationResponse> getAvailableStations() {
        return availableStationResponses;
    }

    public void setAvailableStations(List<AvailableStationResponse> availableStationResponses) {
        this.availableStationResponses = availableStationResponses;
    }
}
