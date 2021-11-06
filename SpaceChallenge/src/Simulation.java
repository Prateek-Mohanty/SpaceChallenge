import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
public class Simulation {
    public ArrayList<Item> loadItems(String filename) throws FileNotFoundException{
        File file = new File(filename);
        Scanner sc = new Scanner(file);
        ArrayList<Item> items = new ArrayList<>();
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String[] oneItem = line.split("=");
            items.add(new Item(oneItem[0],Integer.parseInt(oneItem[1])));
        }
        System.out.println(filename + " contains " + items.size() + " items");

        return items;
    }

    ArrayList<Rocket> loadU1(ArrayList<Item> list) {
        ArrayList<Rocket> fleet = new ArrayList<>();
        Rocket r = new U1();

        System.out.println("\nU1 Rocket weight = " + r.weight + "; maxWeight = " + r.max_weight);

        for (Item i : list) {

            while (!r.canCarry(i)) {
                fleet.add(r);
                r = new U1();
            }
            r.carry(i);
        }
        fleet.add(r);
        System.out.println("U1 fleet contains " + fleet.size() + " rockets");
        return fleet;
    }


    /* This method takes the ArrayList of Items and starts creating U2 rockets and filling them with those items
     * until all items are loaded. The method then returns the ArrayList of those U2 rockets that are fully loaded.
     */
    ArrayList<Rocket> loadU2(ArrayList<Item> list) {
        ArrayList<Rocket> fleet = new ArrayList<>();
        Rocket r = new U2();

        System.out.println("U2 Rocket weight = " + r.weight + "; maxWeight = " + r.max_weight);

        for (Item i : list) {

            while (!r.canCarry(i)) {
                fleet.add(r);
                r = new U2();
            }
            r.carry(i);
        }
        fleet.add(r);
        System.out.println("U2 fleet contains " + fleet.size() + " rockets\n");
        return fleet;
    }
    /* This method takes an ArrayList of Rockets and calls launch and land methods for each of the rockets in the
     * ArrayList. Every time a rocket explodes or crashes (i.e if launch or land return false), it will have to send
     * that rocket again. All while keeping track of the total budget required to send each rocket safely to Mars.
     * runSimulation then returns the total budget required to send all rockets (including the crashed ones).
     */
    int runSimulation(ArrayList<Rocket> list) {
        int num = 0; //failed trials of launch/land
        for (Rocket r : list) {

            while (!r.launch()) {
                r.launch();
                num++;
            }
            while (!r.land()) {
                r.land();
                num++;
            }
        }
        int budget = list.get(0).cost * (list.size() + num);
        System.out.println(list.size() + " rockets and " + num + " extra trials = "
                + (list.size() + num) + " in total");
        return budget;
    }
}