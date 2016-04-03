package clustering.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Irfan Khoirul on 03/04/2016.
 */
public class Node {
    private int id;
    private double v;
    private double w;
    private double x;
    private double y;
    private double z;
    private List<Node> clusterMembers = new ArrayList<>();
    private int cluster;

    public Node() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public double getV() {
        return v;
    }

    public void setV(double v) {
        this.v = v;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

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
        return "Node{" +
                "id=" + id +
                ", v=" + v +
                ", w=" + w +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", clusterMembersSize=" + clusterMembers.size() +
                ", cluster=" + cluster +
                '}';
    }

    public String toString(int index) {
        return "Node:" + index + " {" +
                "id=" + id +
                ", v=" + v +
                ", w=" + w +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", clusterMembersSize=" + clusterMembers.size() +
                ", cluster=" + cluster +
                '}';
    }
}
