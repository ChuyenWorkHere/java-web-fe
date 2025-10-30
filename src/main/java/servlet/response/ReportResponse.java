package servlet.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class ReportResponse<T> {
    private Map<T, List<Integer>> object;
    private List<String> labels;

    public ReportResponse() {
        this.object = new HashMap<>();
        this.labels = new ArrayList<>();
    }
}
