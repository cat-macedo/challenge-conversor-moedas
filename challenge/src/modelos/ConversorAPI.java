package modelos;

import java.util.Map;

public record ConversorAPI(String base_code, Map<String, Double> conversion_rates) {
}
