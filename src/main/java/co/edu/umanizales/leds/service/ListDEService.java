package co.edu.umanizales.leds.service;

import co.edu.umanizales.leds.model.Led;
import co.edu.umanizales.leds.model.*;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class ListDEService {
    private ListDE leds;

    public ListDEService() {
        leds = new ListDE();

        /*
        pets.add(new Pet("123","Carlos"));
        pets.add(new Pet("256","Mariana"));
        pets.add(new Pet("789","Daniel"));
        pets.add(new Pet("156","Paola"));
        pets.add(new Pet("196","Lina"));
        pets.add(new Pet("678","Sofia"));
        pets.add(new Pet("934","Juan"));
        pets.add(new Pet("177","Rodrigo"));
        pets.add(new Pet("481","Sergio"));
         */
    }

    public List<Led> getLeds() { return leds.getLeds(); }
    public List<Led> getLedsByPrevious() { return leds.getLedsByPrevious(); }
    public void addLed(Led led) { leds.add(led); }
    public void addToStartLed(Led led) { leds.addToStart(led); }
    public void getSequenceLedsList() throws InterruptedException { leds.getSequenceLeds(); }
    public void rebootLedsList() { leds.rebootLeds(); }
    public String compareId(int identification) { return leds.compareIdLeds(identification); }
}
