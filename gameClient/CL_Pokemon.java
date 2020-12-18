package gameClient;

import api.edge_data;
import gameClient.util.Point3D;
import org.json.JSONObject;

public class CL_Pokemon {
    private edge_data _edge;
    private double _value;
    private int _type;
    private Point3D _pos;
    private double min_dist;
    private int min_ro;
    private boolean b;

    /**
     * basic constructor
     * @param p
     * @param t
     * @param v
     * @param s
     * @param e
     */
    public CL_Pokemon(Point3D p, int t, double v, double s, edge_data e) {
        _type = t;
        //	_speed = s;
        _value = v;
        set_edge(e);
        _pos = p;
        min_dist = -1;
        min_ro = -1;
        b=false;
    }

    public static CL_Pokemon init_from_json(String json) {
        CL_Pokemon ans = null;
        try {
            JSONObject p = new JSONObject(json);
            int id = p.getInt("id");

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }

    /**
     * check if agent is meant to eat this pokemon
     * @param p
     * @return
     */
    public boolean isOnMe(CL_Pokemon p){
        return b == true;
    }
    public void setIsOnMe(){
        b=true;
    }

    public String toString() {
        return "F:{v=" + _value + ", t=" + _type + "}";
    }

    /**
     *
     * @return edge of pokemon location
     */
    public edge_data get_edge() {
        return this._edge;
    }

    public void set_edge(edge_data _edge) {
        this._edge = _edge;
    }

    /**
     * location of pokemon
     * @return
     */
    public Point3D getLocation() {
        return _pos;
    }

    /**
     * if type > 0 it means that the pokemon is from edge 3 to 4
     * if type < 0 it means that the pokemon is from edge 4 to 3
     * @return
     */
    public int getType() {
        return _type;
    }

    /**
     * value of pokemon is how much score will add to the game
     * @return value of pokemon
     */
    public double getValue() {
        return _value;
    }

    public double getMin_dist() {
        return min_dist;
    }

    public void setMin_dist(double mid_dist) {
        this.min_dist = mid_dist;
    }

    public int getMin_ro() {
        return min_ro;
    }

    public void setMin_ro(int min_ro) {
        this.min_ro = min_ro;
    }
}