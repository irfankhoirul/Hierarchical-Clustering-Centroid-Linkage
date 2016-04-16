package clustering.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Irfan Khoirul on 03/04/2016.
 */
public class Node {
    private int id;
    //    private double v;
//    private double w;
//    private double x;
//    private double y;
//    private double z;
    private double[] value;
    private List<Node> clusterMembers = new ArrayList<>();
    private int cluster;

    public Node(int dimen) {
        value = new double[dimen];
    }

    public double[] getValue() {
        return value;
    }

    public void setValue(double[] value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


//    public double getV() {
//        return v;
//    }
//
//    public void setV(double v) {
//        this.v = v;
//    }
//
//    public double getW() {
//        return w;
//    }
//
//    public void setW(double w) {
//        this.w = w;
//    }

//    public double getX() {
//        return x;
//    }
//
//    public void setX(double x) {
//        this.x = x;
//    }
//
//    public double getY() {
//        return y;
//    }
//
//    public void setY(double y) {
//        this.y = y;
//    }
//
//    public double getZ() {
//        return z;
//    }
//
//    public void setZ(double z) {
//        this.z = z;
//    }

    public List<Node> getClusterMembers() {
        return clusterMembers;
    }

    public void setClusterMembers(List<Node> clusterMembers) {
        this.clusterMembers = clusterMembers;
    }

    public int getCluster() {
        return cluster;
    }

    public void setCluster(int cluster) {
        this.cluster = cluster;
    }

    @Override
    public String toString() {
        String s = "Node{" +
                "id=" + id;
        for (int i = 0; i < value.length; i++) {
            s += ", value[" + i + "]=" + value[i];
        }
        s += ", clusterMembersSize=" + clusterMembers.size() +
                ", cluster=" + cluster +
                '}';
        return s;

//        return "Node{" +
//                "id=" + id +
//                ", value[0]=" + v +
//                ", value[1]=" + w +
//                ", x=" + x +
//                ", y=" + y +
//                ", z=" + z +
//                ", clusterMembersSize=" + clusterMembers.size() +
//                ", cluster=" + cluster +
//                '}';
    }

    public String toString(int index) {
        String s = "Node:" + index + " {" +
                "id=" + id;
        for (int i = 0; i < value.length; i++) {
            s += ", value[" + i + "]=" + value[i];
        }
        s += ", clusterMembersSize=" + clusterMembers.size() +
                ", cluster=" + cluster +
                '}';
        return s;

//        return "Node:" + index + " {" +
//                "id=" + id +
//                ", v=" + v +
//                ", w=" + w +
//                ", x=" + x +
//                ", y=" + y +
//                ", z=" + z +
//                ", clusterMembersSize=" + clusterMembers.size() +
//                ", cluster=" + cluster +
//                '}';
    }
}
