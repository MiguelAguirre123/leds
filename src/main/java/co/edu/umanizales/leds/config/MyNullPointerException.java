package co.edu.umanizales.leds.config;

import co.edu.umanizales.leds.controller.dto.ErrorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MyNullPointerException extends NullPointerException {
    private List<ErrorDTO> elements;
}
