package tools;

public class Vertex {
    double x; double y; double z;

    public Vertex() {};

    public Vertex(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Vertex(Matrix m){
        if (m.rows > 3 || m.colomns > 1) {
            throw new IllegalArgumentException("неккоректая матрица");
        } else {
            this.x = m.getValue(1, 1);
            this.y = m.getValue(2, 1);
            this.z = m.getValue(3, 1);
        }
    }

    public Matrix getMatrix(){
        Matrix res = new Matrix(3, 1);
        res.addValue(1, 1, this.x);
        res.addValue(2, 1, this.y);
        res.addValue(3, 1, this.z);
        return res;
    }


    public Vertex transformationXY(Vertex oldVer, double angle){
        double ang = Math.toRadians(angle);
        double sinus = Math.sin(ang);
        double cosines = Math.cos(ang);
        double[][] array = {{cosines, -sinus, 0.0}, {sinus, cosines, 0.0}, {0.0, 0.0, 1.0}};
        Matrix transformation = new Matrix(array);
        Matrix res = oldVer.getMatrix().multiply(transformation);
        Vertex newVer = new Vertex(res);
        return newVer;

    }
    public Vertex transformationXZ(Vertex oldVer, double angle){
        double ang = Math.toRadians(angle);
        double sinus = Math.sin(ang);
        double cosines = Math.cos(ang);
        double[][] array = {{cosines, 0, -sinus}, {0, 1.0, 0}, {sinus, 0, cosines}};
        Matrix transformation = new Matrix(array);
        Matrix res = oldVer.getMatrix().multiply(transformation);
        Vertex newVer = new Vertex(res);
        return newVer;
    }

    public Vertex transformationYZ(Vertex oldVer, double angle){
        double ang = Math.toRadians(angle);
        double sinus = Math.sin(ang);
        double cosines = Math.cos(ang);
        double[][] array = {{1, 0, 0}, {0, cosines, sinus}, {0, -sinus, cosines}};
        Matrix transformation = new Matrix(array);
        Matrix res = oldVer.getMatrix().multiply(transformation);
        Vertex newVer = new Vertex(res);
        return newVer;
    }
    @Override
    public boolean equals(Object o){
        if (o == this){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Vertex vertex = (Vertex) o;
        return this.x == vertex.x && this.y == vertex.y && this.z == vertex.z;
    }
}