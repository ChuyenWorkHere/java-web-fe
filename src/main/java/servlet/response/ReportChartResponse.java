package servlet.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import servlet.models.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class ReportChartResponse<T> {
    private Map<T, List<Integer>> object;
    private List<String> labels;

    public ReportChartResponse() {
        this.object = new HashMap<>();
        this.labels = new ArrayList<>();
    }
}
