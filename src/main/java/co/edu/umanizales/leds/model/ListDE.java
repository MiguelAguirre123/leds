package co.edu.umanizales.leds.model;

import co.edu.umanizales.leds.controller.dto.ErrorDTO;
import co.edu.umanizales.leds.model.Led;
import co.edu.umanizales.leds.model.NodeDE;
import lombok.Data;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
public class ListDE {
    private NodeDE head;
    private int size;

    public List<Led> getLeds() {
        if (head != null) {
            NodeDE temp = head;
            List<Led> listPets = new ArrayList<>();
            listPets.add(temp.getData());
            while (temp.getNext() != null) {
                temp = temp.getNext();
                listPets.add(temp.getData());
            }

            return listPets;
        }
        return null;
    }

    public List<Led> getLedsByPrevious() {
        if (head != null) {
            NodeDE temp = head;
            List<Led> listPets = new ArrayList<>();
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }

            listPets.add(temp.getData());
            while (temp.getPrevious() != null) {
                temp = temp.getPrevious();
                listPets.add(temp.getData());
            }

            return listPets;
        }
        return null;
    }

    public void add(Led led) {
        if (head != null) {
            NodeDE temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            /// Parado en el último
            NodeDE newNodeDE = new NodeDE(led);
            temp.setNext(newNodeDE);
            temp.getNext().setPrevious(temp);
        } else {
            head = new NodeDE(led);
        }
        size++;
    }

    public void addToStart(Led led) {
        if (head != null) {
            NodeDE newNode = new NodeDE(led);
            newNode.setNext(head);
            head = newNode;
            head.getNext().setPrevious(head);
        } else {
            head = new NodeDE(led);
        }
        size++;
    }

    public String compareIdLeds(int identification) {
        if (head != null) {
            NodeDE temp = head;

            if (temp.getData().getId() == identification) {
                return null;
            } else {
                while (temp.getNext() != null) {
                    temp = temp.getNext();
                    if (temp.getData().getId() == identification) {
                        return null;
                    }
                }
            }

            return "confirmado";
        } else {
            return "confirmado";
        }
    }

    public void getSequenceLeds() throws InterruptedException {
        if (head != null) {
            NodeDE temp1 = head;
            NodeDE temp2 = head;

            if (size == 1) {
                temp1.getData().setState(true);
                temp1.getData().setDateOn(LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                        DateTimeFormatter.ofPattern("HH:mm:ss")));
                return;
            }

            for (int i = 1; i < size/2; i++) {
                temp1 = temp1.getNext();
            }

            if (size % 2 == 0) {
                temp2 = temp1.getNext();
            }
            else {
                temp1 = temp1.getNext();
                temp2 = temp1;
            }

            while (temp1 != null) {
                temp1.getData().setState(true);
                temp2.getData().setState(true);
                temp1.getData().setDateOn(LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                        DateTimeFormatter.ofPattern("HH:mm:ss")));
                temp2.getData().setDateOn(LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                        DateTimeFormatter.ofPattern("HH:mm:ss")));

                if (temp1.getPrevious() != null) {
                    Thread.sleep(1000);
                    temp1.getData().setState(false);
                    temp2.getData().setState(false);
                    temp1.getData().setDateOff(LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                            DateTimeFormatter.ofPattern("HH:mm:ss")));
                    temp2.getData().setDateOff(LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                            DateTimeFormatter.ofPattern("HH:mm:ss")));
                    Thread.sleep(1000);
                }
                temp1 = temp1.getPrevious();
                temp2 = temp2.getNext();
            }
        }
    }

    public void rebootLeds() {
        if (head != null) {
            NodeDE temp = head;

            while (temp != null) {
                temp.setData(new Led(temp.getData().getId(), false, null, null));
                temp = temp.getNext();
            }
        }
    }
}
