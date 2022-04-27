import models.Car;
import models.GraphMember;
import models.House;
import models.Person;
import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.DirectedPseudograph;

import java.io.IOException;

public class GraphExample {

    public static void main(String[] args) throws IOException {
        ListenableGraph<GraphMember, DefaultEdge> graphExample = new DefaultListenableGraph<>(new DirectedPseudograph<>(DefaultEdge.class));

        Car sportCar = new Car();
        sportCar.setLabel("This is a sportcar.");

        House bigSkyScraper = new House();
        bigSkyScraper.setLabel("This is a big skyscraper.");

        Person wealthyOwner = new Person();
        wealthyOwner.setAge(30);
        wealthyOwner.setLabel("This is a wealthy owner, let's call him Mike.");


        graphExample.addVertex(sportCar);
        graphExample.addVertex(bigSkyScraper);
        graphExample.addVertex(wealthyOwner);

        graphExample.addEdge(wealthyOwner,sportCar);
        graphExample.addEdge(wealthyOwner,bigSkyScraper);

        new ExportUtils().exportToGraphMl(graphExample,"sample-output/GraphExport.graphml");
        new ExportUtils().exportToGml(graphExample,"sample-output/GraphExport.gml");

    }
}
