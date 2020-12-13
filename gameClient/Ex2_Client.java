//package gameClient;
//
//import Server.Game_Server_Ex2;
//import api.directed_weighted_graph;
//import api.edge_data;
//import api.game_service;
//import com.google.gson.Gson;
//import com.sun.source.tree.UsesTree;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Iterator;
//import java.util.List;
//
//public class Ex2_Client implements Runnable{
//    private static MyFrame _win;
//    private static Arena _ar;
//    private directed_weighted_graph ga;
//
//    public static void main(String[] a) {
//        Thread client = new Thread(new Ex2_Client());
//        client.start();
//        //client.getName();
//    }
//
//    @Override
//    public void run() {
//        int scenario_num = 11;
//        game_service game = Game_Server_Ex2.getServer(13); // you have [0,23] games
//        //	int id = 999;
//        //	game.login(id);
//        String g = game.getGraph();
//        String pks = game.getPokemons();
//        directed_weighted_graph gg = game.getJava_Graph_Not_to_be_used();
//        init(game);
//
//        game.startGame();
//        _win.setTitle("Ex2 - OOP: by Adi and Dvir lo stam lo stam "+game.toString());
//        int ind=0;
//        long dt=100;
//
//        while(game.isRunning()) {
//            moveAgants(game, gg);
//            try {
//                if(ind%1==0) {_win.repaint();}
//                Thread.sleep(dt);
//                ind++;
//            }
//            catch(Exception e) {
//                e.printStackTrace();
//            }
//        }
//        String res = game.toString();
//
//        System.out.println(res);
//        System.exit(0);
//    }
//    /**
//     * Moves each of the agents along the edge,
//     * in case the agent is on a node the next destination (next edge) is chosen (randomly).
//     * @param game
//     * @param gg
//     * @param
//     */
//    private static void moveAgants(game_service game, directed_weighted_graph gg) {
//        String lg = game.move();
//        List<CL_Agent> log = Arena.getAgents(lg, gg);
//        _ar.setAgents(log);
//        //ArrayList<OOP_Point3D> rs = new ArrayList<OOP_Point3D>();
//        String fs =  game.getPokemons();
//        List<CL_Pokemon> ffs = Arena.json2Pokemons(fs);
//        _ar.setPokemons(ffs);
//        for(int i=0;i<log.size();i++) {
//            CL_Agent ag = log.get(i);
//            int id = ag.getID();
//            double v = ag.getValue();
//            int dest = ag.getNextNode();
//            int src = ag.getSrcNode();
//            if(dest==-1) {
//                dest = nextNode(gg, src);
//                game.chooseNextEdge(ag.getID(), dest);
//                System.out.println("Agent: "+id+", val: "+v+"   turned to node: "+dest);
//            }
//        }
//    }
//    /**
//     * a very simple random walk implementation!
//     * @param g
//     * @param src
//     * @return
//     */
//    private static int nextNode(directed_weighted_graph g, int src) {
//        int ans = -1;
//        Collection<edge_data> ee = g.getE(src);
//        Iterator<edge_data> itr = ee.iterator();
//        int s = ee.size();
//        int r = (int) (Math.random()*s);
//        int i=0;
//        while(i<r) {itr.next();i++;}
//        ans = itr.next().getDest();
//        return ans;
//    }
//
//    private void init(game_service game) {
//        Gson gson = new Gson();
//        String g = game.getGraph();
//        String fs = game.getPokemons();
//        directed_weighted_graph gg = game.getJava_Graph_Not_to_be_used();
//        //gg.init(g);
//        _ar = new Arena();
//        _ar.setGraph(gg);
//        _ar.setPokemons(Arena.json2Pokemons(fs));
//        _win = new MyFrame("test Ex2");
//        _win.setSize(1000, 700);
//        //_win.setResizable(true);
//        _win.update(_ar);
//
//
//        _win.show();
//        String info = game.toString();
//        JSONObject line;
//        try {
//            line = new JSONObject(info);
//            JSONObject ttt = line.getJSONObject("GameServer");
//            int rs = ttt.getInt("agents");
//            System.out.println(info);
//            System.out.println(game.getPokemons());
//            int src_node = 0;  // arbitrary node, you should start at one of the pokemon
//            ArrayList<CL_Pokemon> cl_fs = Arena.json2Pokemons(game.getPokemons());
//            for(int a = 0;a<cl_fs.size();a++) { Arena.updateEdge(cl_fs.get(a),gg);}
//            for(int a = 0;a<rs;a++) {
//                int ind = a%cl_fs.size();
//                CL_Pokemon c = cl_fs.get(ind);
//                int nn = c.get_edge().getDest();
//                if(c.getType()<0 ) {nn = c.get_edge().getSrc();}
//
//                game.addAgent(nn);
//            }
//        }
//        catch (JSONException e) {e.printStackTrace();}
//    }
//}
package gameClient;

import Server.Game_Server_Ex2;
import api.directed_weighted_graph;
import api.*;
import api.game_service;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;
import java.awt.geom.*;


import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Ex2_Client implements Runnable {
    private static MyFrame _win;
    private static Arena _ar;
    private directed_weighted_graph g;


    public static void main(String[] a) {
        Thread client = new Thread(new Ex2_Client());
        client.start();
    }

    public void run() {
        int scenario_num = 11;
        game_service game = Game_Server_Ex2.getServer(scenario_num); // you have [0,23] games
        //	int id = 999;
        //	game.login(id);
        String g = game.getGraph();
        String pks = game.getPokemons();
        directed_weighted_graph gg = game.getJava_Graph_Not_to_be_used();
        try {
            File f = new File("Graph.json");
            FileWriter fileWriter = new FileWriter(f);
            fileWriter.write(g);
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        dw_graph_algorithms ga = new DWGraph_Algo();
        ga.load("Graph.json");
        directed_weighted_graph gg1 = ga.getGraph();
        init(game);
        game.startGame();
        int ind = 3;
        //change
        System.out.println(game.getPokemons());
        long dt = 120;
        while (game.isRunning()) {
            try {
                if(_ar.getAgents().equals(1)){
                    dt = 100;
                }
                if (ind % 1 == 0) {
                    _win.repaint();
                    moveAgants(game, gg1);
                }
                Thread.sleep(dt);
                dt = 150;
                ind++;
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(game.timeToEnd() >= 0) {
                _win.setTimeToEnd(game.timeToEnd() / 10);
                _win.setGrade(game.getPokemons().length());
            }
            _win.setTitle("Ex2 - OOP: (NONE trivial Solution) " + game.toString());
            _win.setLevel(scenario_num);
        }
        String res = game.toString();
        System.out.println(res);
        System.exit(0);
    }

    /**
     * Moves each of the agents along the edge,
     * in case the agent is on a node the next destination (next edge) is chosen (randomly).
     *
     * @param game
     * @param gg
     * @param
     */
    private static void moveAgants(game_service game, directed_weighted_graph gg) {
        String lg = game.move();
        List<CL_Agent> log = Arena.getAgents(lg, gg);
        _ar.setAgents(log);
        String fs = game.getPokemons();
        List<CL_Pokemon> ffs = Arena.json2Pokemons(fs);
        _ar.setPokemons(ffs);
        _ar.getPokemons();
        int counter = 1;
        for (int i = 0; i < log.size(); i++) {
            CL_Agent ag = log.get(i);
            int id = ag.getID();
            int src = ag.getSrcNode();
            double v = ag.getValue();
            if(ag.getNextNode()==-1){
                int got = goToPokemon(ffs, src, gg, counter, ag);
                game.chooseNextEdge(ag.getID(), got);
                System.out.println("Agent: " + id + ",curr fruit is: " + ag.get_curr_fruit() + " val: " + v + "   turned to node: " + got);
            }
            if(ag.isMoving()==false){
                int got = nextNode(gg, src);
                game.chooseNextEdge(ag.getID(), got);
                System.out.println("Agent: " + id + ",curr fruit is: " + ag.get_curr_fruit() + " val: " + v + "   turned to node: " + got);
            }
        }
    }

    private static int goToPokemon(List<CL_Pokemon> ffs, int src, directed_weighted_graph gg, int counter, CL_Agent ag) {
        dw_graph_algorithms ga = new DWGraph_Algo();
        ga.init(gg);
        int a = -1;
        double b = Double.MAX_VALUE;
        CL_Pokemon poke= ffs.get(0);
        //choose most wanted pokemon
        for (CL_Pokemon poke1 : ffs) {
            if(poke.getValue() < poke1.getValue()){
                poke=poke1;
            }
        }

        int way = poke.get_edge().getDest();
        List<node_data> longWay = ga.shortestPath(src, way);
        if (longWay.size() > counter) {
            a = longWay.get(counter).getKey();
            ag.set_curr_fruit(poke);
            ag.setNextNode(a);
//            poke.setIsAgOnMe(ag.getID(),true);
            return a;
        }
        else {
            ag.setNextNode(poke.get_edge().getSrc());
            return poke.get_edge().getSrc();
        }
    }

    /**
     * a very simple random walk implementation!
     *
     * @param g
     * @param src
     * @return
     */
    private static int nextNode(directed_weighted_graph g, int src) {
        int ans = -1;
        Collection<edge_data> ee = g.getE(src);
        Iterator<edge_data> itr = ee.iterator();
        int s = ee.size();
        int r = (int) (Math.random() * s);
        int i = 0;
        while (i < r) {
            itr.next();
            i++;
        }
        ans = itr.next().getDest();
        return ans;
    }

    private void init(game_service game) {
        String g = game.getGraph();
        String fs = game.getPokemons();
        directed_weighted_graph gg = game.getJava_Graph_Not_to_be_used();
        //gg.init(g);
        _ar = new Arena();
        _ar.setGraph(gg);
        _ar.setPokemons(Arena.json2Pokemons(fs));
        _win = new MyFrame("test Ex2");
        _win.setSize(1000, 700);
        _win.setResizable(true);
        _win.update(_ar);

        _win.show();
        String info = game.toString();
        JSONObject line;
        try {
            line = new JSONObject(info);
            JSONObject ttt = line.getJSONObject("GameServer");
            int rs = ttt.getInt("agents");
            System.out.println(info);
            System.out.println(game.getPokemons());
            int src_node = 0;  // arbitrary node, you should start at one of the pokemon
            ArrayList<CL_Pokemon> cl_fs = Arena.json2Pokemons(game.getPokemons());
            for (int a = 0; a < cl_fs.size(); a++) {
                Arena.updateEdge(cl_fs.get(a), gg);
            }
            for (int a = 0; a < rs; a++) {
                double b =0;
                CL_Pokemon k = cl_fs.get(0) ;
                for(CL_Pokemon poke : cl_fs){
                    if (poke.getValue() > b) {
                        b = poke.getValue();
                        k = poke;
                    }
                }
                int nn = k.get_edge().getDest();
                if (k.getType() < 0) {
                    nn = k.get_edge().getSrc();
                }
                game.addAgent(nn);
                cl_fs.remove(k);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
