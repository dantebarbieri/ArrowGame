package arrowgame.src;

import java.lang.Math;

public class Vector{
  float x, y, z;

  public Vector(float x, float y){
    this.x = x;
    this.y = y;
    this.z = 0;
  }

  public Vector(double x, double y){
    this.x = (float)x;
    this.y = (float)y;
    this.z = 0;
  }

  public Vector(float x, float y, float z){
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public Vector(double x, double y, double z){
    this.x = (float)x;
    this.y = (float)y;
    this.z = (float)z;
  }

  private Vector(Vector v){
    this.x = v.x;
    this.y = v.y;
    this.z = v.z;
  }

  public Vector copy(){
    return new Vector(this);
  }

  public static Vector zero(){
    return new Vector(0, 0, 0);
  }

  public static Vector random2D(float s){
    return new Vector(Math.random() * s, Math.random() * s, 0);
  }

  public static Vector add(Vector v1, Vector v2){
    return new Vector(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
  }

  public static Vector sub(Vector v1, Vector v2){
    return new Vector(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
  }

  public static Vector mult(Vector v, float s){
    return new Vector(v.x * s, v.y * s, v.z * s);
  }

  public static Vector fromHeading(float t){
    return new Vector(Math.cos(t), Math.sin(t));
  }

  public static Vector fromHeading(float t, float m){
    return Vector.mult(fromHeading(t), m);
  }

  public static Vector fromVector(Vector v, float m){
    return Vector.mult(v, m / v.len());
  }

  public static Vector rotate(Vector v, float t){
    float h = v.heading();
    t += h;
    return Vector.fromHeading(t, v.len());
  }

  public void add(Vector o){
    x += o.x;
    y += o.y;
    z += o.z;
  }

  public void sub(Vector o){
    x -= o.x;
    y -= o.y;
    z -= o.z;
  }

  public void mult(float s){
    x *= s;
    y *= s;
    z *= s;
  }

  public void setMag(float m){
    mult(m / len());
  }

  public void setHeading(float t){
    Vector goal = fromHeading(t, len());
    x = goal.x;
    y = goal.y;
    z = goal.z;
  }

  public void rotate(float t){
    float h = heading();
    t += h;
    setHeading(t);
  }

  public float dot(Vector o){
    return x * o.x + y * o.y + z * o.z;
  }

  public float lenSq(){
    return x*x + y*y + z*z;
  }

  public float len(){
    return (float)Math.sqrt(lenSq());
  }

  public float heading(){
    return (float)Math.atan2(y, x);
  }

  public Vector normalize(){
    float l = len();
    return new Vector(x / l, y / l, z / l);
  }

  public float[] toFloatArray(){
    return new float[]{x, y, z};
  }

  public int[] toIntArray(){
    return new int[]{(int)x, (int)y, (int)z};
  }

  @Override
  public String toString(){
    return "<" + x + ", " + y + (z == 0 ? "" : ", " + z) + ">";
  }
}