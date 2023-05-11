package co.edu.umanizales.leds.controller;

import co.edu.umanizales.leds.config.MyNullPointerException;
import co.edu.umanizales.leds.controller.dto.*;
import co.edu.umanizales.leds.model.Led;
import co.edu.umanizales.leds.service.ListDEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/listde")
public class ListDEController {
    @Autowired
    private ListDEService listDEService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getLedsList(){
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            listDEService.getLeds().size();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "No hay ningun led agregado"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        return new ResponseEntity<>(new ResponseDTO(
                200,listDEService.getLeds(),null), HttpStatus.OK);
    }

    @GetMapping(path = "/byprevious")
    public ResponseEntity<ResponseDTO> getLedsByPreviousList(){
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            listDEService.getLeds().size();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "No hay ningun led agregado"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        return new ResponseEntity<>(new ResponseDTO(
                200,listDEService.getLedsByPrevious(),null), HttpStatus.OK);
    }

    @PostMapping(path = "/add/{id}")
    public ResponseEntity<ResponseDTO> addLedList(@PathVariable int id){
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            listDEService.compareId(id).toString();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(422, "Ya hay un led con esa ID"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        listDEService.addLed(new Led(id, false, null, null));
        return new ResponseEntity<>(new ResponseDTO(
                200,"Led Agregado",
                null), HttpStatus.OK);

    }

    @PostMapping(path = "/addtostart/{id}")
    public ResponseEntity<ResponseDTO> addToStartLedList(@PathVariable int id){
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            listDEService.compareId(id).toString();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(422, "Ya hay un led con esa ID"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        listDEService.addToStartLed(new Led(id, false, null, null));
        return new ResponseEntity<>(new ResponseDTO(
                200,"Led agregado al inicio",null), HttpStatus.OK);
    }

    @GetMapping(path = "/getsequenceleds")
    public ResponseEntity<ResponseDTO> getSequenceLeds() throws InterruptedException {
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            listDEService.getLeds().size();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "No hay ningun led agregado"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        listDEService.getSequenceLedsList();
        return new ResponseEntity<>(new ResponseDTO(
                200, "La secuencia de leds se ha realizado",null), HttpStatus.OK);
    }

    @GetMapping(path = "/rebootleds")
    public ResponseEntity<ResponseDTO> rebootLeds(){
        List<ErrorDTO> errorsList = new ArrayList<>();
        try {
            listDEService.getLeds().size();
        }
        catch (NullPointerException ex) {
            errorsList.add(new ErrorDTO(404, "No hay ningun led agregado"));
        }

        Optional<List<ErrorDTO>> optionalLista = Optional.ofNullable(errorsList);
        optionalLista.filter(l -> l.isEmpty()).orElseThrow(() -> new MyNullPointerException(errorsList));

        listDEService.rebootLedsList();
        return new ResponseEntity<>(new ResponseDTO(
                200, "Los leds han sido reinicidados",null), HttpStatus.OK);
    }
}