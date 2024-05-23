package Entites;

import java.util.Objects;

class Noeud {
    int x, y;
    int gScore = Integer.MAX_VALUE;
    int fScore = Integer.MAX_VALUE;
    Noeud origine;

    public Noeud (int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getFScore() {
        return fScore;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Noeud node = (Noeud) obj;
        return x == node.x && y == node.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}