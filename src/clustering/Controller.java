package clustering;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import clustering.model.Node;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

//    private static String LABEL = "Ruspini_Clustering_Result";
//    private static int DATA_COUNT = 75;
//    private static int DIMEN = 2;
//    private static int CLASS = 4;

    // Fossil
//    private static String LABEL = "Fossil_Clustering_Result";
//    private static int DATA_COUNT = 87;
//    private static int DIMEN = 6;
//    private static int CLASS = 3;

    // Wine
//    private static String LABEL = "Wine_Clustering_Result";
//    private static int DATA_COUNT = 178;
//    private static int DIMEN = 13;
//    private static int CLASS = 3;

    // Iris
//    private static String LABEL = "Iris_Clustering_Result";
//    private static int DATA_COUNT = 150;
//    private static int DIMEN = 4;
//    private static int CLASS = 3;

    // Thyroid
    private static String LABEL = "Thyroid_Clustering_Result";
    private static int DATA_COUNT = 215;
    private static int DIMEN = 5;
    private static int CLASS = 3;

    @FXML
    public Button btOpenFile;
    @FXML
    public Button btProcess;
    @FXML
    public TextField tfClusterNumber;
    @FXML
    public Label lbFilePath;
    @FXML
    public Label lbVariance;
    @FXML
    public TableView tbvOutput;

    private List<Node> nodes = new ArrayList<>();
    private List<Node> clusteredNodes = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btOpenFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("TXT", "*.txt")
                );
                File chosenFile = fileChooser.showOpenDialog(null);
                String path;
                if (chosenFile != null) {
                    path = chosenFile.getPath();
                } else {
                    path = "File Belum Dipilih";
                }

                try {
                    BufferedReader br = new BufferedReader(new FileReader(path));
                    StringBuilder sb = new StringBuilder();
                    String line = br.readLine();

                    for (int i = 0; i < DATA_COUNT; i++) {
                        sb.append(line);
                        sb.append(System.lineSeparator());
                        line = br.readLine();

                        List<Node> tmpClusterMember = new ArrayList<Node>();

                        String[] parts = line.split(",");
                        Node tmpNode = new Node(DIMEN);
                        tmpNode.setId(i);
                        double[] tmpValue = new double[DIMEN];
                        for (int j = 0; j < DIMEN; j++) {
                            tmpValue[j] = Double.parseDouble(parts[j]);
                        }
                        tmpNode.setValue(tmpValue);
//                        tmpNode.setV(Double.parseDouble(parts[0]));
//                        tmpNode.setW(Double.parseDouble(parts[1]));
//                        tmpNode.setX(Integer.parseInt(parts[2]));
//                        tmpNode.setY(Integer.parseInt(parts[3]));
//                        tmpNode.setZ(Integer.parseInt(parts[4]));
                        tmpNode.setCluster(0);
                        tmpClusterMember.add(tmpNode);
                        tmpNode.setClusterMembers(tmpClusterMember);

                        nodes.add(tmpNode);
                    }
                    lbFilePath.setText(lbFilePath.getText() + path);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btProcess.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int k = 0;
                try {
                    k = Integer.parseInt(tfClusterNumber.getText());
                } catch (Exception e) {

                }

                int counter = 0;
                while (nodes.size() > k) {
//                    System.out.println("Iteration: " + counter + "; Nodes: " + nodes.size());
                    double closestDistance = 999999;
                    int[] closestDstanceId = new int[2];
                    double[] T = new double[DIMEN];
                    double[] T1 = new double[DIMEN];
                    double[] T2 = new double[DIMEN];
                    double D;
                    double a;
                    for (int i = 0; i < nodes.size(); i++) {
                        for (int j = 0; j < DIMEN; j++) {
                            T1[j] = nodes.get(i).getValue()[j];
                        }
//                        T1[0] = nodes.get(i).getV();
//                        T1[1] = nodes.get(i).getW();
//                        T1[2] = nodes.get(i).getX();
//                        T1[3] = nodes.get(i).getY();
//                        T1[4] = nodes.get(i).getZ();
                        for (int j = 0; j < nodes.size(); j++) {
                            for (int l = 0; l < DIMEN; l++) {
                                T2[l] = nodes.get(j).getValue()[l];
                            }
//                            T2[0] = nodes.get(j).getV();
//                            T2[1] = nodes.get(j).getW();
//                            T2[2] = nodes.get(j).getX();
//                            T2[3] = nodes.get(j).getY();
//                            T2[4] = nodes.get(j).getZ();

                            for (int l = 0; l < DIMEN; l++) {
                                T[l] = Math.abs(T1[l] - T2[l]);
                            }
//                            T[0] = Math.abs(T1[0] - T2[0]);
//                            T[1] = Math.abs(T1[1] - T2[1]);
//                            T[2] = Math.abs(T1[2] - T2[2]);
//                            T[3] = Math.abs(T1[3] - T2[3]);
//                            T[4] = Math.abs(T1[4] - T2[4]);

                            D = 0;
                            for (int l = 0; l < DIMEN; l++) {
                                double tmp = T[l] * T[l];
                                D += tmp;
                            }
//                            D = (T[0] * T[0]) +
//                                    (T[1] * T[1]);
//                                    (T[2] * T[2]) +
//                                    (T[3] * T[3]) +
//                                    (T[4] * T[4]);

                            a = Math.sqrt(D);

                            if (i != j && a < closestDistance) {
                                closestDistance = a;
                                System.out.println(nodes.size());
                                System.out.println("CloserDistanceFound: " + closestDistance + " On: [" + i + "][" + j + "]");
                                System.out.println(nodes.get(i).toString() + "|" + nodes.get(j).toString());
                                closestDstanceId[0] = i;
                                closestDstanceId[1] = j;
                            }
                        }
                    }

                    int m = closestDstanceId[0];
                    int n = closestDstanceId[1];

                    List<Node> tmpClusterMember = new ArrayList<Node>();
                    tmpClusterMember.addAll(nodes.get(m).getClusterMembers());
                    tmpClusterMember.addAll(nodes.get(n).getClusterMembers());

                    double[] tmpFuseNodeValue = new double[DIMEN];
                    Node fuseNode = new Node(DIMEN);
                    for (int l = 0; l < DIMEN; l++) {
                        tmpFuseNodeValue[l] = (nodes.get(m).getValue()[l] + nodes.get(n).getValue()[l]) / 2;
                    }
                    fuseNode.setValue(tmpFuseNodeValue);
//                    fuseNode.setV((nodes.get(m).getV() + nodes.get(n).getV()) / 2);
//                    fuseNode.setW((nodes.get(m).getW() + nodes.get(n).getW()) / 2);
//                    fuseNode.setX((nodes.get(m).getX() + nodes.get(n).getX()) / 2);
//                    fuseNode.setY((nodes.get(m).getY() + nodes.get(n).getY()) / 2);
//                    fuseNode.setZ((nodes.get(m).getZ() + nodes.get(n).getZ()) / 2);
                    fuseNode.setClusterMembers(tmpClusterMember);
                    fuseNode.setCluster(counter + 1);

                    nodes.remove(m);
                    if (n > m) n--;
                    nodes.remove(n);

                    nodes.add(fuseNode);

                    counter++;
                }

                System.out.println("\n");
                for (int i = 0; i < nodes.size(); i++) {
                    System.out.println(nodes.get(i).toString(i));
                }

                System.out.println("\n");
                for (int i = 0; i < nodes.size(); i++) {
                    System.out.println("\n");
                    System.out.println("Cluster " + i + " members:");
                    for (int j = 0; j < nodes.get(i).getClusterMembers().size(); j++) {
                        nodes.get(i).getClusterMembers().get(j).setCluster(i);
                        System.out.println(nodes.get(i).getClusterMembers().get(j).toString(j));
                        clusteredNodes.add(nodes.get(i).getClusterMembers().get(j));
                    }
                }

//                updateTable();
                saveToText(clusteredNodes);


//                countVariance();


                System.out.println("END");
            }
        });
    }

/*
    private void updateTable() {
        tbvOutput.setEditable(true);
        tbvOutput.getColumns().removeAll();
        TableColumn columnId = new TableColumn("ID");

        for(int i=0; i<DIMEN; i++){

        }
        TableColumn columnV = new TableColumn("V");
        TableColumn columnW = new TableColumn("W");
//        TableColumn columnX = new TableColumn("X");
//        TableColumn columnY = new TableColumn("Y");
//        TableColumn columnZ = new TableColumn("Z");
        TableColumn columnCluster = new TableColumn("Cluster");

        ObservableList<Node> clusterOutput = FXCollections.observableArrayList(clusteredNodes);

        columnId.setCellValueFactory(new PropertyValueFactory<Node, Integer>("id"));
        columnV.setCellValueFactory(new PropertyValueFactory<Node, Double>("v"));
        columnW.setCellValueFactory(new PropertyValueFactory<Node, Double>("w"));
//        columnX.setCellValueFactory(new PropertyValueFactory<Node, Double>("x"));
//        columnY.setCellValueFactory(new PropertyValueFactory<Node, Double>("y"));
//        columnZ.setCellValueFactory(new PropertyValueFactory<Node, Double>("z"));
        columnCluster.setCellValueFactory(new PropertyValueFactory<Node, Double>("cluster"));

        tbvOutput.setItems(clusterOutput);
        tbvOutput.getColumns().addAll(columnId, columnV, columnW,
//                columnX, columnY, columnZ,
                columnCluster);

    }
*/

    private void saveToText(List<Node> clusteredArrayList) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("D:\\" + LABEL + ".txt"));
            for (int i = 0; i < clusteredArrayList.size(); i++) {
                String str = "";
                for (int j = 0; j < DIMEN; j++) {
                    str += clusteredArrayList.get(i).getValue()[j] + ",";
                }
                str += clusteredArrayList.get(i).getCluster();
                str += "\n";
                out.write(str);
//                out.write(clusteredArrayList.get(i).getV() + "," + clusteredArrayList.get(i).getW() + "," + clusteredArrayList.get(i).getCluster() + "\n");
                out.newLine();
            }
            out.close();
        } catch (IOException e) {
        }
    }


/*
    private void countVariance() {
        List<Double> Vc = new ArrayList<>();
        List<Double> avgV = new ArrayList<>();
        List<Double> avgW = new ArrayList<>();
        List<Double> avgX = new ArrayList<>();
        List<Double> avgY = new ArrayList<>();
        List<Double> avgZ = new ArrayList<>();
        double averageV = 0, averageW = 0, averageX = 0, averageY = 0, averageZ = 0;
        for (int i = 0; i < nodes.size(); i++) {
            double vcTmpPart1 = ((double) 1 / nodes.get(i).getClusterMembers().size());
            for (int j = 0; j < nodes.get(i).getClusterMembers().size(); j++) {
                averageV += nodes.get(i).getClusterMembers().get(j).getV();
                averageW += nodes.get(i).getClusterMembers().get(j).getW();
                averageX += nodes.get(i).getClusterMembers().get(j).getX();
                averageY += nodes.get(i).getClusterMembers().get(j).getY();
                averageZ += nodes.get(i).getClusterMembers().get(j).getZ();
            }
            averageV /= nodes.get(i).getClusterMembers().size();
            avgV.add(averageV);
            averageW /= nodes.get(i).getClusterMembers().size();
            avgW.add(averageW);
            averageX /= nodes.get(i).getClusterMembers().size();
            avgX.add(averageX);
            averageY /= nodes.get(i).getClusterMembers().size();
            avgY.add(averageY);
            averageZ /= nodes.get(i).getClusterMembers().size();
            avgZ.add(averageZ);

            double[] nodeData = new double[5];
            double[] cV = new double[5];
            double vcTmpPart2 = 0;
            double clusterVariance;
            for (int j = 0; j < nodes.get(i).getClusterMembers().size(); j++) {
                nodeData[0] = nodes.get(i).getClusterMembers().get(j).getV();
                nodeData[1] = nodes.get(i).getClusterMembers().get(j).getW();
                nodeData[2] = nodes.get(i).getClusterMembers().get(j).getX();
                nodeData[3] = nodes.get(i).getClusterMembers().get(j).getY();
                nodeData[4] = nodes.get(i).getClusterMembers().get(j).getZ();

                cV[0] = nodeData[0] - averageV;
                cV[1] = nodeData[1] - averageW;
                cV[2] = nodeData[2] - averageX;
                cV[3] = nodeData[3] - averageY;
                cV[4] = nodeData[4] - averageZ;

                vcTmpPart2 += (cV[0] * cV[0]) +
                        (cV[1] * cV[1]) +
                        (cV[2] * cV[2]) +
                        (cV[3] * cV[3]) +
                        (cV[4] * cV[4]);
            }
            clusterVariance = vcTmpPart1 * vcTmpPart2;
            System.out.println("Cluster " + i + ": " + vcTmpPart1 + " * " + vcTmpPart2 + " = " + clusterVariance);
            Vc.add(clusterVariance);
        }

        double vwTmpPart1 = ((double) 1 / (1000 - nodes.size()));
        double vwTmpPart2 = 0;
        double Vw;
        for (int i = 0; i < nodes.size(); i++) {
            vwTmpPart2 += (nodes.get(i).getClusterMembers().size() - 1) * Vc.get(i);
        }
        Vw = vwTmpPart1 * vwTmpPart2;

        double vbTmpPart1 = ((double) 1 / (nodes.size() - 1));
        double vbTmpPart2;
        double Vb;
        double[] cV = new double[5];
        double[] avg = new double[5];
        for (int i = 0; i < nodes.size(); i++) {
            avg[0] += avgV.get(i);
            avg[1] += avgW.get(i);
            avg[2] += avgX.get(i);
            avg[3] += avgY.get(i);
            avg[4] += avgZ.get(i);
        }
        avg[0] /= nodes.size();
        avg[1] /= nodes.size();
        avg[2] /= nodes.size();
        avg[3] /= nodes.size();
        avg[4] /= nodes.size();

        double tmp = 0;
        for (int i = 0; i < nodes.size(); i++) {
            cV[0] = avgV.get(i) - avg[0];
            cV[1] = avgW.get(i) - avg[1];
            cV[2] = avgX.get(i) - avg[2];
            cV[3] = avgY.get(i) - avg[3];
            cV[4] = avgZ.get(i) - avg[4];

            double z = (cV[0] * cV[0]) +
                    (cV[1] * cV[1]) +
                    (cV[2] * cV[2]) +
                    (cV[3] * cV[3]) +
                    (cV[4] * cV[4]);

            z *= nodes.size();
            tmp += z;
        }
        vbTmpPart2 = tmp;
        Vb = vbTmpPart1 * vbTmpPart2;
        System.out.println("VB: " + Vb);

        System.out.println("Variance: " + Vw / Vb);

        lbVariance.setText(lbVariance.getText() + Vw / Vb);
    }
*/
}
