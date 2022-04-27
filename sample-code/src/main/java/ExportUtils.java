import models.GraphMember;
import models.Person;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.AttributeType;
import org.jgrapht.nio.DefaultAttribute;
import org.jgrapht.nio.gml.GmlExporter;
import org.jgrapht.nio.graphml.GraphMLExporter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ExportUtils {


    public void exportToGml(Graph<GraphMember, DefaultEdge> g, String filePath) throws IOException {
        GmlExporter<GraphMember, DefaultEdge> gmlExporter = new GmlExporter<>();

        gmlExporter.setVertexAttributeProvider(new Function<GraphMember, Map<String, Attribute>>() {

            @Override
            public Map<String, Attribute> apply(GraphMember graphMember) {
                Map<String, Attribute> toReturn = new HashMap<>();
                toReturn.put("label", DefaultAttribute.createAttribute(graphMember.getLabel()));
                if (graphMember instanceof Person) {
                    toReturn.put("age", DefaultAttribute.createAttribute(((Person) graphMember).getAge()));
                }
                return toReturn;
            }

            @Override
            public <V> Function<V, Map<String, Attribute>> compose(Function<? super V, ? extends GraphMember> before) {
                return Function.super.compose(before);
            }

            @Override
            public <V> Function<GraphMember, V> andThen(Function<? super Map<String, Attribute>, ? extends V> after) {
                return Function.super.andThen(after);
            }
        });

        gmlExporter.setParameter(GmlExporter.Parameter.EXPORT_CUSTOM_VERTEX_ATTRIBUTES, true);
        gmlExporter.setParameter(GmlExporter.Parameter.EXPORT_EDGE_LABELS, true);
        gmlExporter.setParameter(GmlExporter.Parameter.EXPORT_VERTEX_LABELS, true);
        Writer fileWriter = null;
        try {
            fileWriter = new BufferedWriter(new FileWriter(filePath));
        } catch (IOException e) {
            throw e;
        }
        gmlExporter.exportGraph(g, fileWriter);
    }

    public void exportToGraphMl(Graph<GraphMember, DefaultEdge> g, String filePath) throws IOException {
        GraphMLExporter<GraphMember, DefaultEdge> graphML = new GraphMLExporter<>();
        graphML.setVertexAttributeProvider(new Function<GraphMember, Map<String, Attribute>>() {

            @Override
            public Map<String, Attribute> apply(GraphMember graphMember) {
                Map<String, Attribute> toReturn = new HashMap<>();
                toReturn.put("label", DefaultAttribute.createAttribute(graphMember.getLabel()));
                if (graphMember instanceof Person) {
                    toReturn.put("age", DefaultAttribute.createAttribute(((Person) graphMember).getAge()));
                }
                return toReturn;

            }

            @Override
            public <V> Function<V, Map<String, Attribute>> compose(Function<? super V, ? extends GraphMember> before) {
                return Function.super.compose(before);
            }

            @Override
            public <V> Function<GraphMember, V> andThen(Function<? super Map<String, Attribute>, ? extends V> after) {
                return Function.super.andThen(after);
            }
        });
        graphML.setExportEdgeLabels(true);
        graphML.setExportVertexLabels(true);
        graphML.registerAttribute("label", GraphMLExporter.AttributeCategory.NODE, AttributeType.STRING);
        graphML.registerAttribute("age", GraphMLExporter.AttributeCategory.NODE, AttributeType.STRING);

        Writer fileWriter = null;
        try {
            fileWriter = new BufferedWriter(new FileWriter(filePath));
        } catch (IOException e) {
            throw e;
        }

        graphML.exportGraph(g, fileWriter);
    }

}
